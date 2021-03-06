DocThemes {
	classvar <defaultCustomTheme = \pinkString;
	classvar <currentTheme = \default;

	*initClass { StartUp.add(this); }

	*doOnStartUp {
		var theme;
		this.createDefaultCustomTheme;
		CocoaMenuItem.addToMenu("Code", "toggle light/dark color theme", ["T", true, true], {
			this.toggle;
		});
		if (File.exists(this.colorThemeFile)) {
			theme = Object.readArchive(this.colorThemeFile);
		};
		theme = theme ? \default;
		this setTheme: theme;
	}

	*colorThemeFile { ^Platform.userExtensionDir +/+ "ColorTheme.scd" }

	*toggle {
		if (currentTheme === \default) {
			this setTheme: defaultCustomTheme;
			defaultCustomTheme.writeArchive(Platform.userExtensionDir +/+ "ColorTheme.scd");
		}{
			this.resetToSCdefault;
			\default.writeArchive(Platform.userExtensionDir +/+ "ColorTheme.scd");
		}
	}

	*setTheme { | symbol |
		if (symbol == currentTheme) { ^this };
		if (Document.themes.at(symbol).isNil) {
			postf("Could not find Document theme named %\n", symbol);
			postf("These are the current themes: %\n", Document.themes.keys);
			^this;
		};
		currentTheme = symbol;
		Document.postColor = Document.themes[currentTheme][\postColor];
		this.activate;
		Document.openDocuments do: _.colorizeIfAppropriate;
	}

	*resetToSCdefault {
		this setTheme: \default;
		Document.postColor = Document.theme[\postColor];
		this.deactivate;
	}

	*activate {
		NotificationCenter.register(Panes, \docToFront, this, { | doc |
			doc.colorizeIfAppropriate;
		});
	}
	
	*deactivate {
		NotificationCenter.unregister(Panes, \docToFront, this);
	}

	*createDefaultCustomTheme {
		Document.themes[\pinkString] = Document.themes[\default].copy;
//		Document.themes[\pinkString][\textColor] = Color(0.4, 0.4, 0.5);
//		Document.themes[\pinkString][\textColor] = Color(0.6, 0.6, 0.65);
		Document.themes[\pinkString][\textColor] = Color(0, 0, 0, 1);
		Document.themes[\pinkString][\stringColor] = Color(0.9, 0.1, 0.6);
		Document.themes[\pinkString][\numberColor] = Color(0.7, 0.2, 0, 1);
		Document.themes[\pinkString][\classColor] = Color(0.1, 0.6, 0.9);
		Document.themes[\pinkString][\commentColor] = Color(0.9, 0.5, 0.3, 0.99);

		// also add background color and post color:
		Document.themes[\pinkString][\background] = Color(0.2, 0.2, 0.2, 1);
		Document.themes[\pinkString][\postColor] = Color(0.6, 0.6, 0.6);
		Document.themes[\default][\background] = Color.white;
		Document.themes[\default][\postColor] = Color.black;
		defaultCustomTheme = \pinkString;
	}
}
