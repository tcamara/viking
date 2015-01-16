package com.timcamara.viking.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.timcamara.viking VikingGame;

public abstract class MenuScreen extends ScreenAdapter {
    private Stage            stage;
    private VikingGame       game;
    private VikingGame.menus menu;
    private Skin             button_skin;
    private Table            table;

    public MenuScreen(VikingGame game) {
        this.game = game;
        this.menu = menu;

        button_skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        table = createTable();
    }

    public abstract void show;

    public void startGame() {
        game.setScreen(game.game_screen);
        dispose();
    }

    public void continueGame() {
        game.setScreen(game.game_screen);
        dispose();
    }

    public void exitGame() {
        dispose();
        Gdx.app.exit();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        if(VikingGame.dev_mode) {
            Table.drawDebug(stage);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        button_skin.dispose();
        stage.dispose();
    }

    public Table createTable() {
        Table table = new Table();
        table.setFillParent(true);

        if(VikingGame.dev_mode) {
            table.debug();
        }

        return table;
    }

    public Label createLabel(String text) {
        Label label = new Label(text, button_skin);

        table.add(label).padBottom(10);

        return label;
    }

    public void createButton(String text, InputListener inputListener) {
        TextButton button = new TextButton(text, button_skin);

        button.addListener(inputListener);

        table.add(button).pad(10);
    }
}
