package com.seb.beroepsproduct.entities.score;

import com.github.hanyaeger.api.Timer;

/**
 * A updater for {@link ScoreTextEntity} that checks on a certain interval
 */
public class ScoreTextUpdater extends Timer {

	private ScoreTextEntity scoreText;

	/**
	 * Creates an updater for the {@link ScoreTextEntity} on a certain interval in milliseconds
	 * @param scoreText The score text to update
	 * @param intervalInMs The interval in milliseconds for updating
	 */
	public ScoreTextUpdater(ScoreTextEntity scoreText, long intervalInMs) {
		super(intervalInMs);
		this.scoreText = scoreText;
	}

	/**
	 * Updates the {@link ScoreTextEntity} with the new score every interval
	 */
	@Override
	public void onAnimationUpdate(long timestamp) {
		scoreText.updateScoreText();
	}
}
