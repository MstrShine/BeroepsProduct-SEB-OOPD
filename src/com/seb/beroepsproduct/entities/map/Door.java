package com.seb.beroepsproduct.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

/**
 * A simple Door sprite with collision
 */
public class Door extends SpriteEntity implements Collider {

	/**
	 * Creates a door sprite for on a scene
	 * 
	 * @param location Location to spawn sprite in scene
	 * @param size     Sets size of image in scene
	 * @param rotation Sets the rotation of the sprite
	 */
	public Door(Coordinate2D location, Size size, double rotation) {
		super("sprites/door.png", location, size);
		setRotate(rotation);
	}
}
