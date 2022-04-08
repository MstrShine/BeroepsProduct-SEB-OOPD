package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public abstract class Enemy extends Character {

	public Enemy(String path, Coordinate2D location, Size size, int health) {
		super(path, location, size, health);
	}

}
