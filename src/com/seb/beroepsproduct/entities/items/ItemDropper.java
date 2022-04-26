package com.seb.beroepsproduct.entities.items;

import java.util.Random;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class ItemDropper extends EntitySpawner {
	private Enemy enemy;
	private boolean itemDropped;
	private Player player;

	public ItemDropper(Player player, Enemy enemy, long intervalInMs) {
		super(intervalInMs);
		this.enemy = enemy;
		this.player = player;
		itemDropped = false;
	}

	@Override
	public void spawnEntities() {
		if (!enemy.isVisible() && !itemDropped) {
			var randomNumber = Math.random();

			boolean itemVisible = true;
			var tempLoc = new Coordinate2D(enemy.getAnchorLocation().getX() - 50,
					enemy.getAnchorLocation().getY() - 50);

			if (randomNumber < 0.5) {
				itemVisible = false;
			}

			if (randomNumber < 0.70) { // ~20% kans op key
				spawn(new KeyItem("sprites/keyGif.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber < 0.80) { // ~10% kans op max health
				spawn(new MaxHealthItem("sprites/maxHealth.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber < 0.90) { // ~10% kans op health
				spawn(new HealthItem("sprites/health.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber <= 1) { // ~10% kans op weapon upgrade
				spawn(new WeaponItem("sprites/gunUpgrade.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			}
		}
	}

	public void setItemDropped(boolean itemDropped) {
		this.itemDropped = itemDropped;
	}

}
