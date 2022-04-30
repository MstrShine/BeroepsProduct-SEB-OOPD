package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * An {@link Item} that gives the {@link Player} the ability to go to the next
 * level
 */
public class KeyItem extends Item {

	/**
	 * Creates a {@link KeyItem} on the scene
	 * @param resource        The path to the sprite location on the PC
	 * @param initialLocation The location on the scene
	 * @param itemDropper     An {@link ItemDropper} to set the visibility and
	 *                        handling different item drops
	 * @param visible         Sets the visibility of the {@link KeyItem}
	 * @param player          The {@link Player} that could take the {@link KeyItem}
	 */
	public KeyItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}

	/**
	 * If {@link Player} collides with a {@link KeyItem} the player boolean hasKey is set to true
	 */
	@Override
	public void onCollision(Collider collidingObject) {
		if (collisionCheckPlayer(collidingObject)) {
			player.setPlayerHasKey(true);
			setVisible(false);
		}
	}
}
