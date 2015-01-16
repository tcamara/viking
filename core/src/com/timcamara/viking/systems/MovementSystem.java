package com.timcamara.viking.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.timcamara.viking.components.PositionComponent;
import com.timcamara.viking.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {
	private Vector2 tmp = new Vector2();
	
	private ComponentMapper<PositionComponent> position_mapper;
	private ComponentMapper<VelocityComponent> velocity_mapper;
	
	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Family.getFor(PositionComponent.class, VelocityComponent.class));
		
		position_mapper = ComponentMapper.getFor(PositionComponent.class);
		velocity_mapper = ComponentMapper.getFor(VelocityComponent.class);
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		PositionComponent position_component = position_mapper.get(entity);
		VelocityComponent velocity_component = velocity_mapper.get(entity);
		
		tmp.set(velocity_component.accel).scl(deltaTime);
		velocity_component.speed.add(tmp);
		
		tmp.set(velocity_component.speed).scl(deltaTime);
		position_component.position.add(tmp.x, tmp.y);
	}
}
