package com.timcamara.viking.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.timcamara.viking.VikingGame;

public class PauseMenuScreen extends MenuScreen {

    public PauseMenuScreen(VikingGame game) {
		super(game);
		
		createLabel("Paused");

        table.row();

        createButton("Continue", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                VikingGame.print("continue button pressed");

                // TODO: stuff
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
            }
        );
	}
}
