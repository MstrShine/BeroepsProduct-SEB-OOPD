package com.seb.beroepsproduct.entities.obstacles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

/**
 * A Toxicgas {@link Obstacle} that does damage to the {@link Player}
 */
public class Toxic extends Obstacle {

	/**
	 * Creates a toxicgas {@link Obstacle} 
	 * @param initialLocation The location on the scene
	 * @param size The {@link Size} of the sprite on the scene
	 */
	public Toxic(Coordinate2D initialLocation, Size size) {
		super("sprites/toxic.gif", initialLocation, size, 2, 3);
		setOpacity(0.6);
		setAutoCycle(500);
	}
}
