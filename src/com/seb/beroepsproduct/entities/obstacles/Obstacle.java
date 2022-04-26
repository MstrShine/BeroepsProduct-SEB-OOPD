package com.seb.beroepsproduct.entities.obstacles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Obstacle extends DynamicSpriteEntity implements Collider{

	public Obstacle(String resource, Coordinate2D initialLocation, Size size, int columns, int rows) {
		super(resource, initialLocation, size, columns, rows);
		if (columns>1) {setAutoCycle(500);}
	}

}
