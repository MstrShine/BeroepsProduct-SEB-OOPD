package com.seb.beroepsproduct.entities.obstacles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class Rock extends Obstacle{

	public Rock(Coordinate2D initialLocation, Size size) {
		super("sprites/rock.png", initialLocation, size, 1, 1);
		
	}

}
