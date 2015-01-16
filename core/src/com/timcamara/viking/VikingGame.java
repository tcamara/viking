package com.timcamara.viking;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.timcamara.viking.screens.GameScreen;
import com.timcamara.viking.screens.MenuScreen;
import com.timcamara.viking.screens.TitleMenuScreen;
import com.timcamara.viking.screens.LoadingMenuScreen;
import com.timcamara.viking.screens.PauseMenuScreen;
import com.timcamara.viking.screens.LevelWinMenuScreen;
import com.timcamara.viking.screens.LevelLossMenuScreen;
import com.timcamara.viking.screens.GameWinMenuScreen;
import com.timcamara.viking.screens.GameLossMenuScreen;

public class VikingGame extends Game {
	// Used for debug output
	public static boolean dev_mode = false;

	// Camera related variables
	public static int         screen_width  = 1280;
	public static int         screen_height = 720;
	public OrthographicCamera camera;
	public FitViewport        viewport;

	// Screens
	public TitleMenuScreen     title_screen;
	public LoadingMenuScreen   loading_screen;
	public PauseMenuScreen     pause_screen;
	public LevelWinMenuScreen  level_win_screen;
	public LevelLossMenuScreen level_loss_screen;
	public GameWinMenuScreen   game_win_screen;
	public GameLossMenuScreen  game_loss_screen;
	public GameScreen          game_screen;

	@Override
	public void create () {
		// Initialize the camera and viewport
		camera   = new OrthographicCamera();
		viewport = new FitViewport(VikingGame.screen_width, VikingGame.screen_height, camera);
		viewport.apply(true);

		// Initialize screens
		title_screen      = new TitleMenuScreen(this);
		loading_screen    = new LoadingMenuScreen(this);
		pause_screen      = new PauseMenuScreen(this);
		level_win_screen  = new LevelWinMenuScreen(this);
		level_loss_screen = new LevelLossMenuScreen(this);
		game_win_screen   = new GameWinMenuScreen(this);
		game_loss_screen  = new GameLossMenuScreen(this);
		game_screen       = new GameScreen(this);

		// Start at the title screen
		setScreen(title_screen);
	}

	public void print (String str) {
		if(VikingGame.dev_mode) {
            System.out.println(str);
        }
	}
}
