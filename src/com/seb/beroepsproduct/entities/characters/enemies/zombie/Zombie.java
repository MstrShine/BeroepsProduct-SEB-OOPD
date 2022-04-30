package com.seb.beroepsproduct.entities.characters.enemies.zombie;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.seb.beroepsproduct.entities.characters.enemies.health.EnemyHealthText;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.scenes.GameScreen;

/**
 * An {@link Enemy} that chases the {@link Player} to kill him
 */
public class Zombie extends Enemy {

	/**
	 * Creates a {@link Zombie} to add to a scene
	 * 
	 * @param location   The location on the sene
	 * @param player     The current {@link Player}
	 * @param health     The current health and the maximum health of the
	 *                   {@link Zombie}
	 * @param gameScreen The {@link GameScreen} where the {@link Zombie} is placed
	 */
	public Zombie(Coordinate2D location, Player player, int health, GameScreen gameScreen) {
		super(location, player, health, gameScreen);
		this.player = player;
		var newSpeed = 0.6 + (getEnemyLevel() * 0.2);
		this.speed = newSpeed < 2.5 ? newSpeed : 2.5;
	}

	/**
	 * Do damage to the {@link Zombie} and updates the {@link EnemyHealthText}. If
	 * the {@link Zombie}'s health is 0 or lower he dies and the {@link Player} gets
	 * points added to his score
	 */
	@Override
	public void hit(int damage) {
		this.health -= damage;
		enemyHealthText.updateText();

		if (this.health <= 0 && this.isVisible()) {
			player.setScore(player.getScore() + 100 + (((int) this.getEnemyLevel() - 1) * 10));
			this.die();
		}
	}

	/**
	 * Sets up sprite of the {@link Zombie} and the {@link EnemyHealthText}
	 */
	@Override
	public void setupEntities() {
		var zombieSprite = new ZombieSprite("sprites/zombie.gif", new Coordinate2D(0,0), new Size(80, 80), 1, 2);
		zombieSprite.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(zombieSprite);
		
		this.enemyHealthText = new EnemyHealthText(this, new Coordinate2D(-40, -60));
		addEntity(this.enemyHealthText);
	}
}
