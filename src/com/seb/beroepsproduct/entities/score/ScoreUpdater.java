package com.seb.beroepsproduct.entities.score;

import com.github.hanyaeger.api.Timer;

public class ScoreUpdater extends Timer {

	private scoreTextEntity scoreText;

	protected ScoreUpdater(scoreTextEntity scoreText, long intervalInMs) {
		super(intervalInMs);
		this.scoreText = scoreText;
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
		scoreText.setScoreText();
	}

}
