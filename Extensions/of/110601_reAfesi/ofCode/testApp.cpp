#include "testApp.h"
#include "stdio.h"

//--------------------------------------------------------------
testApp::testApp(){

}

//--------------------------------------------------------------
void testApp::setup(){
	{
<<<<<<< HEAD:Extensions/of/110601_reAfesi/ofCode/testApp.cpp
		//ofSetFullscreen(true);
		
		ofSetBackgroundAuto(false);
		ofEnableSmoothing();
		ofEnableAlphaBlending();
		ofBackground(0,0,0);
		ofSetFrameRate(60);
		ofSetWindowTitle("reAfesi");
	}	//Screen
	{
		texScreen.allocate(ofGetWidth(), ofGetHeight(),GL_RGB);// GL_RGBA); 
	}	//Texture
	{
		af0.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af0.png");
		af1.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af1.png");
		af2.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af2.png");
		af3.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af3.png");
		af4.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af4.png");
		af5.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af5.png");
		af6.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af6.png");
		af7.loadImage("/Users/fou/Dropbox/ArisOmer/AferesiDB/aferesi/af7.png");												
	}	//load DATA
	{
		cout << "listening for osc messages on port " << PORT << "\n";
		receiver.setup( PORT );
		current_msg_string = 0;
	}	//OSC
	{		
		for (int i = 0; i < MAX_SKETCHES; i++){
			sketch[i].init(ofRandom(0.01, 0.99), ofRandom(0.01, 0.99));
		}
	}	//sketch
	{
=======
		af1.loadImage("/Users/omerchatziserif/Dropbox/Aris-Omer/AferesiDB/images/af1.png");
		af2.loadImage("/Users/omerchatziserif/Dropbox/Aris-Omer/AferesiDB/images/af2.png");		
		af3.loadImage("/Users/omerchatziserif/Dropbox/Aris-Omer/AferesiDB/images/af3.png");				
		af4.loadImage("/Users/omerchatziserif/Dropbox/Aris-Omer/AferesiDB/images/af4.png");				
		af5.loadImage("/Users/omerchatziserif/Dropbox/Aris-Omer/AferesiDB/images/af5.png");				
		af6.loadImage("/Users/omerchatziserif/Dropbox/Aris-Omer/AferesiDB/images/af6.png");				
		af7.loadImage("/Users/omerchatziserif/Dropbox/Aris-Omer/AferesiDB/images/af7.png");												
	}	//load DATA
	
    // listen on the given port
	cout << "listening for osc messages on port " << PORT << "\n";
	receiver.setup( PORT );
    current_msg_string = 0;
	
    {
>>>>>>> 2ada020e583feed83ee3fabaf5fbc089cda9a62c:Extensions/of/110601_Aferesi/ofCode/testApp.cpp
	iv["textureRed"] = iv["textureGreen"] = iv["textureBlue"] = iv["textureAlpha"] = 255;
	iv["reverseEllipse"] = ofGetWidth();	iv["reverseTexture"] = -1;
	iv["mirrorMode"] = 0;
	fv["spectroRed"] = fv["spectroGreen"] = fv["spectroBlue"] = 1;
	
	fv["xPosImg"] = ofGetWidth()/2 - af1.width/2;
	fv["yPosImg"] = ofGetHeight()/2 - af1.height/2;
	
	fv["wImg"] = af1.width; fv["hImg"] = af1.height;
	}	//Initial value
}

