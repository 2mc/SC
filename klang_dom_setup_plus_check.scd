

~rate = 1;

~numchannels = 52;

Server.default = s = Server.local;


Server.default.options.numAudioBusChannels = 256;
Server.default.options.numOutputBusChannels = ~numchannels;
Server.default.options.numInputBusChannels = 2;

s.waitForBoot({
	~r = {
		~rate.wait;
		~a = { | out = 0 |
		� � � �Out.ar(out, PinkNoise.ar(Decay2.kr(Impulse.kr(4), 0.01, 0.15, 0.05)));
		� � � �Out.ar(out, SinOsc.ar(880, 0, Decay2.kr(Impulse.kr(4, 2 * pi), 0.1, 0.15, 0.2)));
		}.play;
/*	� � inf do: { | i |
			~rate.wait;
			~a.set(\out, i % ~numchannels);
	� � };
*/
	}.fork;
});

(
~l = EZSlider(label: " channel ", controlSpec: ControlSpec(1, ~numchannels, \linear, 1, 0))
	.action = { | me | ~a.set(\out, me.value.asInteger - 1) };
	
~l.sliderView.window.postln;
)

/*
~a = { | out = 0 |

� � � �Out.ar(out, SinOsc.ar(50, 0, Decay2.kr(Impulse.kr(4), 0.01, 0.15, 0.15)));

//� � � �Out.ar(out, LFNoise2.ar(50, Decay2.kr(Impulse.kr(4), 0.01, 0.15, 0.05)));
//� � � �Out.ar(out, SinOsc.ar(880, 0, Decay2.kr(Impulse.kr(4, 2 * pi), 0.1, 0.15, 0.2)));
}.play;


*/

// Channel test.

/*
a.set(\out, 23);

~rate = 1;

~r.stop;

(Platform.userAppSupportDir ++ "/mini_dom_setup_plus_check.scd").load;

*/
