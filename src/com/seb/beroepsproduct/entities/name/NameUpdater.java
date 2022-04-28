package com.seb.beroepsproduct.entities.name;

import com.github.hanyaeger.api.Timer;
import com.seb.beroepsproduct.scenes.EndGameScreen;

public class NameUpdater extends Timer {

	private NameTextEntity nameTextEntity;
	private EndGameScreen screen;

	protected NameUpdater(NameTextEntity nameTextEntity, long intervalInMs, EndGameScreen screen) {
		super(intervalInMs);
		this.nameTextEntity = nameTextEntity;
		this.screen = screen;

	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		nameTextEntity.setNameText();
		screen.updateName();
	}

}