//--------------------------------------------------------------
void testApp::update(){
	{
	for ( int i=0; i<NUM_MSG_STRINGS; i++ )
	{
		if ( timers[i] < ofGetElapsedTimef() )
			msg_strings[i] = "";
	}

	// check for waiting messages
	while( receiver.hasWaitingMessages() )
	{
		ofxOscMessage m;
		receiver.getNextMessage( &m );

		// map implementation
		if ( m.getAddress() == "int" )	{
			iv[m.getArgAsString(0)] = m.getArgAsInt32(1);	
			printf("value = %d\n", m.getArgAsInt32(1));		
		}
		if ( m.getAddress() == "float" )	{
			fv[m.getArgAsString(0)] = m.getArgAsFloat(1);			
		}
		if ( m.getAddress() == "img" )	{
<<<<<<< HEAD:Extensions/of/110601_reAfesi/ofCode/testApp.cpp
			printFoto(int(m.getArgAsFloat(0)), m.getArgAsFloat(1), m.getArgAsFloat(2),m.getArgAsFloat(3),m.getArgAsFloat(4));			
		}				
=======
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // GL_SRC_ALPHA_SATURATE,GL_ONE     GL_SRC_ALPHA, GL_ONE
			//glBlendFunc(GL_ZERO, GL_ONE_MINUS_SRC_ALPHA);
			ofFill();
			//ofSetColor(0xFFFFFF);				
            ofSetColor(255,255,255,100);				
			
            if	(m.getArgAsString( 0 ) == "af1")	{		
				iv["xPosImg"] = m.getArgAsInt32(1);
				iv["yPosImg"] = m.getArgAsInt32(2);
				iv["wImg"] = m.getArgAsInt32(3);
                iv["hImg"] = m.getArgAsInt32(4);
                af1.draw(iv["xPosImg"],iv["yPosImg"], iv["wImg"], iv["hImg"]);					
			}	else	if (m.getArgAsString( 0 ) == "af2")	{

				//af2.draw(ofGetWidth()/2 - af2.width/2, ofGetHeight()/2 - af2.height/2);	
				af2.draw(iv["xPosImg"],iv["yPosImg"], iv["wImg"], iv["hImg"]);					

			}	else	if (m.getArgAsString( 0 ) == "af3")	{
				//af3.draw(ofGetWidth()/2 - af3.width/2, ofGetHeight()/2 - af3.height/2);	
				af3.draw(iv["xPosImg"],iv["yPosImg"], iv["wImg"], iv["hImg"]);					
				
			}	else	if (m.getArgAsString( 0 ) == "af4")	{
				//af3.draw(ofGetWidth()/2 - af3.width/2, ofGetHeight()/2 - af3.height/2);	
				af4.draw(iv["xPosImg"],iv["yPosImg"], iv["wImg"], iv["hImg"]);					
				
			}	else	if (m.getArgAsString( 0 ) == "af5")	{
				//af3.draw(ofGetWidth()/2 - af3.width/2, ofGetHeight()/2 - af3.height/2);	
				af5.draw(iv["xPosImg"],iv["yPosImg"], iv["wImg"], iv["hImg"]);					
				
			}	else	if (m.getArgAsString( 0 ) == "af6")	{
				//af3.draw(ofGetWidth()/2 - af3.width/2, ofGetHeight()/2 - af3.height/2);	
				af6.draw(iv["xPosImg"],iv["yPosImg"], iv["wImg"], iv["hImg"]);					
				
			}	else	if (m.getArgAsString( 0 ) == "af7")	{
				//af3.draw(ofGetWidth()/2 - af3.width/2, ofGetHeight()/2 - af3.height/2);	
				af7.draw(iv["xPosImg"],iv["yPosImg"], iv["wImg"], iv["hImg"]);					
				
			}

			//cout << "ok" << endl;
		}
>>>>>>> 2ada020e583feed83ee3fabaf5fbc089cda9a62c:Extensions/of/110601_Aferesi/ofCode/testApp.cpp
	}
	}	//OSC
}
//--------------------------------------------------------------
void testApp::draw(){
	switch ( iv["mirrorMode"] )	{
		case 0:
		break;
		 case 1:
			texScreen.loadScreenData(0,0,ofGetWidth(), ofGetHeight());
			ofSetColor(iv["textureRed"],iv["textureGreen"],iv["textureBlue"],iv["textureAlpha"]);
			texScreen.draw(iv["reverseTexture"],0,ofGetWidth(), ofGetHeight());
			
			break;
		 case 2:
			texScreen.loadScreenData(0,0,ofGetWidth(), ofGetHeight());
			ofSetColor(iv["textureRed"],iv["textureGreen"],iv["textureBlue"],iv["textureAlpha"]);
			texScreen.draw(iv["reverseTexture"],0,ofGetWidth(), ofGetHeight());
			break;
		 case 3:
			texScreen.loadScreenData(0,0,ofGetWidth(), ofGetHeight());
			ofSetColor(iv["textureRed"],iv["textureGreen"],iv["textureBlue"],iv["textureAlpha"]);
			texScreen.draw(iv["reverseTexture"],0,ofGetWidth(), ofGetHeight());
			break;

		 case 4:
			texScreen.loadScreenData(0,0,ofGetWidth()/2, ofGetHeight());
			ofSetColor(iv["textureRed"],iv["textureGreen"],iv["textureBlue"],iv["textureAlpha"]);					
			texScreen.draw(-1,0,ofGetWidth()/2, ofGetHeight());					
			texScreen.loadScreenData(ofGetWidth()/2, 0,ofGetWidth(), ofGetHeight());
			ofSetColor(iv["textureRed"],iv["textureGreen"],iv["textureBlue"],iv["textureAlpha"]);					
			texScreen.draw(ofGetWidth()/2 +1,0,ofGetWidth(), ofGetHeight());					
			break;
		 case 5:
			ofSetColor(255,255,255,255);
			texScreen.loadScreenData(0,0,ofGetWidth()/4, ofGetHeight());
			texScreen.draw(-1,0);					
			texScreen.loadScreenData(ofGetWidth()/4, 0,ofGetWidth()/4, ofGetHeight());
			texScreen.draw(ofGetWidth()/4 + 1,0);					
								
			texScreen.loadScreenData(ofGetWidth()/4, 0,ofGetWidth()/4, ofGetHeight());
			texScreen.draw(3*ofGetWidth()/4 + 1,0);					
//
			texScreen.loadScreenData(ofGetWidth()/2, 0, ofGetWidth()/4, ofGetHeight());
			texScreen.draw(ofGetWidth()/2 - 1,0);
			
			break;
		 default:
<<<<<<< HEAD:Extensions/of/110601_reAfesi/ofCode/testApp.cpp
			printf("%d", fv["mirrorMode"]);
		}	// mirrowMode
		
	for( int i=0; i<100; i++ ) {
		sketch[i].draw(mouseX, mouseY, 0, 255,255,255,50, 1);	
	}		
	
=======
			printf("%d", iv["mirrorMode"]);
	  }
