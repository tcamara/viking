package com.timcamara.viking.screens;

import com.timcamara.viking.VikingGame;

public class LoadingMenuScreen extends MenuScreen {
	
	public LoadingMenuScreen(VikingGame game) {
    	super(game);
    	
		createLabel("Loading");
		
		// TODO: loading screen w/ progress bar
	}
	
	public void render(float delta) {
		super.render(delta);
		
		if(game.asset_manager.update()) {
			// Go to title screen
			game.setScreen(game.title_screen);
		}
	}
}
