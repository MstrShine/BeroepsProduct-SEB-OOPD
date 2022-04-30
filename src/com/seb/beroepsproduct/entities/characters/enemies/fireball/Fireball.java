package com.seb.beroepsproduct.entities.characters.enemies.fireball;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.health.EnemyHealthText;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.bullets.Bullet;
import com.seb.beroepsproduct.scenes.GameScreen;

/**
 * An {@link Enemy} that moves horizontally and shoots {@link Bullet}s in a
 * circle like manner
 */
public class Fireball extends Enemy {

	/**
	 * Creates a {@link Fireball} to add to a scene
	 * 
	 * @param location   The location on the sene
	 * @param player     The current {@link Player}
	 * @param health     The current health and the maximum health of the
	 *                   {@link Fireball}
	 * @param gameScreen The {@link GameScreen} where the {@link Fireball} is placed
	 * 
	 */
	public Fireball(Coordinate2D location, Player player, int health, GameScreen gameScreen) {
		super(location, player, health, gameScreen);
		this.player = player;
		setSpeed(0.8 + (getEnemyLevel() * 0.1));
		setDirection(90);
	}

	/**
	 * Do damage to the {@link Fireball} and updates the {@link EnemyHealthText}. If
	 * the {@link Fireball}'s health is 0 or lower he dies and the {@link Player}
	 * gets points added to his score
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
	 * Sets up sprite of the {@link Fireball} and the {@link EnemyHealthText}
	 */
	@Override
	public void setupEntities() {
		double rotationSpeed = -5 + Math.random() * 10;
		var fireballSprite = new FireballSprite("sprites/fire2.gif", new Coordinate2D(0, 0), new Size(80, 80),
				rotationSpeed);
		fireballSprite.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(fireballSprite);
		this.enemyHealthText = new EnemyHealthText(this, new Coordinate2D(-40, -60));
		addEntity(this.enemyHealthText);
	}

}
