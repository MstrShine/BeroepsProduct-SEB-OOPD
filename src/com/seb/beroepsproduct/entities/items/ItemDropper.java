package com.seb.beroepsproduct.entities.items;

import java.util.Random;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;

public class ItemDropper extends EntitySpawner {
	private Robot robot;
	private boolean itemDropped;

	public ItemDropper(Robot rbt, long intervalInMs) {
		super(intervalInMs);
		this.robot = rbt;
		itemDropped = false;
	}

	@Override
	protected void spawnEntities() {
		if (!robot.isVisible() && !itemDropped) {
			var random = new Random();
			var randomNumber = random.nextInt(1, 101); // lower bound inclusive, higher bound exclusive

			boolean itemVisible;
			var tempLoc = new Coordinate2D(robot.getAnchorLocation().getX() - 50,
					robot.getAnchorLocation().getY() - 50);

			if (randomNumber <= 50) {
				itemVisible = false;
			} else {
				itemVisible = true;
			}

			if (randomNumber >= 1 && randomNumber < 70) { // ~70% kans op key
				spawn(new KeyItem("sprites/keyGif.gif", tempLoc, this, itemVisible));
				itemDropped = true;
			}
			if (randomNumber >= 70 && randomNumber < 80) { // ~10% kans op max health
				spawn(new MaxHealthItem("sprites/maxHealth.gif", tempLoc, this, itemVisible));
				itemDropped = true;
			}
			if (randomNumber >= 80 && randomNumber < 90) { // ~10% kans op health
				spawn(new HealthItem("sprites/health.gif", tempLoc, this, itemVisible));
				itemDropped = true;
			}
			if (randomNumber >= 90 && randomNumber <= 100) { // ~10% kans op weapon upgrade
				spawn(new WeaponItem("sprites/gunUpgrade.gif", tempLoc, this, itemVisible));
				itemDropped = true;
			}
		}
	}

	public void setItemDropped(boolean itemDropped) {
		this.itemDropped = itemDropped;
	}

}
