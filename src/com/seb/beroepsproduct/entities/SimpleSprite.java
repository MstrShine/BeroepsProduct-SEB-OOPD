package com.seb.beroepsproduct.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class SimpleSprite extends SpriteEntity {

	public SimpleSprite(String resource, Coordinate2D initialLocation, int rotation, Size size) {
		super(resource, initialLocation, size);
		setRotate(rotation);
	}
}