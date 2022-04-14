package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import java.util.Random;
import java.util.Set;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.seb.beroepsproduct.entities.characters.player.Player;

import javafx.scene.input.KeyCode;

public class BulletShooter extends EntitySpawner {

	private int nBullets;
	private Player player;
	private boolean shooting;
	
	public BulletShooter(Player player, long reloadSpeed) {
		super(reloadSpeed);
		this.player = player;
		this.nBullets = player.getPlayerLevel();
		shooting = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void spawnEntities() {
		// spawn(new bullet(randomLocation(), 2) );
		if (player.isShooting()) {
			for (int i = 0; i < nBullets; i++) {
				spawn(new Bullet(player, new Coordinate2D(player.getAnchorLocation()), 20, i));
			}
		}
	}


	// private Coordinate2D randomLocation() {
	// double x = new Random().nextInt((int) 1600);
	// double y = new Random().nextInt((int) 800);
	// return new Coordinate2D(x, y);
	// }

}
