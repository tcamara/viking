package com.timcamara.viking.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.timcamara.viking VikingGame;

public class GameLossMenuScreen extends MenuScreen {

    @Override
    public void show() {
        // Make sure stage is good to go
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);

        createLabel("Game Over");

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
