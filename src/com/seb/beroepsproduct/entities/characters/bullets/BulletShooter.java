package com.seb.beroepsproduct.entities.characters.bullets;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.enemies.fireball.Fireball;

/**
 * A {@link Bullet} spawner for a {@link Character}
 */
public class BulletShooter extends EntitySpawner {

	private int nBullets;
	private Character character;

	/**
	 * Creates a {@link BulletShooter} for a {@link Character}
	 * 
	 * @param character   The {@link Character} that will shoot the {@link Bullet}s
	 * @param reloadSpeed The interval in milliseconds of {@link Bullet} spawning
	 */
	public BulletShooter(Character character, long reloadSpeed) {
		super(reloadSpeed);
		this.character = character;
	}

	@Override
	public void spawnEntities() {
		if (character instanceof Player) {
			var player = (Player) character;
			this.nBullets = player.getWeaponLevel();
			
			if (player.isShooting()) {
				for (int i = 0; i < nBullets; i++) {
					spawn(new Bullet(player, player.calcGunLocation(), 12, i));
				}
			}
		}

		if (character instanceof Fireball) {
			var robot = (Fireball) character;
			this.nBullets = (int) Math.floor(4 + Math.floor(robot.getEnemyLevel() / 2)); // Every two levels a bullet extra

			if (robot.isVisible()) {
				for (int i = 0; i < nBullets; i++) {
					spawn(new Bullet(robot, new Coordinate2D(robot.getAnchorLocation()), 8, i));
				}
			}
		}
	}
}
