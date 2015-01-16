package com.timcamara.viking.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.timcamara.viking VikingGame;

public class TitleMenuScreen extends MenuScreen {

    @Override
    public void show() {
        // Make sure stage is good to go
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);

        createLabel("Judged");

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
