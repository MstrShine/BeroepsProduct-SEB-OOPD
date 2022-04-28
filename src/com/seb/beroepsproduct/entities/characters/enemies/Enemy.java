package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.scenes.GameScreen;

public abstract class Enemy extends Character implements Collided, TimerContainer {

	protected int damage;
	protected double enemyLevel;
	protected Player player;

	public Enemy(Coordinate2D location, Size size, Player player, int health, int damage, GameScreen screen) {
		super(location, size, health, screen);
		this.player = player;
		this.damage = damage;
		setEnemyLevel();
	}

	public int getDamage() {
		return damage;
	}

	protected void createEnemyTimer() {
		addTimer(new EnemyTimer(this, this.player, 50, screen));
	}

	/**
	 * Gets current level of {@link enemy}
	 * 
	 * @return current level
	 */
	public double getEnemyLevel() {
		return enemyLevel;
	}

	/**
	 * Sets level of {@link Enemy} by one plus dividing current score with 200
	 */
	public void setEnemyLevel() {
		this.enemyLevel = 1 + Math.floor(screen.getLevel() / 3);
	}

	public double getEnemySpeed() {
		return this.speed;
	}
}
