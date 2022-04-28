package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.seb.beroepsproduct.entities.characters.health.CharacterHealthText;
import com.seb.beroepsproduct.scenes.GameScreen;

public abstract class Character extends DynamicCompositeEntity implements Collided, Collider {

	protected int health;
	protected int maxHealth;
	protected double speed;

	protected GameScreen screen;
	public CharacterHealthText text;

	public Character(Coordinate2D location, Size size, int health, GameScreen screen) {
		super(location);
		this.health = health;
		this.maxHealth = health;
		this.screen = screen;
	}

	protected void die() {
		this.setVisible(false);
	}

	protected void move(double direction) {
		setMotion(this.speed, direction);
	}

	protected void move(Direction direction) {
		setMotion(this.speed, direction);
	}

	public void hit(int damage) {
		this.health -= damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return this.health;
	}

	public String getHealthString() {
		return "" + this.health;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxHealth() {
		return this.maxHealth;
	}

	public String getMaxHealthString() {
		return "" + maxHealth;
	}
}
