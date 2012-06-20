//systemCmd rather than unixCmd so blocks until done

+ Score { 
	recordNRTSCMIR { | oscFilePath, outputFilePath, inputFilePath, sampleRate = 44100, 
		headerFormat = "AIFF", sampleFormat = "int16", options, completionString="", duration = nil | 
		var cmd;
		//var pipe, line, cmd;
		postf("Score starting NRT SCMIR. Paths are:\nosc: %\ninput: %\noutput: %\n", 
			oscFilePath, inputFilePath, outputFilePath);
		
		this.writeOSCFile(oscFilePath, 0, duration); 
		
		cmd = program + "-v -2 -N" + oscFilePath.quote 
			+ if (inputFilePath.notNil) { inputFilePath.quote } { "_" } 
			+ outputFilePath.quote 
			+ sampleRate + headerFormat + sampleFormat
			+ (options ? Score.options).asOptionsString 
			+ completionString;

		//systemCmd(cmd); 
		SCMIR.external(cmd, true);

		//SCMIR.pipe(cmd); 
		
		//pipe = Pipe.new(cmd, "r");				
//		line = pipe.getLine;								// get the first line
//		while({line.notNil}, {line.postln; line = pipe.getLine; });	// post until l = nil
//		pipe.close;										// close the pipe to avoid that nasty buildup
//		
	}	 
	 
	*recordNRTSCMIR { | list, oscFilePath, outputFilePath, inputFilePath, sampleRate = 44100, 
		headerFormat = "AIFF", sampleFormat = "int16", options, completionString="", duration = nil |
		this.new(list).recordNRTSCMIR(
			oscFilePath, outputFilePath, inputFilePath, sampleRate, 
			headerFormat, sampleFormat, options, completionString, duration
		); 
	}	 
		 
		 
} 