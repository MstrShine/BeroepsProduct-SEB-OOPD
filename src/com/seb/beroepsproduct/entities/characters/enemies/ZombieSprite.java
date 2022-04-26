package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class ZombieSprite extends SpriteEntity {

	public ZombieSprite(String resource, Coordinate2D initialLocation, Size size) {
		super(resource, initialLocation, size);
	}
}
