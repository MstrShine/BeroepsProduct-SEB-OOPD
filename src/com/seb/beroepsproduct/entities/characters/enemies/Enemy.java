package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.bullets.Bullet;
import com.seb.beroepsproduct.entities.characters.enemies.health.EnemyHealthText;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.scenes.GameScreen;

/**
 * The super class of an enemy that extends the {@link Character} class
 */
public abstract class Enemy extends Character {

	protected double enemyLevel;
	protected Player player;	
	protected EnemyHealthText enemyHealthText;

	/**
	 * Creates an {@link Enemy} to place on the {@link GameScreen}
	 * @param location Location on the scene
	 * @param player The current {@link Player}
	 * @param health The current health and the max health of the {@link Enemy}
	 * @param gameScreen The scene where the {@link Enemy} is placed
	 */
	public Enemy(Coordinate2D location, Player player, int health, GameScreen gameScreen) {
		super(location, health, gameScreen);
		this.player = player;
		setEnemyLevel();
	}

	/**
	 * Creates an {@link EnemyTimer} for handling {@link Enemy} behaviour
	 */
	@Override
	public void setupTimers() {
		addTimer(new EnemyTimer(this, this.player, 50, gameScreen));
	}

	/**
	 * Handles collision with {@link Bullet}s of the {@link Player}
	 */
	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Bullet) {
			var bullet = (Bullet) collidingObject;
			if (bullet.getCharacter() instanceof Player) {
				hit(bullet.getDamage());
			}
		}
	}
	
	/**
	 * Gets current level of {@link enemy}
	 * 
	 * @return current level of the {@link Enemy}
	 */
	public final double getEnemyLevel() {
		return enemyLevel;
	}

	/**
	 * Sets level of {@link Enemy} by one plus dividing the current level with three.
	 * So every three levels an {@link Enemy} gets stronger
	 */
	public final void setEnemyLevel() {
		this.enemyLevel = 1 + Math.floor(gameScreen.getLevel() / 3);
	}

	/**
	 * Get the speed of the {@link Enemy}
	 * @return The current speed of {@link Enemy}
	 */
	public final double getEnemySpeed() {
		return this.speed;
	}
	
	/**
	 * Get the health text of the {@link Enemy}
	 * @return The current health text of the {@link Enemy}
	 */
	public final EnemyHealthText getEnemyHealthText() {
		return this.enemyHealthText;
	}
}
