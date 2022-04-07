package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Character extends DynamicSpriteEntity implements ICharacter{
	
	protected int health;
	
	public Character(String path, Coordinate2D location, int health) {
		super(path, location);
		this.health = health;
	}
}
