package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Timer;

public class ItemTimer extends Timer {

	private Item item;
	// private HealthItem healthItem;

	protected ItemTimer(Item item, long intervalInMs) {
		super(intervalInMs);
		this.item = item;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		item.itemDropper.setItemDropped(false);
		item.remove();
	}

}
