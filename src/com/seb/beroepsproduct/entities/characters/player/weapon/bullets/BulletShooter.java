package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;

public class BulletShooter extends EntitySpawner {

	private int nBullets;
	private Character character;

	public BulletShooter(Character character, long reloadSpeed) {
		super(reloadSpeed);
		this.character = character;
		if (character instanceof Player) {
			var player = (Player) character;
			this.nBullets = player.getWeaponLevel();
		}
	}

	@Override
	protected void spawnEntities() {
		if (character instanceof Player) {
			var player = (Player) character;
			this.nBullets = player.getWeaponLevel();
			if (player.isShooting()) {
				for (int i = 0; i < nBullets; i++) {
					spawn(new Bullet(player, new Coordinate2D(player.getAnchorLocation()), 15, i));
				}
			}
		}

		if (character instanceof Robot) {
			var robot = (Robot) character;
			var nBullets = 4 + Math.floor(robot.getEnemyLevel() / 2); // elke twee levels een kogel extra

			if (robot.isVisible()) {
				for (int i = 0; i < nBullets; i++) {
					spawn(new Bullet(robot, new Coordinate2D(robot.getAnchorLocation()), 10, i));
				}
			}
		}
	}

}
