

AbstractUniqueServerObject : UniqueObject {

	*makeKey { | key, target |
		^(target.asTarget.server.asString ++ ":" ++ key).asSymbol;
	}

	*onServer { | server |
		var regexp;
		regexp = format("^%:", server.asTarget.server);
		^Library.global.at(this.mainKey).values select: { | node |
			regexp.matchRegexp(node.key.asString);
		};	
	}
} 

UniqueSynth : AbstractUniqueServerObject {
	*mainKey { ^\synths }
	*removedMessage { ^\n_end }

	*new { | key, defName, args, target, addAction=\addToHead ... moreArgs |
		^super.new(key, target.asTarget, defName ?? { key.asSymbol }, args, addAction, *moreArgs);
	}

	init { | target, what ... moreArgs |
		if (target.server.serverRunning) {
			this.makeObject(target, what, *moreArgs);
		}{
			ServerReady(target.server).addOneShot(this, {
				this.makeObject(target, what, *moreArgs);
		// needed because the server has not time to send the \n_go notification
		// when the synth is created right after booting the server
				NotificationCenter.notify(key, \n_go, this);
			});
			target.server.boot;
		}
	}

	makeObject { | target, defName, args, addAction |
		object = Synth(defName, args, target, addAction);
		this.registerObject;
	}

	registerObject {
		object addDependant: { | me, what |
			switch (what, 
				\n_go, {
					NotificationCenter.notify(key, \n_go, this);
				},
				\n_end, {
					this.remove;
					object.releaseDependants; // clean up synth's dependants
				}
			);
		};
		object.register;
	}

	synth { ^object }						// synonym
	isPlaying { ^object.isPlaying; }
	release { object.release }

	// Synchronization with start / stop events: 
	onStart { | func |
		NotificationCenter.registerOneShot(key, \n_go, this, func);
	}
	onEnd { | func | this.onRemove(func) }	// synonym

	wait { | dtime = 0 |	
	/* wait dtime seconds after start of synth or after receiving wait, whichever is earlier
	makes routines wait safely when starting a UniqueSynth with unbooted server.
	Can only be called inside a routine.
	This includes running a code snippet by typing Command-Shift-x (see DocListWindow)
	*/
	// cannot use this.onStart({ dtime.wait }) because it calls .wait on a function, not a routine
		while { this.isPlaying.not }{ 0.01.wait };
		dtime.wait;
	}	
	
	rsync { | func, clock |
		var routine;
		this.onStart({
			routine = { func.(object, this) }.fork(clock ? AppClock);
		});
		this.onEnd({
			routine.stop;	
		});	
	}

	rsyncs { | func | this.rsync(func, SystemClock) }
	rsynca { | func | this.rsync(func, AppClock) }
	
	dur { | dtime = 1 | this.onStart({ { this.release }.defer(dtime) }) }

	free { if (this.isPlaying ) { object.free } }	// safe free: only runs if not already freed
	
}

// Synonym - abbreviation for UniqueSynth :  
Usynth : UniqueSynth {}

