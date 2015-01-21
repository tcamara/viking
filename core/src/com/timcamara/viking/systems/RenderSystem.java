package com.timcamara.viking.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.timcamara.viking.VikingGame;
import com.timcamara.viking.components.PositionComponent;
import com.timcamara.viking.components.TextureComponent;

public class RenderSystem extends IteratingSystem {
	private VikingGame    game;
	private Viewport      viewport;
	private SpriteBatch   batch;
	private Array<Entity> renderQueue;
	private Camera        camera;
	static final float PIXELS_TO_METRES = 1.0f / 32.0f;
	
	private ComponentMapper<TextureComponent>  texture_mapper;
	private ComponentMapper<PositionComponent> position_mapper;
	
	@SuppressWarnings("unchecked")
	public RenderSystem(VikingGame viking_game) {
		// Only entities that have a TextureComponent and a PositionComponent will be run through this system
		super(Family.getFor(TextureComponent.class, PositionComponent.class));
		
		// Get our render-related objects from the main game object to avoid re-instantiating them
		game     = viking_game;
		viewport = game.game_viewport;
		camera   = viewport.getCamera();
		batch    = game.batch;
		
		// Get our component mappers so we can retrieve objects in an efficient manner
		texture_mapper  = ComponentMapper.getFor(TextureComponent.class);
		position_mapper = ComponentMapper.getFor(PositionComponent.class);
		
		renderQueue = new Array<Entity>();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		// Start batch
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		// Render World
		for(int x = 0; x < game.world.map_heights.length; x++){
			for(int y = 0; y < game.world.map_heights[x].length; y++){
				if(game.world.map_heights[x][y] == 0){
					batch.draw(game.assets.water_deep_region, x * VikingGame.pixels_per_meter, y * VikingGame.pixels_per_meter);
				}
				else if (game.world.map_heights[x][y] == 1){
					batch.draw(game.assets.water_shallow_region, x * VikingGame.pixels_per_meter, y * VikingGame.pixels_per_meter);
				}
				else if (game.world.map_heights[x][y] == 2){
					batch.draw(game.assets.beach_region, x * VikingGame.pixels_per_meter, y * VikingGame.pixels_per_meter);
				}
				else if (game.world.map_heights[x][y] == 3){
					batch.draw(game.assets.grass_region, x * VikingGame.pixels_per_meter, y * VikingGame.pixels_per_meter);
				}
			}
		}
		
		// Process batch items
		for (Entity entity : renderQueue) {
			TextureComponent texture_component   = texture_mapper.get(entity);
			PositionComponent position_component = position_mapper.get(entity);
		
			float width   = texture_component.region.getRegionWidth();
			float height  = texture_component.region.getRegionHeight();
			float originX = width  * 0.5f;
			float originY = height * 0.5f;
			
			batch.draw(texture_component.region,
					   position_component.position.x - originX, position_component.position.y - originY,
					   originX, originY,
					   width, height,
					   1.0f * PIXELS_TO_METRES, 1.0f * PIXELS_TO_METRES,
					   MathUtils.radiansToDegrees * position_component.rotation
			);
		}
		
		// End batch
		batch.end();
		renderQueue.clear();
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}
}
