package com.seb.beroepsproduct.entities.obstacles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

/**
 * A rock {@link Obstacle}
 */
public class Rock extends Obstacle {

	/**
	 * Creates a rock {@link Obstacle} for placing in the game scene
	 * @param initialLocation The location on the scene
	 * @param size The {@link Size} of the sprite on the scene
	 */
	public Rock(Coordinate2D initialLocation, Size size) {
		super("sprites/rock.png", initialLocation, size, 1, 1);
	}
}
