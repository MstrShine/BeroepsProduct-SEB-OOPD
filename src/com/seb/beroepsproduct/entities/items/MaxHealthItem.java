package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class MaxHealthItem extends Item {

	public MaxHealthItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collisionCheckPlayer(collidingObject)) {
			var newMaxHealth = player.getMaxHealth() + 1;
			player.setMaxHealth(newMaxHealth);
			setVisible(false);
		}
	}
}
