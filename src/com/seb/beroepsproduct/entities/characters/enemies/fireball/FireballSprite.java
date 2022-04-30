package com.seb.beroepsproduct.entities.characters.enemies.fireball;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

/**
 * The sprite of a {@link Fireball}
 */
public class FireballSprite extends DynamicSpriteEntity {

	/**
	 * Creates the sprite of a {@link Fireball}
	 * @param resource        The path to the sprite on the PC
	 * @param initialLocation The location on the scene
	 * @param size            The size of the sprite on the scene
	 * @param rotationSpeed   The speed at which the sprite rotates
	 */
	public FireballSprite(String resource, Coordinate2D initialLocation, Size size, double rotationSpeed) {
		super(resource, initialLocation, size, 1, 9);
		setOpacity(0.7);
		setAutoCycle(50);
		setRotationSpeed(rotationSpeed);
	}

}
