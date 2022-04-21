package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.CircleEntity;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.Character;

import javafx.scene.paint.Color;

public class Bullet extends DynamicCircleEntity implements TimerContainer, Collider, Collided {

	private int damage;
	public Character character;
	private Coordinate2D initialLocation;

	protected Bullet(Character character, Coordinate2D initialLocation, double speed, int bulletOffset) {
		super(initialLocation);
		this.character = character;
		if (character instanceof Player) {
			setRadius(8);
			setFill(Color.YELLOW);
			setSpeed(speed);
			var player = (Player) character;
			var totalBulletAngle = 15 * player.getPlayerLevel();
			var direction = player.getDirectionPlayer() - (totalBulletAngle / 2) + ((bulletOffset + 1) * 15);
			setDirection(direction + 90);
			setupTimers();
			// TODO Auto-generated constructor stub
			this.damage = 3;
		}

		if (character instanceof Robot) {
			setRadius(8);
			setFill(Color.RED);
			setSpeed(speed);
			var robot = (Robot) character;
			var nBullets = 3 + robot.getLevel();
			setSpeed(8 + robot.getLevel() * 2); // nu schiet robot harder per level
			// var robot = (Robot) character;
			setDirection(bulletOffset * 360 / nBullets);
			setupTimers();
			this.damage = 10;
		}
	}

	/*
	 * protected double determineRadius(Character character) { double bulletSize =
	 * 30 - (initialLocation.distance(character.getAnchorLocation())/20); if
	 * (bulletSize < 0) {bulletSize = 0;} return bulletSize; }
	 */

	@Override
	public void setupTimers() {
		// TODO Auto-generated method stub
		var bullettimer = new BulletTimer(this, 500);
		addTimer(bullettimer);
	}

	public int getDamage() {
		return this.damage;
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (character instanceof Player) {
			if (collidingObject instanceof Enemy && ((Enemy) collidingObject).isVisible()) {
				remove();
			}
		} else if (character instanceof Enemy) {
			if (collidingObject instanceof Player) {
				remove();
			}
		}
	}

}
