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

		// TODO Auto-generated constructor stub
	}

	@Override
	protected void spawnEntities() {
		// spawn(new bullet(randomLocation(), 2) );
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
			if (robot.isVisible()) {
				for (int i = 0; i < 9; i++) {
					//var bulletOrigin = new Coordinate2D( robot.getAnchorLocation().getX()+100, robot.getAnchorLocation().getY());
					spawn(new Bullet(robot, new Coordinate2D(robot.getAnchorLocation()), 10, i));
					//spawn(new Bullet(robot, bulletOrigin, 10, i));
				}
			}
		}

	}

	// private Coordinate2D randomLocation() {
	// double x = new Random().nextInt((int) 1600);
	// double y = new Random().nextInt((int) 800);
	// return new Coordinate2D(x, y);
	// }

}
