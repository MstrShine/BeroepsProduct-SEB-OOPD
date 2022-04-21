package com.seb.beroepsproduct.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class HeartSprite extends DynamicSpriteEntity {

	private Player player;

	protected HeartSprite(Coordinate2D initialLocation, Player player, int i, Size size) {
		super("sprites/hearts.png", initialLocation, size, 1, 2);
		if (player.getHealth() >= (i + 1)) {
			setCurrentFrameIndex(0);
		} else {
			setCurrentFrameIndex(1);
		}
		// TODO Auto-generated constructor stub
	}

}
