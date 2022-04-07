package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;

public abstract class Enemy extends Character {

	public Enemy(String path, Coordinate2D location ,int health) {
		super(path, location, health);
	}

}
