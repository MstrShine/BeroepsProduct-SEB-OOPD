package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public class WeaponItem extends DynamicSpriteEntity{
	
	protected ItemDropper itemDropper;
	
	protected WeaponItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible) {
		super(resource, initialLocation, new Size(100, 100));
		this.itemDropper = itemDropper;
		setVisible(visible);
	}
}
