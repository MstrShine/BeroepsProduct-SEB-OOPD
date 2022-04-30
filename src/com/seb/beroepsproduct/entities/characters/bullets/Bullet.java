package com.seb.beroepsproduct.entities.characters.bullets;

import com.github.hanyaeger.api.AnchorPoint;
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

/**
 * A bullet that can be shot by a {@link BulletShooter}
 */
public class Bullet extends DynamicCircleEntity implements TimerContainer, Collider, Collided {

	private int damage;
	private Character shooter;

	/**
	 * Creates {@link Bullet} that can collide with other objects in the scene
	 * @param shooter         The {@link Character} that has shot the {@link Bullet}
	 * @param initialLocation The start location of the {@link Bullet}
	 * @param speed           The speed of the {@link Bullet}
	 * @param bulletOffset	  The offset of the {@link Bullet} from the previous one
	 */
	public Bullet(Character shooter, Coordinate2D initialLocation, double speed, int bulletOffset) {
		super(initialLocation);
		this.shooter = shooter;
		this.setAnchorPoint(AnchorPoint.CENTER_CENTER);

		if (shooter instanceof Player) {
			setRadius(8);
			setFill(Color.YELLOW);
			setSpeed(speed);

			var player = (Player) shooter;
			var totalBulletAngle = 15 * (player.getWeaponLevel() - 1);
			var direction = player.getBulletDirection() - (totalBulletAngle / 2) + (bulletOffset * 15);
			setDirection(direction + 90);
			setupTimers();
			this.damage = (int) Math.floor((3.5 + (Math.floor((int) player.getWeaponLevel() / 2)) * 0.5));

		}

		if (shooter instanceof Fireball) {
			setRadius(8);
			setFill(Color.RED);
			setSpeed(speed);

			var robot = (Fireball) shooter;
			var nBullets = 4 + Math.floor(robot.getEnemyLevel() / 2); // Every two levels add one bullet to shoot
			setSpeed(8 + (robot.getEnemyLevel() * 0.5)); // Every level the bullet goes faster
			setDirection(bulletOffset * 360 / nBullets);
			setupTimers();
			this.damage = 10;
		}
	}

	/**
	 * Adds a {@link BulletTimer} to the {@link Bullet} for despawning after some time
	 */
	@Override
	public void setupTimers() {
		addTimer(new BulletTimer(this, 500));
	}

	/**
	 * Handles collision with objects, if the bullet hits something it will despawn and do damage to enemies if it hits an {@link Enemy}
	 */
	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Rock) {
			remove();
		}
		
		if (shooter instanceof Player) {
			if (collidingObject instanceof Enemy && ((Enemy) collidingObject).isVisible()) {
				if (collidingObject instanceof Zombie) {
					var zombie = (Zombie) collidingObject;
					zombie.hit(damage);
				}
				if (collidingObject instanceof Fireball) {
					var robot = (Fireball) collidingObject;
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

	/**
	 * Gets the damage of the {@link Bullet}
	 * @return The current damage of the {@link Bullet}
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Gets the {@link Character} that shot the {@link Bullet}
	 * @return The {@link Character} that shot the {@link Bullet}
	 */
	public Character getCharacter() {
		return this.shooter;
	}

}
