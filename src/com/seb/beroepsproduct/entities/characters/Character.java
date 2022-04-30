package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.seb.beroepsproduct.scenes.GameScreen;

/**
 * Simple class to create moving characters on a scene with collision and border
 * watching
 */
public abstract class Character extends DynamicCompositeEntity
		implements Collided, Collider, TimerContainer, SceneBorderTouchingWatcher {

	protected int health;
	protected int maxHealth;
	protected double speed;

	protected GameScreen gameScreen;

	/**
	 * Creates a {@link Character} on the scene
	 * 
	 * @param location   The location on the scene
	 * @param health     The start health and the max health of the
	 *                   {@link Character}
	 * @param gameScreen The game screen where the character is located
	 */
	public Character(Coordinate2D location, int health, GameScreen gameScreen) {
		super(location);
		this.health = health;
		this.maxHealth = health;
		this.gameScreen = gameScreen;
	}

	@Override
	public void notifyBoundaryTouching(SceneBorder border) {
		switch (border) {
		case TOP:
			setAnchorLocationY(getBoundingBox().getHeight() / 1.7);
			break;
		case BOTTOM:
			setAnchorLocationY(getSceneHeight() - (getBoundingBox().getHeight() / 2));
			break;
		case LEFT:
			setAnchorLocationX(getBoundingBox().getWidth() / 1.75);
			break;
		case RIGHT:
			setAnchorLocationX(getSceneWidth() - (getBoundingBox().getWidth() / 1.75));
		default:
			break;
		}
	}
	
	/**
	 * Lets the character 'die', sets visibility to false. For respawning purposes
	 */
	protected final void die() {
		this.setVisible(false);
	}

	/**
	 * Lets the character move with the given speed of the character. Can be
	 * overridden by extending classes
	 * 
	 * @param direction In degrees where 0 is down and 180 is up
	 */
	protected void move(double direction) {
		setMotion(this.speed, direction);
	}

	/**
	 * Lets the character move with the given speed of the character. Can be
	 * overridden by extending classes
	 * 
	 * @param direction Using the {@link Direction} enum for setting direction
	 */
	protected void move(Direction direction) {
		setMotion(this.speed, direction);
	}

	/**
	 * Basic method for handling damage to health for character. Can be overridden
	 * by extending classes
	 * 
	 * @param damage The points of damage that needs to be removed from the health
	 */
	public void hit(int damage) {
		this.health -= damage;
	}

	/**
	 * Basic method of setting health of the {@link Character}. Can be overridden by
	 * extending classes
	 * 
	 * @param health The new health of the {@link Character}
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Gets the current health of the {@link Character}
	 * @return The current health of the {@link Character}
	 */
	public final int getHealth() {
		return this.health;
	}

	/**
	 * Gets the current health of the {@link Character} as text
	 * @return The current health of the {@link Character} as text
	 */
	public final String getHealthString() {
		return "" + this.health;
	}

	/**
	 * Sets the maximum health of the {@link Character}
	 * @param maxHealth The new max health of the {@link Character}
	 */
	public final void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * Gets the current maximum health of the {@link Character}
	 * @return The current maximum health of the {@link Character}
	 */
	public final int getMaxHealth() {
		return this.maxHealth;
	}

	/**
	 * Gets the current maximum health of the {@link Character} as text
	 * @return The current maximum health of the {@link Character} as text
	 */
	public final String getMaxHealthString() {
		return "" + maxHealth;
	}
}
