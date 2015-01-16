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
import com.timcamara.viking.components.PositionComponent;
import com.timcamara.viking.components.TextureComponent;

public class RenderSystem extends IteratingSystem {
	private Viewport      viewport;
	private SpriteBatch   batch;
	private Array<Entity> renderQueue;
	private Camera        camera;
	static final float PIXELS_TO_METRES = 1.0f / 32.0f;
	
	private ComponentMapper<TextureComponent>  texture_mapper;
	private ComponentMapper<PositionComponent> position_mapper;
	
	@SuppressWarnings("unchecked")
	public RenderSystem(Viewport view) {
		super(Family.getFor(TextureComponent.class, PositionComponent.class));
		
		viewport = view;
		camera = viewport.getCamera();
		
		texture_mapper  = ComponentMapper.getFor(TextureComponent.class);
		position_mapper = ComponentMapper.getFor(PositionComponent.class);
		
		renderQueue = new Array<Entity>();
		
		batch = new SpriteBatch();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		// Start batch
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
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
