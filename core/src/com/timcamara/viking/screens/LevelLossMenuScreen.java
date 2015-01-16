package com.timcamara.viking.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.timcamara.viking.VikingGame;

public class LevelLossMenuScreen extends MenuScreen {

    public LevelLossMenuScreen(VikingGame game) {
		super(game);
		
		createLabel("Level Failed");

        table.row();

        createButton("Retry", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                VikingGame.print("retry button pressed");

                startGame();
                return true;
            }
        });

        table.row();

        createButton("Quit", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                VikingGame.print("quit button pressed");

                exitGame();
                return true;
            }
        });
	}
}
