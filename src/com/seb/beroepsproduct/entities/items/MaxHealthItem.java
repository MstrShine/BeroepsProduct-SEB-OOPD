package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * An {@link Item} that adds one max health to the {@link Player}
 */
public class MaxHealthItem extends Item {

	/**
	 * Creates a {@link MaxHealthItem} on the scene
	 * 
	 * @param resource        The path to the sprite location on the PC
	 * @param initialLocation The location on the scene
	 * @param itemDropper     An {@link ItemDropper} to set the visibility and
	 *                        handling different item drops
	 * @param visible         Sets the visibility of the {@link MaxHealthItem}
	 * @param player          The {@link Player} that could take the
	 *                        {@link MaxHealthItem}
	 */
	public MaxHealthItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}

	/**
	 * If {@link Player} collides with a {@link MaxHealthItem} the max health of the player will be increased by one
	 */
	@Override
	public void onCollision(Collider collidingObject) {
		if (collisionCheckPlayer(collidingObject)) {
			var newMaxHealth = player.getMaxHealth() + 1;
			player.setMaxHealth(newMaxHealth);
			setVisible(false);
		}
	}
}
