package com.timcamara.viking;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	
	public static void load_assets_for_loading(AssetManager asset_manager) {
		asset_manager.load("ui/uiskin.json", Skin.class);
		
		// Force finish updating
		asset_manager.finishLoading();
	}
	
	public static void load_assets(AssetManager asset_manager) {
		
	}
}
