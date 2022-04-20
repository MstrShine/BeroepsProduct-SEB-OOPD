package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletTimer;

public class MaxHealthItem extends DynamicSpriteEntity implements TimerContainer{
	
	protected ItemDropper itemDropper;
	
	protected MaxHealthItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper) {
		super(resource, initialLocation, new Size(100,100));
		setCurrentFrameIndex(0);
		this.itemDropper = itemDropper;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setupTimers() {
		var itemTimer = new ItemTimer(this, 8000);
		addTimer(itemTimer);
	}

}
