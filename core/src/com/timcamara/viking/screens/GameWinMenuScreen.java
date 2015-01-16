package com.timcamara.viking.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.timcamara.viking.VikingGame;

public class GameWinMenuScreen extends MenuScreen {

    public GameWinMenuScreen(VikingGame game) {
		super(game);
		
		createLabel("You Win!");

        table.row();

        createButton("Play Again", new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                VikingGame.print("play again button pressed");

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
