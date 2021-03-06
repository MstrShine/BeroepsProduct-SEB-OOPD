package com.seb.beroepsproduct.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

/**
 * A simple way to create a sprite to use in a scene
 */
public class SimpleSprite extends SpriteEntity {

	/**
	 * Creates a simple sprite to use in scene
	 * 
	 * @param resource        Location on PC
	 * @param initialLocation Location to spawn sprite in scene
	 * @param rotation        Sets rotation of sprite
	 * @param size            Sets size of image in scene
	 */
	public SimpleSprite(String resource, Coordinate2D initialLocation, int rotation, Size size) {
		super(resource, initialLocation, size);
		setRotate(rotation);
	}
}