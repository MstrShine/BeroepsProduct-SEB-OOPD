package com.seb.beroepsproduct.entities.name;

import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.scenes.EndGameScreen;

/**
 * A updater for {@link NameTextEntity}
 */
public class NameTextUpdater extends Timer {

	private NameTextEntity nameTextEntity;
	private EndGameScreen endGameScreen;

	/**
	 * Creates an updater for the {@link NameTextEntity} on a certain interval in milliseconds
	 * @param nameTextEntity The name text to update
	 * @param intervalInMs The interval in milliseconds for updating
	 * @param endGameScreen The {@link EndGameScreen} to update the {@link NameTextEntity} on
	 */
	public NameTextUpdater(NameTextEntity nameTextEntity, long intervalInMs, EndGameScreen endGameScreen) {
		super(intervalInMs);
		this.nameTextEntity = nameTextEntity;
		this.endGameScreen = endGameScreen;

	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		nameTextEntity.setNameText();
		endGameScreen.updateName();
	}
}
