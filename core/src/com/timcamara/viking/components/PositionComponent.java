package com.timcamara.viking.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends Component {
	public Vector2 position;
	public float   rotation;
	
	public PositionComponent() {
		this(0, 0, 0);
	}
	
	public PositionComponent(Vector2 pos, float rot) {
		position = pos;
		rotation = rot;
	}
	
	public PositionComponent(float x, float y, float rot) {
		position = new Vector2(x, y);
		rotation = rot;
	}
}
 