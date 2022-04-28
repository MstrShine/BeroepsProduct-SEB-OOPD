package com.seb.beroepsproduct.entities.obstacles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class Toxic extends Obstacle {

	public Toxic(Coordinate2D initialLocation, Size size) {
		super("sprites/toxic.gif", initialLocation, size, 2, 3);
		setOpacity(0.6);
		setAutoCycle(500);
	}

}
