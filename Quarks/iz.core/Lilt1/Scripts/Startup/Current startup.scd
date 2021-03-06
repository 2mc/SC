/* 
Whatever is in the present script: "Startup/Current startup" is executed by Main-startup each time after the library is recompiled (Command-K).

This Script is not used as a Script in a Patch Window, but is executed as startup.  
To execute a scripts code "raw", load it by name with: 
	ScriptBrowser.loadPath(foldername, scriptname);
Note: The script must already be loaded in the ScriptBrowser for this to work. 

One can store other scripts in this folder as backups, alternatives, or modular sub-scripts for customizing startup. 
*/

// Load session 'Default Session', if present
// ScriptBrowser.at('Sessions', 'Default Session');

// Server.default.boot; // boot the default server
// Samples(Server.default).load("./sounds/a11wlk*");
// Samples(Server.default).load("./sounds/break*");
// Samples(Server.default).load("./sounds/Sined*");

// Make a window for opening/closing browsers for various objects: 
ScriptBrowser.loadPath('Startup', 'BrowserControlWindow');
// Load all files in folder "Utilities" in a session with that name: 
// ScriptBrowser.loadPath('Includes', 'UtilitiesSession');

// ScriptPatch.loadDefault; // load default script-patch

// { =============== OSC ===============
// Enable sending of SC code over OSC. This installs OSCresponders for commands
// 'do' and 'doArgs'
ScriptBrowser.loadPath('Startup', 'Enable OSC commands');
// }

// { ============== SPECIFIC PROJECTS =============
// Tests: 
// Script.at('buffer playback', 'playbuf (looping)').makeGui;
// Setup for project Kosmos-X : 
// ScriptBrowser.loadPath('Kosmos-X', '00Kosmos X Startup');
// }

// ================ WRAP-UP =============
ScriptBrowser.makeGui;		// Make ScriptBrowser browser last to have window foremost

// Move message window below Script Browser:
// Document.listener.bounds = Rect(0, -22, 630, 400).fromTop.fromRight;
// Document.listener.front;

// Session.select('Untitled Session');

