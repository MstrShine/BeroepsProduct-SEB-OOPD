package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.fireball.Fireball;
import com.seb.beroepsproduct.entities.characters.enemies.zombie.Zombie;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.characters.Character;

import javafx.scene.paint.Color;

public class Bullet extends DynamicCircleEntity implements TimerContainer, Collider, Collided {

	private int damage;
	private Character shooter;

	protected Bullet(Character shooter, Coordinate2D initialLocation, double speed, int bulletOffset) {
		super(initialLocation);
		this.shooter = shooter;

		if (shooter instanceof Player) {
			setRadius(8);
			setFill(Color.YELLOW);
			setSpeed(speed);

			var player = (Player) shooter;
			var totalBulletAngle = 15 * (player.getWeaponLevel() - 1);
			var direction = player.getDirectionPlayer() - (totalBulletAngle / 2) + (bulletOffset * 15);
			setDirection(direction + 90);
			setupTimers();
			this.damage = (int) Math.floor((3.5 + (Math.floor((int) player.getWeaponLevel() / 2)) *0.5));

		}

		if (shooter instanceof Fireball) {
			setRadius(8);
			setFill(Color.RED);
			setSpeed(speed);

			var robot = (Fireball) shooter;
			var nBullets = 4 + Math.floor(robot.getEnemyLevel() / 2); // elke twee levels een kogel extra
			setSpeed(8 + (robot.getEnemyLevel() * 0.5)); // nu schiet robot harder per level
			setDirection(bulletOffset * 360 / nBullets);
			setupTimers();
			this.damage = 10;
		}
	}

	@Override
	public void setupTimers() {
		var bullettimer = new BulletTimer(this, 500);
		addTimer(bullettimer);
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Rock) {
			remove();
		}
		if (shooter instanceof Player) {
			if (collidingObject instanceof Enemy && ((Enemy) collidingObject).isVisible()) {
				if(collidingObject instanceof Zombie) {
					var zombie = (Zombie)collidingObject;
					zombie.hit(damage);
				}
				if(collidingObject instanceof Fireball) {
					var robot = (Fireball)collidingObject;
					robot.hit(damage);
				}
				remove();
			}
		} else if (shooter instanceof Enemy) {
			if (collidingObject instanceof Player) {
				remove();
			}
		}
	}

	public int getDamage() {
		return this.damage;
	}

	public Character getCharacter() {
		return this.shooter;
	}

}
