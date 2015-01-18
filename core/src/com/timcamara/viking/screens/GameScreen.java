package com.timcamara.viking.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.timcamara.viking.VikingGame;
import com.timcamara.viking.systems.MovementSystem;
import com.timcamara.viking.systems.RenderSystem;

public class GameScreen extends ScreenAdapter {
    private VikingGame      game;
    private Engine          engine;
    private FPSLogger       fps;

    public GameScreen(VikingGame game) {
        this.game = game;

        // Create the engine for the Ashley ECS
        engine = new Engine();
        
        // Set up the systems
        start_systems();

        // Display FPS counter if we're in dev mode
        if(VikingGame.dev_mode) {
            fps = new FPSLogger();
        }
    }
    
    private void start_systems() {
    	engine.addSystem(new RenderSystem(game));
        engine.addSystem(new MovementSystem());
    }
    
    private void pause_systems() {
    	engine.getSystem(RenderSystem.class).setProcessing(false);
		engine.getSystem(MovementSystem.class).setProcessing(false);
    }
    
    private void resume_systems() {
    	engine.getSystem(RenderSystem.class).setProcessing(true);
		engine.getSystem(MovementSystem.class).setProcessing(true);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);

        if(VikingGame.dev_mode) {
            fps.log();
        }
    }
    
    @Override
    public void pause() {
    	pause_systems();
    }
    
    @Override
    public void resume() {
    	resume_systems();
    }
    
    @Override
    public void resize(int width, int height) {
        game.camera.update();
        game.viewport.update(width, height);
    }

    @Override
    public void dispose() {
    	
    }
}
