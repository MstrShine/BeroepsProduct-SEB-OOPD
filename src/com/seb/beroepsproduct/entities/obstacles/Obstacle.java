package com.seb.beroepsproduct.entities.obstacles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

/**
 * A basic class for creating an obstacle
 */
public abstract class Obstacle extends DynamicSpriteEntity implements Collider {

	/**
	 * Creating a basic obstacle
	 * @param resource Path where the sprite is located
	 * @param initialLocation The location on the scene
	 * @param size The {@link Size} of the sprite on the scene
	 * @param columns The number of columns in the sprite
	 * @param rows The number of rows in the sprite
	 */
	public Obstacle(String resource, Coordinate2D initialLocation, Size size, int columns, int rows) {
		super(resource, initialLocation, size, columns, rows);
	}
}
