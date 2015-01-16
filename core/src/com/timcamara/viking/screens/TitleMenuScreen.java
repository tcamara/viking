package com.timcamara.viking.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.timcamara.viking.VikingGame;

public class TitleMenuScreen extends MenuScreen {

    public TitleMenuScreen(VikingGame game) {
		super(game);
		
		createLabel("Viking");

        table.row();

        createButton("Start", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                VikingGame.print("start button pressed");

                startGame();
                return true;
            }
        });
	}
}