>>>>>>> 2ada020e583feed83ee3fabaf5fbc089cda9a62c:Extensions/of/110601_Aferesi/ofCode/testApp.cpp
}

void testApp::keyPressed  (int key){
	if(key == 'v' or key == 'V'){
		full = !full;
		if(full){
			ofSetFullscreen(true);
		} else {
			ofSetFullscreen(false);
		}
	}	
	if(key == 'b' or key == 'B'){
		ofBackground(0,0,0);
	}	
	if(key == 't' or key == 'T'){
		//ofEllipse(100,100,100,100);
		for (int i = 0; i < 10; i++)	{
			ofSetColor(255,255,255,255);
			texScreen.loadScreenData(int(ofRandom(0,1200)),int(ofRandom(0,1200)),int(ofRandom(100,500)),int(ofRandom(100,500)));
			texScreen.draw(int(ofRandom(0,1400)),int(ofRandom(0,1400)),100,100);			
		}		
	}	
}
void testApp::mouseDragged(int x, int y, int button){}
void testApp::mousePressed(int x, int y, int button)	{

}
void testApp::mouseReleased(int x, int y, int button){}

void testApp::printFoto(int photoID, float xPosImg, float yPosImg, float wImg, float hImg)	{
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // GL_SRC_ALPHA_SATURATE,GL_ONE     GL_SRC_ALPHA, GL_ONE
	ofFill();
	ofSetColor(0xFFFFFF);				
	fv["xPosImg"] = xPosImg;
	fv["yPosImg"] = yPosImg;
	fv["wImg"] = wImg;
	fv["hImg"] = hImg;			
	switch ( photoID )	{
		case 0:	af0.draw(fv["xPosImg"],fv["yPosImg"], fv["wImg"], fv["hImg"]);	break;
		case 1:	af1.draw(fv["xPosImg"],fv["yPosImg"], fv["wImg"], fv["hImg"]);	break;
		case 2:	af2.draw(fv["xPosImg"],fv["yPosImg"], fv["wImg"], fv["hImg"]);	break;
		case 3:	af3.draw(fv["xPosImg"],fv["yPosImg"], fv["wImg"], fv["hImg"]);	break;
		case 4:	af4.draw(fv["xPosImg"],fv["yPosImg"], fv["wImg"], fv["hImg"]);	break;
		case 5:	af5.draw(fv["xPosImg"],fv["yPosImg"], fv["wImg"], fv["hImg"]);	break;
		case 6:	af6.draw(fv["xPosImg"],fv["yPosImg"], fv["wImg"], fv["hImg"]);	break;
		//default: //printf("No foto found\n");
	}
}

