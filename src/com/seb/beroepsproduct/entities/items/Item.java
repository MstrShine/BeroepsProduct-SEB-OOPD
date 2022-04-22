package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.Character;

abstract class Item extends DynamicSpriteEntity implements TimerContainer, Collided {
	
	protected ItemDropper itemDropper;
	protected Player player;
	
	public Item(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible, Player player) {
		super(resource, initialLocation, new Size(100, 100));
		this.itemDropper = itemDropper;
		this.player = player;
		
		setVisible(visible);
	}
	
	@Override
	public void setupTimers() {
		var itemTimer = new ItemTimer(this, 8000);
		addTimer(itemTimer);
	}
	
	/**
	 * Non-overrideable function to check if {@link Player} is colliding with {@link Item} and item is visible
	 * @param collidingObject object that collided with item
	 * @return true if {@link Collider} is Player and item is visible
	 */
	protected final boolean collisionCheckPlayer(Collider collidingObject) {
		return (collidingObject instanceof Player && isVisible());
	}
}
