package com.timcamara.viking;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	public AssetManager             asset_manager;
	public Skin                     skin;
	public TextureAtlas             atlas;
	public TextureAtlas             menu_atlas;
	public TextureAtlas.AtlasRegion water_deep_region;
	public TextureAtlas.AtlasRegion water_shallow_region;
	public TextureAtlas.AtlasRegion beach_region;
	public TextureAtlas.AtlasRegion grass_region;
	public TextureAtlas.AtlasRegion ship_region;
	public TextureAtlas.AtlasRegion gradient_small;
	
	public Assets() {
		asset_manager = new AssetManager();
	}
	
	// Force loads the assets needed for the loading screen
	public void load_assets_loading() {
		// Queue up the skin for loading
		asset_manager.load("ui/uiskin.json", Skin.class);
		
		// Queue up the menu background for the loading screen
		asset_manager.load("images/menu_pack.txt", TextureAtlas.class);
		
		// Force finish updating
		asset_manager.finishLoading();
		
		// Store it!
		skin           = asset_manager.get("ui/uiskin.json", Skin.class);
		menu_atlas     = asset_manager.get("images/menu_pack.txt", TextureAtlas.class);
		gradient_small = menu_atlas.findRegion("gradient_small");
	}
	
	// Loads the main set of assets
	public void load_assets_main() {
		asset_manager.load("images/test.txt", TextureAtlas.class);
		
	}
	
	// Called after the main assets are finished loading to handle assignments
	public void load_assets_main_finished() {
		atlas                = asset_manager.get("images/test.txt", TextureAtlas.class);
		water_deep_region    = atlas.findRegion("water_deep");
		water_shallow_region = atlas.findRegion("water_shallow");
		beach_region         = atlas.findRegion("sand");
		grass_region         = atlas.findRegion("grass");
		ship_region          = atlas.findRegion("ship");
	}
	
	// Wrapper to make it easier to call the update function for the asset manager
	public boolean update() {
		return asset_manager.update();
	}
}
