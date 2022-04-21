package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Timer;

public class ItemTimer extends Timer{
	
	private MaxHealthItem maxHealthItem;
	//private HealthItem healthItem;

	protected ItemTimer(MaxHealthItem maxHealthItem, long intervalInMs) {
		super(intervalInMs);
		this.maxHealthItem = maxHealthItem;
	}


	@Override
	public void onAnimationUpdate(long timestamp) {
		maxHealthItem.itemDropper.setItemDropped(false);
		maxHealthItem.remove();
		// TODO Auto-generated method stub
		
	}

}
