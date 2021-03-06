/* (IZ 2006-05-07) {
Accessing vertex data from within Paction stream
} */


var hihat; // the instrument
var envir; // listener's envir - contains vertex data
var stream;	// the action stream


hihat = ~session.addScriptAt('Sand', 'nohihat');
envir = ~envir;	// get the envir locally for faster access
stream = Pactions([
	{
		hihat.set(\dur, 3 * envir.wait); // extend duration 
		hihat.set(\freq, envir.aa.abs * 800 + 250);
		hihat.start;
	},
	2	// skip 2 vertices
], inf).asStream;	// inf: repeat actions array for ever

// The 1 rule: play the next action in the stream 
~conductor = {
	stream.next;
};
