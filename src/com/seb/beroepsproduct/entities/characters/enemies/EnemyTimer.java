package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.github.hanyaeger.api.scenes.DynamicScene;

public class EnemyTimer extends Timer {
	private Enemy enemy;
	private Player player;
	private long previousTimestamp;
	private long respawnTimestamp;

	protected EnemyTimer(Enemy enemy, Player player, long intervalInMs) {
		super(intervalInMs);
		this.enemy = enemy;
		this.player = player;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		respawn(timestamp);
		if (previousTimestamp == 0) {
			previousTimestamp = timestamp;
		} else {
			if (timestamp - previousTimestamp > 5000000000d) {
				changeDirection();
				previousTimestamp = timestamp;
				
			}
		}

		updateSpeed();
		updateDirection();
		//enemy.setMotion(enemy.getSpeed(), enemy.angleTo(player));

	}
	
	private void respawn(long timestamp) {
		if (!enemy.isVisible()) {
			if (respawnTimestamp == 0) {respawnTimestamp = timestamp;}
			else {
				if (timestamp - respawnTimestamp > 5000000000d) {
					enemy.setAnchorLocation(new Coordinate2D(Math.random() * 1200,Math.random() * 700));
					//ik kon hier niet de functies getWidth/height gebruiken...
					enemy.setVisible(true);
					enemy.setHealth(500);
					enemy.text.update();
					respawnTimestamp = 0;
				}
			}
		}
	}
	
	private void changeDirection() {
		if (enemy.getDirection() == 270) {enemy.setDirection(90);}
		else {enemy.setDirection(270);
		}
	}

	private void updateSpeed() {

	}

	private void updateDirection() {

	}

}
