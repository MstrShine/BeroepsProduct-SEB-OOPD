package com.seb.beroepsproduct.entities;

import com.github.hanyaeger.api.Timer;

public class ScoreUpdater extends Timer {

	private scoreTextEntity entity;

	protected ScoreUpdater(scoreTextEntity entity, long intervalInMs) {
		super(intervalInMs);
		this.entity = entity;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		entity.setScoreText();
	}

}
