package com.seb.beroepsproduct.entities.characters.player.health;

import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class HealthDisplayUpdater extends Timer {

	private HealthDisplay healthDisplay;
	private Player player;

	protected HealthDisplayUpdater(HealthDisplay healthDisplay, long intervalInMs, Player player) {
		super(intervalInMs);
		this.player = player;
		this.healthDisplay = healthDisplay;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		healthDisplay.hearts.clear();
		//healthDisplay.setupEntities();
		
		for (int i = 0; i < healthDisplay.hearts.size(); i++) {
			if ((i + 1) <= player.getHealth()) {
				healthDisplay.hearts.get(i).setCurrentFrameIndex(0);
			} else {
				healthDisplay.hearts.get(i).setCurrentFrameIndex(1);
			}
		}

	}

}
