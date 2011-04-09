
DocProxy {
	var <name;
	var <path;
	var <text;
	var <>bounds;		// for restoring bounds of documents saved in sessions;
	var <timestamp;	// timestamp of date last saved in archive. (Date instances themselves cannot be archived)
		
	*new { | document |
		^this.newCopyArgs(document.name, document.path, document.text, document.bounds, Date.getDate.stamp);
	}

	open { | fromArchive = false |
		var doc, extension;
		if (Document.allDocuments.detect({ | d | d.name == name }).notNil) {
			^postf("Document: % is already open\n", name);
		};
		doc = if (fromArchive) { this.openFromArchive } { this.openFromPath };
		^doc;
	}

	openFromPath {
		var match;
		if (path.isNil) { ^this.openFromArchive };
		match = path.pathMatch;
		if (match.size == 0) { ^this.openFromArchive }; 
		^Document.open(path);
	}

	openFromArchive {
		var doc, extension;
		doc = Document(name, text);
		extension = doc.name.splitext.last;
		if (extension == "scd" or: { extension == "sc" }) { doc.syntaxColorize };
		^doc;
	}
}

DocSession {
	classvar <sessionArchiveRoot = \docSessions;
	var <name;
	var <docs;

	*new { | name, docProxies |
		^this.newCopyArgs(name, docProxies ?? { this.getAllDocs });
	}

	*getAllDocs { 
		^Document.allDocuments collect: DocProxy(_);	
	}

	*saveDialog { | docProxies |
		TextDialog(
			"input name of session",
			Date.getDate.stamp,
			{ | name | this.new(name, docProxies).save },
			{ "save cancelled".postln }
		);
	}
	
	save {
		Archive.global.put(sessionArchiveRoot, name.asSymbol, this);
		Archive.write;
	}

	
	*load { | name |
		^Archive.global.at(sessionArchiveRoot, name.asSymbol);
	}

	*loadDialog { | ref |
		ListSelectDialog("Select a session", Archive.global.at(sessionArchiveRoot).keys.asArray,
			{ | i, name |
				ref.value = this.load(name);
			},{
				"Loading cancelled".postln;
			}
		);
	}

	*loadAndOpenDialog { | fromArchive = false |
		ListSelectDialog("Select a session", Archive.global.at(sessionArchiveRoot).keys.asArray,
			{ | i, name |
				this.load(name).openAllDocs(fromArchive);
			},{
				"Loading cancelled".postln;
			}
		);
	}

	openAllDocs { | fromArchive = false |
		docs do: _.open(fromArchive);	
	}
}