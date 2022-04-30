package com.seb.beroepsproduct.entities.characters.enemies.zombie;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

/**
 * The sprite of the {@link Zombie}
 */
public class ZombieSprite extends DynamicSpriteEntity {

	/**
	 * Creates the sprite of the {@link Zombie}
	 * @param resource The path to the sprite on the PC
	 * @param initialLocation The location on the scene
	 * @param size The size of the sprite on the scene
	 * @param rows The number of rows the sprite contains
	 * @param columns The number of columns the sprite contains
	 */
	public ZombieSprite(String resource, Coordinate2D initialLocation, Size size, int rows, int columns) {
		super(resource, initialLocation, size, rows, columns);
		setAutoCycle(200);
		setOpacity(0.4);
	}
}
