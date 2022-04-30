package com.seb.beroepsproduct.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * Creates a simple lock sprite to show if user can go through the door
 */
public class Lock extends DynamicSpriteEntity implements TimerContainer {

	private Player player;

	/**
	 * Creates a lock sprite for on the scene
	 * 
	 * @param player          The current {@link Player} to check for key
	 * @param resource        Location on PC
	 * @param initialLocation Location to spawn sprite in scene
	 * @param size            Sets size of image in scene
	 */
	public Lock(Player player, String resource, Coordinate2D initialLocation, Size size) {
		super(resource, initialLocation, size);
		this.player = player;
		setVisible(!player.isPlayerHasKey());
	}

	@Override
	public void setupTimers() {
		var lockUpdater = new LockUpdater(this, 500);
		addTimer(lockUpdater);
	}

	public void updateLockVisible() {
		setVisible(!player.isPlayerHasKey());
	}

}
