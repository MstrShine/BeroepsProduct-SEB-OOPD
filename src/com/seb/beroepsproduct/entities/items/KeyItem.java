package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class KeyItem extends Item {

	protected KeyItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible, Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}
	
	public void setupTimers() {
		var itemTimer = new ItemTimer(this, Long.MAX_VALUE);
		addTimer(itemTimer);
	}

	@Override
	public void onCollision(Collider collidingObject) {
		// TODO Auto-generated method stub
		
	}
}
