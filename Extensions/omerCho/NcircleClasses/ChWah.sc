/*

ChWah.load;

*/

ChWah {
	classvar <action;
	*load {
	var s;
	s = Server.default;
	
		SynthDef("wah", { |out = 0, in = 0, amp = 1, pan = 0, 
			rate = 0.5, cfreq = 1400, mfreq = 1200, rq=0.1, dist = 0.15,
			lvl = 0.9, durt = 0.01|
			
			var zin, zout, ses;
			
			zin = In.ar(in, 2);
			cfreq = Lag3.kr(cfreq, 0.1);
			mfreq = Lag3.kr(mfreq, 0.1);
			rq   = Ramp.kr(rq, 0.1);
			zout = RLPF.ar(zin, LFNoise1.kr(rate, mfreq, cfreq), rq, amp).distort * dist;
			ses = Limiter.ar(zout, lvl, durt);
			ses = PanAz.ar(
					2, 						// numChans
					ses, 					// in
					SinOsc.kr(0.01, -0.1,0.1), 	// pos
					0.5,						// level
					2.5						// width
				);
			
			Out.ar( out , ses); 
		}).send(s);
		
		
		~distortF =�OSCresponderNode(nil,�'/outs/distort', {�|t,r,m|�
			var�n1;
			n1�= (m[1]*5) ;
			~wah.set(\dist, n1);
		}).add;		
		~wahrqF =�OSCresponderNode(nil,�'/outs/wahrq', {�|t,r,m|�
			var�n1;
			n1�= (m[1]*20)-5 ;
			~wah.set(\rq, n1);
		}).add;
		~wahampF =�OSCresponderNode(nil,�'/outs/wahamp', {�|t,r,m|�
			var�n1;
			n1�= (m[1]*6) ;
			~wah.set(\amp, n1);
		}).add;
	
	
	}
	
	*unLoad{
	
	
	}
	
}