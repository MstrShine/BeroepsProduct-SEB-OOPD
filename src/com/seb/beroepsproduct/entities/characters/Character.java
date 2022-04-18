package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.scenes.GameScreen;

public abstract class Character extends DynamicCompositeEntity implements ICharacter, Collided, Collider {
	
	protected int health;
	
	//protected CharacterHealthText text;
	public CharacterHealthText text;

	public Character(Coordinate2D location, Size size, int health) {
		super(location);
		this.health = health;
	}
	
	protected void Die() {
		//this.remove();
		this.setVisible(false);
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}
	
	
}
