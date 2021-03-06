//one song in a collection, one movement in a symphony, one continuous audio file      
  
SCMIRAudioFile {      
	  
	var <valid;       
	var <sourcepath, <sourcedir, <basename, <analysispath;    
	var <analysisfilename;      
	var <duration, <numChannels;      
	var <featureinfo, <featuredata, <normalizationtype; 
	var <numfeatures, <numframes;   
	//var <nummfcc, <numchroma; //no longer used, no need to differentiate
	var <numbeats, <beatdata, <tempi, <tempo; //, <featuresforbeats;       
     var <numonsets, <onsetdata;
     var <segmenttimes, <numsegments, <featuresbysegments;  
     
     var <loadstart,<loadframes;  
       
	//var normgroups;       
	  
	*new {|filename, featureinfo, normtype=0, start=0, dur=0|      
		  
		if (filename.isNil,{"Meta_SCMIRAudioFile:new no filename provided".postln; ^nil});       
		  
		^super.new.initSCMIRAudioFile(filename, featureinfo, normtype, start, dur);       
		  
	}    
	  
	//also loads from ZArchive file (usually already containing analyzed feature data)  
	*newFromZ {|filename|      
		  
		if (filename.isNil,{"Meta_SCMIRAudioFile:newFromZ no filename provided".postln; ^nil});       
		  
		//.initSCMIRAudioFile(filename) NO NEED, load should set everything required  
		^super.new.load(filename);       
		  
	}     
	
	
	//copy settings of an existing file, for use with DTW comparison methods
	//assumes feature extraction already took place in original
	//doesn't copy everything, no segments, beats etc
	*newFromRange {|other,starttime=0.0,end|
		
		if (other.isNil,{"Meta_SCMIRAudioFile:newFromRange no other file provided".postln; ^nil});       
		if (other.featuredata.isNil,{"Meta_SCMIRAudioFile:newFromRange other file provided but no feature data".postln; ^nil});       
		
		^super.new.initSCMIRAudioFileFromOther(other,starttime,end);
	}
	
	initSCMIRAudioFileFromOther {|other,starttime=0.0,end|
		var framestart, frameend; 
		var top = other.numframes-1; 
		var timeperhop= SCMIR.hoptime;
		
		this.setFeatureInfo(other.featureinfo,other.normalizationtype); 
		
		numChannels = other.numChannels;     
		
		//don't bother	  
		//sourcepath = other.sourcepath;      
		//analysispath = other.analysispath;   	     
		//basename = other.basename;     
		 
		end= end ?? {other.duration}; 
		
		framestart = (starttime/timeperhop).asInteger; //rounding down
		frameend = (end/timeperhop).asInteger; 

		if(framestart<0) {framestart=0};
		if(framestart>top) {framestart=top};
		if(frameend<0) {frameend=0};
		if(frameend>top) {frameend=top};
		
		duration = (frameend-framestart)*timeperhop;      
		
		numframes = frameend-framestart+1; 
		
	
		numfeatures = other.numfeatures; 
			  
		featuredata = other.featuredata.copyRange(framestart*numfeatures,(frameend+1)*numfeatures-1); 		
	}  
	  
	
	initSCMIRAudioFile {|filename, fi, normtype=0, start=0, dur=0|      
		var loadtemp;   
		  
		//if (filename.pathMatch.isEmpty,{}); //existence check	     
		//check for MP3, create temp .wav file if necessary 
		if((PathName(filename).extension) =="mp3") {
		
			//have to convert whole thing
			filename = this.resolveMP3(filename);
			
		};
		  
		//not multi-thread safe     
		if (valid=SCMIR.soundfile.openRead(filename),{     
			  
			loadstart = (start*(SCMIR.soundfile.numFrames)).asInteger; 
			
			if(loadstart<0 || (loadstart>=(SCMIR.soundfile.numFrames))) {
				loadstart=0; 	
			};
			
			loadframes= if(dur==0) {SCMIR.soundfile.numFrames} {
				
				loadtemp = dur*(SCMIR.soundfile.sampleRate); 
				
				if((loadstart + loadtemp) > (SCMIR.soundfile.numFrames)) {
						
						loadtemp = SCMIR.soundfile.numFrames- loadstart; 
						
						if(loadtemp<0) {
							loadtemp = 0; 
						}; 
						
					};
					
				loadtemp	
				
				};  
			  
			duration=  loadframes/SCMIR.soundfile.sampleRate; //SCMIR.soundfile.numFrames/SCMIR.soundfile.sampleRate;      
			numChannels = SCMIR.soundfile.numChannels;     
			  
			sourcepath = filename;      
			
			sourcedir = sourcepath.dirname;   
			  
			if(SCMIR.tempdir.isNil,{     
				analysispath = sourcepath.dirname ++ "/";      
				},{     
				analysispath = SCMIR.tempdir; 	     
			});      
			  
			basename = sourcepath.basename;     
			  
			basename= basename.copyRange(0,basename.findBackwards(".")-1);     
			  
			},{     
			  
			"SCMIRAudioFile: soundfile failed to load, wrong path?".postln;		     
		});      
		  
		  
		SCMIR.soundfile.close;	       
		  
		this.setFeatureInfo(fi,normtype); 
		
	
		    
	}      
	  
	//no safety, expert use only 
	setFeatureData {|newfeaturedata, numf, featureinformation, renormalize=true| 
		
		featuredata = newfeaturedata; 
		
		numfeatures = numf; 
		
		numframes = featuredata.size.div(numf); 
		
		featureinfo = featureinformation; 
		
		if(renormalize) {
			
			featuredata = this.normalize(featuredata,false,false); 
	
		}
		
		
	}
	
	renormalize {|useglobalnormalization=false|
		
		featuredata = this.normalize(featuredata,false,useglobalnormalization);   
	}
	
	
		  
	  
	//warning: will invalidate current feature data 
	//can also be used for resetting    
	setFeatureInfo {|fi, normtype=0|
		
			//check feature instructions      
		  
		normalizationtype = normtype;     
		
		//invalidates old collected data since formats will be wrong now  
		//featuresforbeats = false;
		featuresbysegments = false;  
		featuredata = nil;  //any old data removed
		numbeats = nil; 
		beatdata = nil; 
		tempi = nil; 
		tempo = nil;   
		  
		featureinfo = fi ?? {[[MFCC, 10]]};   
		//before v0.4 used to be:      
		//[featureclass,normtype,featurespecificparams]     
		//[[MFCC, 1, 10]]    
		
		//impose feature defaults for MFCC?  
		//put anything not in a SequenceableCollection, in one  
		featureinfo= featureinfo.collect{|val| 
			var val2; 
			
			 val2 = if(val.isKindOf(SequenceableCollection)){val}{[val]};
			
			//defaults
			if((val2[0]==MFCC) && (val2.size==1),{val2 = [MFCC,10] });
			
			if(\Chromagram.asClass.notNil) {
				if((val2[0]==Chromagram) && (val2.size==1),{val2 = [Chromagram,12] });
			}; 
			
			//this not supported due to check requiring user to have PolyPitch
			
			if(\PolyPitch.asClass.notNil) {
				if((val2[0]==PolyPitch) && (val2.size==1),{val2 = [PolyPitch,4] }); 
			};
			
			if((val2[0]==\MFCC) && (val2.size==1),{val2 = [MFCC,10] });
			if((val2[0]==\Chromagram) && (val2.size==1),{val2 = [Chromagram,12] });
			if((val2[0]==\PolyPitch) && (val2.size==1),{val2 = [PolyPitch,4] }); 
			 
			 
			 
			 val2
			  };   
		  				
		//featureinfo.postln;  
		  
		numfeatures = 0;      
		numframes = 0;   
		//nummfcc = 0;     
		//numchroma = 0;     
		
		  
		featureinfo.do{|featuregroup|      
			  
			//featuregroup.postln;	    
			     
			//use Symbol rather than class name in case Tartini not installed in system   
			switch(featuregroup[0].asSymbol,    
			\MFCC,{    
			
				//assumes featuregroup[1] exists!!!!!!!!!!!!!!
				//nummfcc= featuregroup[2];    
				numfeatures = numfeatures + featuregroup[1]; //nummfcc;     
		
			},    
			\Chromagram,{    
				  
				//numchroma= featuregroup[2];    
				numfeatures = numfeatures +  featuregroup[1]; //numchroma;     
			}, 
			\Tartini,{    
				  
				numfeatures = numfeatures +  2;     
			}, 
			\PolyPitch, {
				
				numfeatures = numfeatures +  ((2*featuregroup[1])+1); 
				
			}, 
			{ 
				numfeatures = numfeatures +  1;  
			}    
			);	    
			   
			  
			  
		};     
		
		
	}  
	  
	  
	

	
	*resolveFeatures {|input, featurehop, featureinfo| 
		var trig, chain, centroid, features;     
		var mfccfft, chromafft, specfft, onsetfft;      
		var fftsizetimbre = 1024;    
		var fftsizepitch = 4096; //for chromagram, pitch detection  
		var fftsizespec = 2048;   
		var fftsizeonset = SCMIR.framehop; //512 or 1024; //should really be 512 with 256 overlap, but need to conform to general frame size choice 
			
		if (featurehop == 1024) { 
			mfccfft = FFT(LocalBuf(fftsizetimbre,1),input,1, wintype:1);     
			chromafft = FFT(LocalBuf(fftsizepitch,1),input,0.25, wintype:1);     
			//for certain spectral features
			specfft = FFT(LocalBuf(fftsizespec,1),input,0.5, wintype:1);     
			onsetfft = FFT(LocalBuf(fftsizeonset,1),input,1);     
			} {
			//else it should be 512
			mfccfft = FFT(LocalBuf(fftsizetimbre,1),input,0.5, wintype:1);     
			chromafft = FFT(LocalBuf(fftsizepitch,1),input,0.125, wintype:1);     
			//for certain spectral features
			specfft = FFT(LocalBuf(fftsizespec,1),input,0.25, wintype:1);     
			onsetfft = FFT(LocalBuf(fftsizeonset,1),input,1); //will be smaller to start with
			};   
			  
			  
			trig=chromafft;     
			  
			features= [];     
			  
			//if(nummfcc>0,{features = features ++  MFCC.kr(mfccfft,nummfcc); });     
			//			if(numchroma>0,{features = features ++  Chromagram.kr(chromafft,4096,numchroma); });     
			//			   
			featureinfo.do{|featuregroup|      
				  
				//special case for onsets and beat detection; each requires own FeatureSave UGen?  
				//in principle, mfccfft reused, but should be fine since only analysis operations 
				
				
				  
				features = features ++ (switch(featuregroup[0].asSymbol,    
				\MFCC,{    
					MFCC.kr(mfccfft,featuregroup[1]);      
				},    
				\Chromagram,{    
					  
					Chromagram.kr(chromafft,4096,featuregroup[1]);    
				}, 
				\Tartini, {Tartini.kr(input, 0.93, 2048, 0, 2048-featurehop) },
				\PolyPitch,{PolyPitch.kr(input,featuregroup[1])},
				\Loudness, {Loudness.kr(mfccfft) },
				\SensoryDissonance,{SensoryDissonance.kr(specfft, 2048)},
				\SpecCentroid,{SpecCentroid.kr(specfft)},
				\SpecPcile,{SpecPcile.kr(specfft,featuregroup[1] ? 0.5)},
				\SpecFlatness,{SpecFlatness.kr(specfft)},
				\FFTCrest,{FFTCrest.kr(specfft,featuregroup[1] ? 0, featuregroup[2] ? 50000)},
				\FFTSpread,{FFTSpread.kr(specfft)},
				\FFTSlope,{FFTSlope.kr(specfft)},
				//always raw detection function in this feature extraction context
				\Onsets,{Onsets.kr(onsetfft, odftype: (featuregroup[1] ?  \rcomplex), rawodf:1)},
				//more to add: FFTRumble (in combination with pitch detection, energy under f0) 
				\RMS,{Latch.kr(RunningSum.rms(input,1024),mfccfft)},
				\ZCR,{Latch.kr(ZeroCrossing.ar(input),mfccfft)},
				\Transient,{
					chain = DWT(LocalBuf(1024,1), input, 1, wavelettype:2);
					chain = WT_Transient(chain, featuregroup[1] ? 0.5, featuregroup[2] ? 0.1); 
					WT_ModulusSum.kr(chain); 				
					}
				));	  
				  				  
				  
			};     

		
		^[features, trig]; 	
		
	}
	


	//times at start of each windows
	frameStartTimes {
		var featurehop = SCMIR.framehop;
		var hoptime = featurehop/SCMIR.samplingrate;
		
		^if(featuresbysegments) {
			//centres rather than beginnings
			segmenttimes 
		}
		{
			Array.fill(numframes,{|i|  hoptime*i });
		}
	}

	
	//frame time at centre of window
	frameTimes {
		var featurehop = SCMIR.framehop;
		var start = 4096/SCMIR.samplingrate; 
		var hoptime = featurehop/SCMIR.samplingrate; //if (featurehop == 1024) {1024}{}; 
		var frametimes; 
			
		^if(featuresbysegments) {
			//centres rather than beginnings
			segmenttimes.collect{|val,i|  if(i<(segmenttimes.size-1)) {0.5*(segmenttimes[i+1]+val)}{0.5*(duration+val)}  }; 
		}
		{
			Array.fill(numframes,{|i|  start + (hoptime*i) });
		}
			
				
		//^Array.fill(numframes,{|i|  start + (hoptime*i) });
	}
	  
	//must be called within a fork? How to enforce, test that?       
	extractFeatures {|normalize=true, useglobalnormalization=false| //|writefeaturefile= false|   
		  
//		var fftsizetimbre = 1024;    
//		var fftsizepitch = 4096; //for chromagram, pitch detection  
//		var fftsizespec = 2048;   
//		var fftsizeonset = SCMIR.framehop; //512 or 1024; //should really be 512 with 256 overlap, but need to conform to general frame size choice 
		var featurehop = SCMIR.framehop; //1024; //measurement about 40Hz    
		  
		var score; //, analysisfilename;     
		var serveroptions, buffersize;     
		var temp;     
		var normdata;     
		var ugenindex;   
		var file; 
		var def;   
		  
		("Extracting features for"+sourcepath).postln;  
		  
		 //safety if called multiple times and switched to beats later 
		//featuresforbeats = false;     
		featuresbysegments = false; 
		  
		//mono input only     
		def = SynthDef(\SCMIRAudioFileFeatures,{arg playbufnum, length;       
			var env, input, features, trig; 
		//	var , chain, centroid, ;     
		//	var mfccfft, chromafft, specfft, onsetfft;      
			var featuresave;   
			  
			env=EnvGen.ar(Env([1,1],[length]),doneAction:2);		     
			//stereo made mono     
			input= if(numChannels==1,{    
				PlayBuf.ar(1, playbufnum, BufRateScale.kr(playbufnum), 1, 0, 0);    
				},{    
				  
				Mix(PlayBuf.ar(numChannels, playbufnum, BufRateScale.kr(playbufnum), 1, 0, 0))/numChannels;      
				  
			});    
			  
			//get features     
			  
			//ASSUMES SR of 44100 or 48000   
			#features, trig = SCMIRAudioFile.resolveFeatures(input,featurehop,featureinfo); 
	  
			//[\sizecheck, features.size].postln;   
			  
			//Logger.kr(features, trig, loggerbufnum);     
			featuresave = FeatureSave.kr(features, trig);    
			  
			 //issue in that doesn't seem to correspond to necessary unit index 
			//ugenindex =  featuresave.synthIndex;   
			//must check post hoc, because of optimisation changes   
			  
			//[\ugenindex, ugenindex].postln;  
			  
			//no actual output required, goes via logger buffer     
			  
		}); 
		
		//find synth index for FeatureSave
		
		def.children.do{|val,i| if(val.class==FeatureSave,{ugenindex = val.synthIndex})};

		def.writeDefFile;     
		

		//for batch processing, need this fork outside;     
		  
		//wait for SynthDef sorting just in case     
		//0.1.wait;     
		
		//SCMIR.waitIfRoutine(0.1); 
		  
		analysisfilename= analysispath++basename++"features.data";   
		//analysisfilename= analysispath++basename++"features.wav";     
		//analysisfilename.postln;     
		  
		//allow for 10 beats per second, else unreasonable, 2 floats per beat= [beat time, curr tempo estimate]        
		//windows per second * length . numfeatures is number of channels in buffer?      
		//initial delay in FFT implementation is hopsize itself.      
		buffersize= ((44100*duration)/featurehop).asInteger; //+1 for safety not needed unless rounding error on exact match      
		  
		score = [      
		
		//after path, next two arguments: int - starting frame in file (optional. default = 0) int - number of frames to read (optional. default = 0, see below)
		
		//[0.0, [\b_allocRead, 0, sourcepath, 0, 0]],     
		
		[0.0, [\b_allocRead, 0, sourcepath, loadstart, loadframes]],   //loadframes
		
		[0.0, [ \s_new, \SCMIRAudioFileFeatures, 1000, 0, 0,\playbufnum,0,\length, duration]], //plus any params for fine tuning   
		//[0.0,[\u_cmd, 1000, ugenindex, "createfile",analysisfilename]], 
		[0.01,[\u_cmd, 1000, ugenindex, "createfile",analysisfilename]], //can't be at 0.0, needs allocation time for synth before calling u_cmd on it  
		//[0.0, [\b_alloc, 1, buffersize, numfeatures.postln]],     
		//[0.0, [ \s_new, \SCMIRAudioFileFeatureExtraction, 1000, 0, 0,\playbufnum,0,\loggerbufnum,1,\length, duration]], //plus any params for fine tuning     
		  
		//after length of soundfile played, end     
		//[duration,[\b_write,1,analysisfilename,"WAV", "float"]],  
		[duration,[\u_cmd, 1000, ugenindex, "closefile"]],     
		[duration, [\c_set, 0, 0]]      
		];     
		  
		serveroptions = ServerOptions.new;    
		serveroptions.numOutputBusChannels = 1; // mono output     
		  
		//can set how verbose to be?      
		  
		//"NRTanalysis.wav"     
		//issue with Score under 3.4 that it doesn't accept a nil argument for output?    
		
		Score.recordNRTSCMIR(score, "NRTanalysis", "NRToutput", nil,44100, "WAV", "int16", serveroptions); // synthesize   
		//Score.recordNRT(score, "NRTanalysis", "NRToutput", nil,44100, "WAV", "int16", serveroptions); // synthesize  
		  		
		//SCMIR.processWait("scsynth");    
		
		
		  
		//LOAD FEATURES  
		//Have to be careful; Little Endian is standard for Intel processors  
		file = SCMIRFile(analysisfilename,"rb");  
		  
		numframes = file.getInt32LE;   
		  
		//[\numframes,numframes].postln;  
		  
		temp = file.getInt32LE;  
		if (numfeatures!= temp) {  
			"extract features: mismatch of expectations in number of features ".postln;  
			[numfeatures, temp].postln;   
		};   
		  
		temp = numframes*numfeatures;  
		featuredata= FloatArray.newClear(temp);     
		  
//		temp.do{|i|  
//			  
//			featuredata[i] = file.getFloatLE;   
//			  
//		};  
		
		//faster implementation? 
		file.readLE(featuredata); 
		
		if((featuredata.size) != temp) {

			file.close; 
			
			featuredata=nil; 
			
			SCMIR.clearResources; 
							
			file = SCMIRFile(analysisfilename,"rb");   
			file.getInt32LE;
			file.getInt32LE;
			  
			onsetdata= FloatArray.newClear(temp);  
			file.readLE(featuredata);  
		}; 
		
		  
		file.close;   
		  
		//[\ghent, numframes, numfeatures, temp, featuredata].postcs;  
		  
		//load wav then write out txt file instead     
		//SCMIR.soundfile.openRead(analysisfilename);     
		  
		//0.05.wait;     
		  
		//numframes = SCMIR.soundfile.numFrames;  
		//    
		//		featuredata= FloatArray.newClear(numframes*SCMIR.soundfile.numChannels);     
		//		  
		//		[\soundfileanalysis, \numframes, numframes, \numchannels, SCMIR.soundfile.numChannels].postln;   
		//		    
		//		SCMIR.soundfile.readData(featuredata);     
		//		    
		//0.2.wait;     
		  
		//TODO normalisation step    
		if(normalize && (numframes>=1)){featuredata = this.normalize(featuredata,false,useglobalnormalization);   };
		  
		//temp= SCMIR.soundfile.numChannels;     
		  
		//SCMIR.soundfile.close;     
		  
		("rm "++ (analysisfilename.asUnixPath)).systemCmd;      
		this.notify(\extractionDone, this);
		"Feature extraction complete".postln;  
	}    
	 
	 
	
	
	resolveFeatureNumbers {
		
		var accum = 0; 
		var featurecounts; 
		
		featurecounts =featureinfo.collect{|featurenow|    
			  
			var numberlinked = 1;    
			 var index = accum; 
			  
			switch(featurenow[0].asSymbol,
			\MFCC,{
				
				numberlinked = featurenow[1]; 
			},
			\Chromagram,{
				
				numberlinked = featurenow[1];
			},
			\Tartini,{
				numberlinked = 2; 
			}
			); 	
		
			accum  = accum + numberlinked; 
			
			[index, numberlinked]
		
		};
		
		
		^featurecounts; 
	}
	
	  
	  
	//for archiving features detected   
	  
	save { |filename| 
		//Archive- ascii  
		//ZArchive - binary, better for this data  
		var a;   
		var instancevars;   
		  
		filename  = filename??{sourcepath++basename++".scmirZ"};   
		  
		a = SCMIRZArchive.write(filename);  //analysisfilename
		  
		//future proofing, works as long as all have getters  
		instancevars = SCMIRAudioFile.instVarNames;   
		  
		instancevars.do{|iname|  
			  
			a.writeItem(this.perform(iname)); 	  
		};  
		  
		//  
		//		 a.writeItem(valid);   
		//		 a.writeItem(sourcepath);   
		//		 a.writeItem(basename);   
		//		 a.writeItem(analysispath);   
		//		 a.writeItem(analysisfilename);   
		//		 a.writeItem(duration);   
		//		 a.writeItem(numChannels);   
		//		 a.writeItem(featureinfo);   
		//		 a.writeItem(featuredata);   
		//		 a.writeItem(numfeatures);   
		//		 a.writeItem(numframes);   
		//		 a.writeItem(nummfcc);   
		//		 a.writeItem(numchroma);   
		//		    
		a.writeClose;  
		  
		//	// write a test file with numbers:     
		//		a = File(analysisfilename, "w");     
		//		    
		//		numframes.do {|i|     
			//			    
			//			numfeatures.do {|j|     
				//				//interleaved[i*(f.numChannels)+j].postln;     
				//				a.write(interleaved[i*numfeatures+j].asString);      
				//				if(j<(numfeatures-1),{a.write(" ")});      
			//			};     
			//			    
			//			a.write("\n");     
		//		};     
		//		    
		//		a.close;      
		//		    
		//		//"NOW".postln;     
		//		//f.numFrames.postln;     
		//		    
		//		0.5.wait;     
		  
		  
	}  
	  
	  
	load { |filename| 
		var a;   
		var instancevars;   
		  
		filename  = filename?? {sourcepath++basename++".scmirZ"};   
		  
		a = SCMIRZArchive.read(filename);  
		  
		//future proofing, works as long as all have setters; but that is more of an access issue  
		//	instancevars = SCMIRAudioFile.instVarNames;   
		//		  
		//		instancevars.do{|iname|  
			//			this.perform((iname++$_).asSymbol, a.readItem); 	  
		//		};  
		  
		valid = a.readItem;   
		sourcepath = a.readItem;   
		sourcedir = a.readItem; 
		basename = a.readItem;   
		analysispath = a.readItem;   
		analysisfilename = a.readItem;   
		duration= a.readItem;   
		numChannels = a.readItem;   
		
		featureinfo = a.readItem;   
		featuredata = a.readItem;  
		normalizationtype	= a.readItem;
			
		numfeatures =  a.readItem;   
		numframes = a.readItem;   
		//nummfcc = a.readItem;   
		//numchroma = a.readItem;   
		
		numbeats = a.readItem; 
		beatdata = a.readItem;  
		tempi = a.readItem; 
		tempo = a.readItem; 
		//featuresforbeats = a.readItem; 

		numonsets =  a.readItem; 
		onsetdata =  a.readItem; 
		segmenttimes = a.readItem; 
		numsegments = a.readItem; 
		featuresbysegments = a.readItem; 
		  
		loadstart= a.readItem;
		loadframes= a.readItem;  
		  
		  
		a.close;  
		  
	}  
	  
	  
	  
	
		
	
	
	
	exportARFF {|filename|
	
		var file; 
		var last = numfeatures-1;
		
		filename = filename ?? {sourcepath++basename++"features.arff"};
		
		file = File(filename,"w"); 
		
		file.write("@RELATION SCMIR\n");
		
		numfeatures.do{|i|
			
			file.write("@ATTRIBUTE"+i+"NUMERIC\n");
		
		};
		
		file.write("@DATA\n");
		
		numframes.do{|i|
			
			var outputstring; 
			var pos = i*numfeatures; 
			var array; 
			
			array = featuredata[pos..(pos+last)]; 
			outputstring = ""; 
			array.do{|val,j| 
					
					if (j<last) {
						outputstring = outputstring++val++",";
					} {
						outputstring = outputstring++val++"\n"; 
					};
					
				}; 
			
			file.write(outputstring);
		
		};
		
		file.close; 
	}  
	
	
	
	//feature data exported with instances attached to a given class
	exportARFFInstances {|file,category|
	
		var last = numfeatures-1;
		
		if (file.isNil) {
				
			"exportARFFInstance: no ARFF file provided".postln; ^nil;       
		  
		};
			
		numframes.do{|i|
			
			var outputstring; 
			var pos = i*numfeatures; 
			var array; 
			
			array = featuredata[pos..(pos+last)]; 
			outputstring = ""; 
			array.do{|val,j| 
					
					if (j<last) {
						outputstring = outputstring++val++",";
					} {
						outputstring = outputstring++val++","++category++"\n"; 
					};
					
				}; 
			
			file.write(outputstring);
		
		};
		
	}  
	
	  
	  
}      
  
  
  
  
 
