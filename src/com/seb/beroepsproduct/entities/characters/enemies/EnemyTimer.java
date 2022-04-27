package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.enemies.zombie.Zombie;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.scenes.GameScreen;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.YaegerScene;

public class EnemyTimer extends Timer {
	private Enemy enemy;
	private Player player;
	private long previousTimestamp;
	private long respawnTimestamp;
	private GameScreen screen;

	protected EnemyTimer(Enemy enemy, Player player, long intervalInMs, GameScreen screen) {
		super(intervalInMs);
		this.enemy = enemy;
		this.player = player;
		this.screen = screen;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		respawn(timestamp);
		if (enemy instanceof Robot) {
			if (previousTimestamp == 0) {
				previousTimestamp = timestamp;
			} else {
				if (timestamp - previousTimestamp > 7000000000d) {
					changeDirection();
					previousTimestamp = timestamp;
				}
			}
		}
		if(enemy instanceof Zombie) {
			var zombie = (Zombie)enemy;
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
					// enemy.setAnchorLocation(new Coordinate2D(Math.random() * 1200,Math.random() *
					// 700));
					enemy.setAnchorLocation(screen.pickEnemyLocation(player, screen.obstacles));
					// TODO
					// hier reset ik de enemy met andere stats aan de hand van level
					enemy.setVisible(true);
					enemy.setHealth(enemy.getMaxHealth());
					enemy.text.update();
					respawnTimestamp = 0;
				}
			}
		}
	}

	private void changeDirection() {
		if (enemy instanceof Robot) {
			if (enemy.getDirection() == 270) {
				enemy.setDirection(90);
			} else {
				enemy.setDirection(270);
			}
		}
	}
}
