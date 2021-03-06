//This file is part of The BBCut Library. Copyright (C) 2001  Nick M.Collins distributed under the terms of the GNU General Public License full notice in file BBCutLibrary.help

//Onset Detector class- N.M.Collins 7/11/03

AmpAndSlope : OnsetDetector
{

*new {
^super.new.initAmpAndSlope;
}


initAmpAndSlope {
	uiparams=
	[
	["threshold", 0.0001,1.0,\exponential,0.0001,0.02],
	[ "decaytime", 0.005,0.4,\lin,0.005,0.08],
	[ "windowsize", 1,2000,\lin,1,100],
	[ "slopethreshold", 0.0001,1.0,\exponential,0.0001,0.0025]
	];
	
	params=uiparams.collect({arg val; val[5];});
}

//pass in the filename to run on or an SF3 object
runOnFile
{
arg sf, audition=true;
var s,l;
var node;

s= BBCut.currentserver;
		
l=List.new;
	
//if passed in filename	
if(not(sf.isKindOf(SF3)	),{sf= SF3(sf);});
	
// register to receive this message
OSCresponder(s.addr,'/tr',{ arg time,responder,msg;
//could store msg.at(0) as amplitude test
	l.add(time);
}).add;	
		
node=SynthDef(\rmsod1,
{
 var source1, proc, test, trig, diff, trig2, test2, combine;
	
	source1= sf.playForSynthDef; 
	
	if(sf.numChannels>1,{source1=Mix.ar(source1)});

	SendTrig.ar(Impulse.ar(0), 1, 1);
	
	proc= RunningSum.rms(source1, params[2]);
	
	test= proc-(params[0]);
	
	//takes decaytime*0.5 to drop by 60dB
	//test= Decay.ar(test-0.001, decaytime*0.5).clip2(1.0);
	
	trig= test>0; //Trig1.ar(test, params[1]);
	
	//2 point differentiator, could make DelayN.ar based one later 
	diff= HPZ1.ar(proc);
	
	test2= diff- (params[3]);
	
	trig2= test2>0; //Trig1.ar(test2, params[1]);
	
	combine= Trig1.ar((trig+trig2)>1.5,params[1]);
	
	trig=Trig1.ar(combine,params[1]);
	//Trig1.ar((combine<1.5),params[1]);
	
	SendTrig.ar(combine, 0, proc);
	
	if(audition, {Out.ar(0,[trig*SinOsc.ar(440, 0, 0.2),0.5*source1])});
	
	}).play(s);

SystemClock.sched(sf.length+0.5,{
node.free;

l.postln;
//adjust relative to first trigger
l= l-l.at(0);
//remove initial marker
l=l.copyRange(1, l.size-1);

l.postln;

onsets= l/(sf.length);

onsets.postln;
});

}



}