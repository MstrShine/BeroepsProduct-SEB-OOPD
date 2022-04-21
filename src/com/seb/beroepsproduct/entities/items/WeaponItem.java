package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class WeaponItem extends Item {

	protected ItemDropper itemDropper;

	protected WeaponItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible, Player player) {
		super(resource, initialLocation, itemDropper, visible, player);

	}

	@Override
	public void onCollision(Collider collidingObject) {
		if(collidingObject instanceof Player && isVisible()) {
			var newWeaponLevel = player.getWeaponLevel() +1;
			player.setWeaponLevel(newWeaponLevel);
			setVisible(false);
		}
		
	}
}
