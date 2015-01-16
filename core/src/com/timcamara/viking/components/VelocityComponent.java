package com.timcamara.viking.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent extends Component {
	public Vector2 speed;
	public Vector2 accel;
	
	public VelocityComponent(Vector2 speed, Vector2 accel) {
		this.speed     = speed;
		this.accel     = accel;
	}
}
