package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * Handles dropping items if the enemy is killed and has not already dropped an
 * item
 */
public class ItemDropper extends EntitySpawner {

	private Enemy enemy;
	private boolean itemDropped;
	private Player player;

	/**
	 * Initialises a item dropper for an {@link Enemy}
	 * 
	 * @param player       The {@link Player} that could take the {@link Item}
	 * @param enemy        The {@link Enemy} to check
	 * @param intervalInMs The interval in milliseconds for checking
	 */
	public ItemDropper(Player player, Enemy enemy, long intervalInMs) {
		super(intervalInMs);
		this.enemy = enemy;
		this.player = player;
		this.itemDropped = false;
	}

	/**
	 * Spawns {@link Item}, every item has a certain chance to spawn. If item is not
	 * spawned after killing an enemy the item is dropped but invisible and will not
	 * register colliding events.
	 */
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

			if (randomNumber < 0.65) { // ~15% chance
				spawn(new KeyItem("sprites/keyGif.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber < 0.75) { // ~10% chance
				spawn(new MaxHealthItem("sprites/maxHealth.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber < 0.85) { // ~10% chance
				spawn(new HealthItem("sprites/health.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			} else if (randomNumber <= 1) { // ~15% chance
				spawn(new WeaponItem("sprites/gunUpgrade.gif", tempLoc, this, itemVisible, player));
				itemDropped = true;
			}
		}
	}

	/**
	 * Sets if the item has dropped or not
	 * 
	 * @param itemDropped true if dropped otherwise false
	 */
	public void setItemDropped(boolean itemDropped) {
		this.itemDropped = itemDropped;
	}

}
