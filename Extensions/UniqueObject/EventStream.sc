/* 
Pattern and stream support for looping Functions

The Stream stores itself in the current environment, that way each environment can have its own namespace, and name reuse becomes possible 
*/

EventStream {
	var <key, <pattern, <stream, <value;
	
	*new { | key, pattern |
		^currentEnvironment[key] ?? { this.newCopyArgs(key, pattern).init };
	}

	init {
		currentEnvironment[key] = this;
		this.reset;
	}
	
	next { 
		value = stream.next;
		if (value.isNil) { this.remove };
		^value;
	}

	reset { stream = pattern.asStream }

	remove { currentEnvironment[key] = nil }		
}

+ Symbol {
	stream { | pattern | ^EventStream(this, pattern) }
	next { ^EventStream(this).next }
	this { ^EventStream(this).value }
	replaceStream { | pattern | ^EventStream(this).init(pattern) } // next not called!
	resetStream { ^EventStream(this).reset } 	// next not called: design choice.
	removeStream { ^EventStream(this).remove }

	// Shortcuts for creating all kinds of patterns can be added.
	pseq { | array, repeats = 1 | ^this.stream(Pseq(array, repeats)); }
	pseq1 { | ... elements | ^this.pseq(elements, 1); }
	pser { | array, repeats = inf | ^this.stream(Pser(array, repeats)); }
	pser1 { | ... elements | ^this.stream(Pser(elements, inf)); }
	pwhite { | lo, hi, length = inf | ^this.stream(Pwhite(lo, hi, length)) }
	prand { | array, repeats = inf | ^this.stream(Prand(array, repeats)) }
	prand1 { | ... elements | ^this.stream(Prand(elements, inf)) }
	pn { | pattern, repeats = 1 | ^this.stream(Pn(pattern, repeats)) }
	pfuncn { | func, repeats = 1 | ^this.stream(Pfuncn(func, repeats)) }
	pseries { | start = 0, step = 1, length = inf | ^this.stream(Pseries(start, step, length)) }
	once { | dur = 0 | ^this.stream(r { dur.yield }) }
}

