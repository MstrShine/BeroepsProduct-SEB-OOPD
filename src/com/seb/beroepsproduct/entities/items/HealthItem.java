package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * An {@link Item} that restores one health to the {@link Player}
 */
public class HealthItem extends Item {

	/**
	 * Creates a {@link HealthItem} on the scene
	 * 
	 * @param resource        The path to the sprite location on the PC
	 * @param initialLocation The location on the scene
	 * @param itemDropper     An {@link ItemDropper} to set the visibility and
	 *                        handling different item drops
	 * @param visible         Sets the visibility of the {@link HealthItem}
	 * @param player          The {@link Player} that could take the
	 *                        {@link HealthItem}
	 */
	public HealthItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}

	/**
	 * If {@link Player} collides with a {@link HealthItem} and the new health is
	 * not greater than the max health the player will gain one health back
	 */
	@Override
	public void onCollision(Collider collidingObject) {
		if (collisionCheckPlayer(collidingObject)) {
			var newHealth = player.getHealth() + 1;
			if (newHealth <= player.getMaxHealth()) {
				this.player.setHealth(newHealth);
			}
			setVisible(false);
		}
	}
}