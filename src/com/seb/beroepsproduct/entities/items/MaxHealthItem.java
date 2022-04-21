package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletTimer;

public class MaxHealthItem extends Item{

	protected ItemDropper itemDropper;

	protected MaxHealthItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Player && isVisible()) {
			var newMaxHealth = player.getMaxHealth() + 1;
			player.setMaxHealth(newMaxHealth);
			//System.out.println("" + player.getMaxHealth());
			setVisible(false);
		}

	}

}
