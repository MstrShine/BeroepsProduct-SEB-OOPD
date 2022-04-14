package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class EnemyTimer extends Timer {
	private Enemy enemy;
	private Player player;
	private long previousTimestamp;

	protected EnemyTimer(Enemy enemy, Player player, long intervalInMs) {
		super(intervalInMs);
		this.enemy = enemy;
		this.player = player;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
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
