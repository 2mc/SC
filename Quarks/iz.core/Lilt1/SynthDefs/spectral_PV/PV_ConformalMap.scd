/*
SynthDef("PV_ConformalMap", { arg in=0, out=0, bufnum=0, 
	// real: -1.0, 1.0    imag: -1.0, 1.0
	real= 0.5, imag = 0.2;
	var chain;
	chain = FFT(bufnum, In.ar(in));
	chain = PV_ConformalMap(chain, real, imag); 
	Out.ar(out, IFFT(chain));
});


*/