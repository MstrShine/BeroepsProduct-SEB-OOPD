package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.scenes.YaegerScene;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletTimer;
import com.seb.beroepsproduct.scenes.GameScreen;

public abstract class Enemy extends Character implements Collided, TimerContainer {

	protected int damage;
	protected double level;
	protected Player player;

	public Enemy(Coordinate2D location, Size size, Player player, int health, int damage, GameScreen screen) {
		super(location, size, health, screen);
		this.player = player;
		this.damage = damage;
		level = 1;
	}
	
	public Enemy(Coordinate2D location, Size size, Player player, int health, int damage, GameScreen screen, int rows, int columns) {
		super(location, size, health, screen, rows, columns);
		this.player = player;
		this.damage = damage;
		level = 1;
	}
	
	public int getDamage() {
		return damage;
	}

	protected void createEnemyTimer() {
		addTimer(new EnemyTimer(this, this.player, 50, screen));
	}

	/**
	 * Gets current level of {@link enemy}
	 * @return current level
	 */
	public double getLevel() {
		return level;
	}

	/**
	 * Sets level of {@link Enemy} by one plus dividing current score with 200
	 */
	public void setLevel() {
		this.level = 1 + Math.floor(player.getScore() / 200);
	}

}
