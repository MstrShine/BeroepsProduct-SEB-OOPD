package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class KeyItem extends Item {

	public KeyItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}

	@Override
	public void setupTimers() {
		var itemTimer = new ItemTimer(this, 60000);
		addTimer(itemTimer);
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collisionCheckPlayer(collidingObject)) {
			player.setPlayerHasKey(true);
			setVisible(false);
		}

	}
}
