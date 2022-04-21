package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

abstract class Item extends DynamicSpriteEntity implements TimerContainer, Collided {
	
	protected ItemDropper itemDropper;
	protected Player player;
	
	public Item(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible, Player player) {
		super(resource, initialLocation, new Size(100, 100));
		this.itemDropper = itemDropper;
		setVisible(visible);
	}
	
	@Override
	public void setupTimers() {
		var itemTimer = new ItemTimer(this, 8000);
		addTimer(itemTimer);
	}
}
