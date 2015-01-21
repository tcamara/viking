package com.timcamara.viking;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.timcamara.viking.screens.GameLossMenuScreen;
import com.timcamara.viking.screens.GameScreen;
import com.timcamara.viking.screens.GameWinMenuScreen;
import com.timcamara.viking.screens.LevelLossMenuScreen;
import com.timcamara.viking.screens.LevelWinMenuScreen;
import com.timcamara.viking.screens.LoadingMenuScreen;
import com.timcamara.viking.screens.PauseMenuScreen;
import com.timcamara.viking.screens.TitleMenuScreen;

public class VikingGame extends Game {
	// Used for debug output
	public static boolean dev_mode = true;
	
	// Screen dimensions determine the actual pixel size of the game window
	public static int screen_width  = 1280;
	public static int screen_height = 720;
	
	// World dimensions determine the size of the game world in meters?
	public static int world_width      = 100;
	public static int world_height     = 100;
	public static int pixels_per_meter = 64;
	
	// One viewport for menus, one for the game, since they use different camera dimensions
	public FitViewport game_viewport;
	public FitViewport menu_viewport;
	
	// Stage and SpriteBatch are heavy objects, so only instantiate one of them, and then re-use it
	public Stage       stage;
	public SpriteBatch batch;
	
	// Screens
	public LoadingMenuScreen   loading_screen;
	public TitleMenuScreen     title_screen;
	public PauseMenuScreen     pause_screen;
	public LevelWinMenuScreen  level_win_screen;
	public LevelLossMenuScreen level_loss_screen;
	public GameWinMenuScreen   game_win_screen;
	public GameLossMenuScreen  game_loss_screen;
	public GameScreen          game_screen;
	
	// Handles loading and storing all assets
	public Assets assets;
	
	// Builds and maintains game world state
	public World world;
	
	@Override
	public void create () {
		// Initialize the menu viewport with screen coordinates
		menu_viewport = new FitViewport(screen_width, screen_height);
		
		// Initialize the game viewport with world dimensions, and center the camera
		game_viewport = new FitViewport(world_width, world_height);
		game_viewport.apply(true);
		
		// Setup structure for the menu screens
		assets = new Assets();
		batch  = new SpriteBatch();
        stage  = new Stage(menu_viewport, batch);
        
        // Bootstrap the assets for the loading screen
        assets.load_assets_loading();
		
		// Initialize loading screen
        loading_screen = new LoadingMenuScreen(this);
        setScreen(loading_screen);
        
        // Create the world
        world = new World();
        
        // Initialize the other screens
        title_screen      = new TitleMenuScreen(this);
		pause_screen      = new PauseMenuScreen(this);
		level_win_screen  = new LevelWinMenuScreen(this);
		level_loss_screen = new LevelLossMenuScreen(this);
		game_win_screen   = new GameWinMenuScreen(this);
		game_loss_screen  = new GameLossMenuScreen(this);
		game_screen       = new GameScreen(this);
		
		// TODO: load the loading screen, then show that while loading everything else
		
		// Start at the title screen
		setScreen(loading_screen);
	}
	
	public static void print (String str) {
		if(VikingGame.dev_mode) {
            System.out.println(str);
        }
	}
}
