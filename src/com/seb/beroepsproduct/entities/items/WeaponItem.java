package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * An {@link Item} that upgrades the weapon of the {@link Player}
 */
public class WeaponItem extends Item {

	/**
	 * Creates a {@link WeaponItem} on the scene
	 * 
	 * @param resource        The path to the sprite location on the PC
	 * @param initialLocation The location on the scene
	 * @param itemDropper     An {@link ItemDropper} to set the visibility and
	 *                        handling different item drops
	 * @param visible         Sets the visibility of the {@link WeaponItem}
	 * @param player          The {@link Player} that could take the
	 *                        {@link WeaponItem}
	 */
	public WeaponItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collisionCheckPlayer(collidingObject)) {
			var newWeaponLevel = player.getWeaponLevel() + 1;
			player.setWeaponLevel(newWeaponLevel);
			setVisible(false);
		}
	}
}
