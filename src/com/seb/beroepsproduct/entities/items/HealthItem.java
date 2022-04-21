package com.seb.beroepsproduct.entities.items;

import java.util.Optional;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

import javafx.scene.Node;

public class HealthItem extends Item implements Collided{

	protected Player player;
	
	protected HealthItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible, Player player) {
		super(resource, initialLocation, itemDropper, visible, player);
		this.player = player;
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Player) {
			this.player.setHealth(player.getMaxHealth());
			remove();
		}
		
	}
}