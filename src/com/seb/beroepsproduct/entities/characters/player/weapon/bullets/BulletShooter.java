package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import java.util.Random;
import java.util.Set;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;

import javafx.scene.input.KeyCode;

public class BulletShooter extends EntitySpawner {

	private int nBullets;
	private Character character;

	public BulletShooter(Character character, long reloadSpeed) {
		super(reloadSpeed);
		this.character = character;
		if (character instanceof Player) {
			var player = (Player) character;
			this.nBullets = player.getPlayerLevel();
		}
	}

	@Override
	protected void spawnEntities() {
		if (character instanceof Player) {
			var player = (Player) character;
			if (player.isShooting()) {
				for (int i = 0; i < nBullets; i++) {
					spawn(new Bullet(player, new Coordinate2D(player.getAnchorLocation()), 15, i));
				}
			}
		}

		if (character instanceof Robot) {
			var robot = (Robot) character;
			var nBullets = 3 + robot.getLevel();
			
			if (robot.isVisible()) {
				for (int i = 0; i < nBullets; i++) {
					spawn(new Bullet(robot, new Coordinate2D(robot.getAnchorLocation()), 10, i));
				}
			}
		}

	}
}
