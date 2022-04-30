package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.entities.characters.enemies.fireball.Fireball;
import com.seb.beroepsproduct.entities.characters.enemies.zombie.Zombie;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.scenes.GameScreen;

/**
 * A {@link Timer} for handling {@link Enemy} behaviour on the game screen
 */
public class EnemyTimer extends Timer {
	
	private Enemy enemy;
	private Player player;
	private long previousTimestamp;
	private long respawnTimestamp;
	private GameScreen gameScreen;

	/**
	 * Initialises timer to handle behaviour of the {@link Enemy}
	 * @param enemy The {@link Enemy} of which the timer handles the behaviour of
	 * @param player The {@link Player} to give enemy knowledge of his position
	 * @param intervalInMs The interval in milliseconds to change behaviour of {@link Enemy}
	 * @param gameScreen The screen where the {@link Player} and the {@link Enemy} are
	 */
	public EnemyTimer(Enemy enemy, Player player, long intervalInMs, GameScreen gameScreen) {
		super(intervalInMs);
		this.enemy = enemy;
		this.player = player;
		this.gameScreen = gameScreen;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		respawn(timestamp);
		if (enemy instanceof Fireball) {
			if (previousTimestamp == 0) {
				previousTimestamp = timestamp;
			} else {
				if (timestamp - previousTimestamp > 7000000000d) {
					changeDirection();
					previousTimestamp = timestamp;
				}
			}
		}
		if (enemy instanceof Zombie) {
			var zombie = (Zombie) enemy;
			zombie.setRotate(zombie.angleTo(player) - 180);
			zombie.setDirection(zombie.angleTo(player));
			zombie.setMotion(zombie.getEnemySpeed(), zombie.getDirection());
		}
	}

	private void respawn(long timestamp) {
		if (!enemy.isVisible()) {
			if (respawnTimestamp == 0) {
				respawnTimestamp = timestamp;
			} else {
				if (timestamp - respawnTimestamp > 5000000000d) {
					enemy.setAnchorLocation(gameScreen.pickEnemyLocation(player));
					
					// Reseting enemy
					enemy.setVisible(true);
					enemy.setHealth(enemy.getMaxHealth());
					enemy.getEnemyHealthText().updateText();
					respawnTimestamp = 0;
				}
			}
		}
	}

	private void changeDirection() {
		if (enemy instanceof Fireball) {
			if (enemy.getDirection() == 270) {
				enemy.setDirection(90);
			} else {
				enemy.setDirection(270);
			}
		}
	}
}
