
NanoK2Strip {
	var <kontrol;  	// NanoKontrol2 instance to which I belong
	var <index = 0;	// my strip number in the gui from left to right (1 to 8)
	var <>knobProxy, <>sliderProxy, <>knobControl, <>sliderControl;
	var knobProxyMenu, knobControlMenu;
	var knob, knobMin, knobVal, knobMax, knobControlSpec;
	var sliderProxyMenu, sliderControlMenu;
	var slider, sliderMin, sliderVal, sliderMax, sliderControlSpec;
	var <proxyStartButton;
	
	*new { | kontrol, index = 0 |
//		QtGUI.style = \CDE;
		^this.newCopyArgs(kontrol, index).init;
	}
	
	init {
		knobControlSpec = ControlSpec(0, 1);
		sliderControlSpec = ControlSpec(0, 2);
	}

	widgets {
		^VLayout(
			knobProxyMenu = PopUpMenu().font_(Font.default.size_(10))
				.action_({ | me |
					this.setProxy(me.item.asSymbol, 'knobProxy_', knobProxyMenu, knobControlMenu);
				}),
			knobControlMenu = PopUpMenu().font_(Font.default.size_(10))
				.items_([" ", "vol"])
				.action_({ | me |
					knobControl = me.item.asSymbol;
				}),
			HLayout(
				VLayout(
					Button().states_([["ed"]])
						.font_(Font.default.size_(10))
						.action_({ kontrol.editNodeProxySource(knobProxy) }),
					knob = Knob()
						.action_({ | s |
							this.setControl(kontrol.proxySpace[knobProxy],
								knobControlMenu, knobVal, knobControlSpec.map(s.value)
							)
						})
				),
				VLayout(
					knobMax = NumberBox().value_(1)
						.action_({
							knobControlSpec = ControlSpec(knobMin.value, knobMax.value);
						}),
					knobVal = NumberBox(),
					knobMin = NumberBox()
						.action_({
							knobControlSpec = ControlSpec(knobMin.value, knobMax.value);
						})
				)
			),
			HLayout(
				slider = Slider()
					.value_(0.5)
					.action_({ | s |
						this.setControl(kontrol.proxySpace[sliderProxy],
							sliderControlMenu, sliderVal, sliderControlSpec.map(s.value)
						)
					}),
				VLayout(
					sliderMax = NumberBox().value_(2)
						.action_({
							sliderControlSpec = ControlSpec(sliderMin.value, sliderMax.value);
						}),
					sliderVal = NumberBox().value_(1), // no action?
					sliderMin = NumberBox()
						.action_({
							sliderControlSpec = ControlSpec(sliderMin.value, sliderMax.value);
						}),
					Button().states_([["ed"]])
						.font_(Font.default.size_(10))
						.action_({ kontrol.editNodeProxySource(sliderProxy) }),
					proxyStartButton = Button().states_([[">"], ["||"]])
						.font_(Font.default.size_(10))
						.action_({ | me |
							this.perform([\stopProxy, \startProxy][me.value])
						})
				)
			),
			sliderProxyMenu = PopUpMenu().font_(Font.default.size_(10))
				.action_({ | me |
					this.setProxy(me.item.asSymbol, 'sliderProxy_', 
						sliderProxyMenu, sliderControlMenu);
				}),
			sliderControlMenu = PopUpMenu().font_(Font.default.size_(10))
				.items_([" ", "vol"])
				.value_(1)
				.action_({ | me |
					sliderControl = me.item.asSymbol;
				})
		)
	}


	setProxy { | name, setter, proxymenu, controlmenu |
		var proxy, proxyArgs, index;
		this.perform(setter, name);
		index = proxymenu.items.collect(_.asSymbol).indexOf(name);
		proxymenu.value = index;	// does not execute action 
		if (name === ' ') { } {
			proxy = kontrol.proxySpace[name];
			controlmenu.items = (proxy.controlKeys ++ ['vol', 'fadeTime']);
		};
	}

	setControl { | proxy, control, numbox, value |
		numbox.value = value;
		control = control.item.asSymbol;
		switch (control,
			'vol', { proxy.vol = value; },
			'fadeTime', { proxy.fadeTime = value; },
			'nil', { }, 
			' ', { },
			{ proxy.set(control, value); }
		)
	}
					
	setProxies { | proxyList, proxy |
		var myProxy;
		knobProxyMenu.items = proxyList;
		sliderProxyMenu.items = proxyList;
		myProxy = proxyList[index];
		if (myProxy.notNil and: { sliderProxyMenu.value == 0 }) {
			this.setProxy(myProxy, 'sliderProxy_', sliderProxyMenu, sliderControlMenu);
			if (proxy.isMonitoring) { proxyStartButton.value = 1; }
		}
	}

	proxyStarted { | name |
		if (sliderProxyMenu.item.asSymbol === name) {
			proxyStartButton.value = 1;
		}
	}

	proxyStopped { | name |
		if (sliderProxyMenu.item.asSymbol === name) {
			proxyStartButton.value = 0;
		}
	}


	startProxy { sliderProxy !? { kontrol.proxySpace[sliderProxy].play } }

	stopProxy { sliderProxy !? { kontrol.proxySpace[sliderProxy].stop } }

	getPresetData {
		// return data array in form that can be used to load the data back to a preset 
		^[	knobProxyMenu.items, knobProxyMenu.item, knobControlMenu.items, knobControlMenu.item, 
			knob.value, knobMin.value, knobVal.value, knobMax.value,
			sliderProxyMenu.items, sliderProxyMenu.item, 
			sliderControlMenu.items, sliderControlMenu.item,
			slider.value, sliderMin.value, sliderVal.value, sliderMax.value
		]
	}
	
	setPresetData { | data |
		// set preset data from an array created by getPresetData		
	}
}
