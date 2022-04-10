package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public abstract class Enemy extends Character {

	protected int damage;
	
	public Enemy(String path, Coordinate2D location, Size size, int health) {
		super(path, location, size, health);
	}

	abstract public int getDamage();
	
}
