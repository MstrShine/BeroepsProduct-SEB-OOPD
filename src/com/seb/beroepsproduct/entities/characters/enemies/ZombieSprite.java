package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class ZombieSprite extends DynamicSpriteEntity {

	public ZombieSprite(String resource, Coordinate2D initialLocation, Size size, int rows, int columns) {
		super(resource, initialLocation, size, rows, columns);
		setAutoCycle(200);
		setOpacity(0.4);
	}
}
