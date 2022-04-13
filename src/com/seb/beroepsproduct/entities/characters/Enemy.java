package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;

public abstract class Enemy extends Character implements Collided{

	protected int damage;
	
	public Enemy(Coordinate2D location, Size size, int health) {
		super(location, size, health);
	}

	abstract public int getDamage();
	
}
