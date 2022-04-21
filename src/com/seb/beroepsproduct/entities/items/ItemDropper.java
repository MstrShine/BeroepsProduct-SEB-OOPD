package com.seb.beroepsproduct.entities.items;

import java.util.Random;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class ItemDropper extends EntitySpawner {
	private Robot robot;
	private boolean itemDropped;
	private Player player;

	public ItemDropper(Player player, Robot rbt, long intervalInMs) {
		super(intervalInMs);
		this.robot = rbt;
		this.player = player;
		itemDropped = false;
	}

	@Override
	protected void spawnEntities() {
		if (!robot.isVisible() && !itemDropped) {
			var randomNumber = Math.random();

			boolean itemVisible = true;
			var tempLoc = new Coordinate2D(robot.getAnchorLocation().getX() - 50,
					robot.getAnchorLocation().getY() - 50);

			if (randomNumber < 0.5) {
				itemVisible = false;
			}

			if (randomNumber < 0.70) { // ~10% kans op key
				System.out.println("randomNumber for key: " + randomNumber);
				spawn(new KeyItem("sprites/keyGif.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber < 0.80) { // ~10% kans op max health
				System.out.println("randomNumber for maxH: " + randomNumber);
				spawn(new MaxHealthItem("sprites/maxHealth.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber < 0.90) { // ~10% kans op health
				System.out.println("randomNumber for Health: " + randomNumber);
				spawn(new HealthItem("sprites/health.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber <= 1) { // ~10% kans op weapon upgrade
				System.out.println("randomNumber for weapon: " + randomNumber);
				spawn(new WeaponItem("sprites/gunUpgrade.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			}
		}
	}

	public void setItemDropped(boolean itemDropped) {
		this.itemDropped = itemDropped;
	}

}
