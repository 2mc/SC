/*

SynthDef("PV_MagAbove", { | in=0, out=0, bufnum=0,
	threshold = 5, // threshold: 0 - 10
	source_level = 0, fx_level = 1 |
	var chain, source;
	source = In.ar(in);
	chain = FFT(bufnum, source);
	chain = PV_BinShift(chain, threshold);
	Out.ar(out, Mix.ar([IFFT(chain) * fx_level, source * source_level]))
});
*/