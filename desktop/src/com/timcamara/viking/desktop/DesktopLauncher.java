package com.timcamara.viking.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.timcamara.viking.VikingGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width  = VikingGame.screen_width;
		config.height = VikingGame.screen_height;
		
		new LwjglApplication(new VikingGame(), config);
	}
}
