package com.timcamara.viking.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.timcamara.viking VikingGame;

public class LoadingMenuScreen extends MenuScreen {

    @Override
    public void show() {
        // Make sure stage is good to go
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);

        createLabel("Loading");

        // TODO: stuff
    }
}
