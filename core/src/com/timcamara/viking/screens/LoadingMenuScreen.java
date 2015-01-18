package com.timcamara.viking.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.timcamara.viking.VikingGame;

public class LoadingMenuScreen extends MenuScreen {
	Label progress;
	int   percent = 0;
	
	public LoadingMenuScreen(VikingGame game) {
    	super(game);
    	
		createLabel("Loading");
		
		table.row();
		
		progress = createLabel("0%");
		
		// Queue up main asset loading
		game.assets.load_assets_main();
	}
	
	public void render(float delta) {
		super.render(delta);
		
		// Change loading percentage
		percent = (int) (game.assets.asset_manager.getProgress() * 100);
		progress.setText(percent + "%");
		
		// Load assets until complete, then switch to the title screen
		if(game.assets.update()) {
			// Finish off loading of main assets
			game.assets.load_assets_main_finished();
			
			// Go to title screen
			game.setScreen(game.title_screen);
		}
	}
}
