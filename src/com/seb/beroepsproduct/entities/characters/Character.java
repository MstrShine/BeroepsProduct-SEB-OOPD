package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Character extends DynamicSpriteEntity implements ICharacter{
	
	protected int health;
	
	public Character(String path, Coordinate2D location, Size size, int health) {
		super(path, location, size);
		this.health = health;
	}
}
