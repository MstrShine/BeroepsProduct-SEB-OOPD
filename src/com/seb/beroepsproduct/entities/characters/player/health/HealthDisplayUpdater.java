package com.seb.beroepsproduct.entities.characters.player.health;

import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * An updater for the {@link HealthDisplay} so that the correct health of the {@link Player} is displayed
 */
public class HealthDisplayUpdater extends Timer {

	private HealthDisplay healthDisplay;
	private Player player;

	/**
	 * Initialises the updater on a interval for a {@link Player}
	 * @param healthDisplay The {@link HealthDisplay} on the scene that needs updating
	 * @param intervalInMs The interval in milliseconds for checking
	 * @param player The {@link Player} of which health needs to displayed
	 */
	public HealthDisplayUpdater(HealthDisplay healthDisplay, long intervalInMs, Player player) {
		super(intervalInMs);
		this.player = player;
		this.healthDisplay = healthDisplay;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		healthDisplay.clearHeartList();

		for (int i = 0; i < healthDisplay.getHeartList().size(); i++) {
			if ((i + 1) <= player.getHealth()) {
				healthDisplay.getHeartList().get(i).setCurrentFrameIndex(0);
			} else {
				healthDisplay.getHeartList().get(i).setCurrentFrameIndex(1);
			}
		}
	}
}
