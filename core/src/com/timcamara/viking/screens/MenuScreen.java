package com.timcamara.viking.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.timcamara.viking.VikingGame;

public abstract class MenuScreen extends ScreenAdapter {
	protected VikingGame game;
	protected Stage      stage;
	protected Table      table;
    protected Skin       skin;
    
    public MenuScreen(VikingGame viking_game) {
        game  = viking_game;
        stage = game.stage;
        skin  = game.assets.skin;
        
        table = createTable();
    }
    
    @Override
    public void show() {
        // Remove everything on the stage
    	stage.clear();
    	
    	// Add this screen's table back in
    	stage.addActor(table);
    	
    	stage.setViewport(game.menu_viewport);
    	
    	// Make sure the stage is listening to input
    	Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        if(VikingGame.dev_mode) {
            table.debugAll();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    // TODO: Make this not stupid
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
    
    // Creates a table for the menu structure
    // It's like web design circa 1999
    protected Table createTable() {
        Table table = new Table();
        table.setFillParent(true);
        
        if(VikingGame.dev_mode) {
            table.debug();
        }
        
        return table;
    }
    
    // Add a text string to the table, and give it some bottom padding for good measure
    protected Label createLabel(String text) {
        Label label = new Label(text, skin);
        
        table.add(label).padBottom(10);
        
        return label;
    }
    
    // Add a text-based button to the table, and give it some padding for good measure
    protected TextButton createButton(String text, InputListener inputListener) {
        TextButton button = new TextButton(text, skin);
        
        button.addListener(inputListener);
        
        table.add(button).pad(10);
        
        return button;
    }
    
    // Add a background to the table
    protected Drawable createBackground(AtlasRegion region) {
    	Drawable drawable = new TextureRegionDrawable(region);
    	
    	table.background(drawable);
    	
    	return drawable;
    }
}
