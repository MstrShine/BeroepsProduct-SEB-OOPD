package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Timer;

/**
 * A {@link Timer} for {@link Item}s to clean them up from the scene
 */
public class ItemTimer extends Timer {

	private Item item;

	/**
	 * Creates a {@link Timer} for cleaning up {@link Item}s on the scene after a certain amount of time
	 * @param item The {@link Item} that needs to despawn after interval
	 * @param intervalInMs The despawn interval in miliseconds
	 */
	public ItemTimer(Item item, long intervalInMs) {
		super(intervalInMs);
		this.item = item;
	}

	/**
	 * Resets item dropped boolean in {@link ItemDropper} and removes entity from the scene
	 */
	@Override
	public void onAnimationUpdate(long timestamp) {
		item.getItemDropper().setItemDropped(false);
		item.remove();
	}
}
