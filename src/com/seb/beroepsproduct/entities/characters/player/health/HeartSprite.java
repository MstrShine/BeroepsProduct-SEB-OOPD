package com.seb.beroepsproduct.entities.characters.player.health;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * A simple sprite of a full heart and an empty heart
 */
public class HeartSprite extends DynamicSpriteEntity {

	/**
	 * 
	 * @param initialLocation The location on the scene
	 * @param player The {@link Player} of which the health needs to displayed
	 * @param positionInArray The position in the health array of the {@link Player}
	 * @param size The size of the sprite on the scene
	 */
	public HeartSprite(Coordinate2D initialLocation, Player player, int positionInArray, Size size) {
		super("sprites/hearts.png", initialLocation, size, 1, 2);
		if (player.getHealth() >= (positionInArray + 1)) {
			setCurrentFrameIndex(0);
		} else {
			setCurrentFrameIndex(1);
		}
	}
}
