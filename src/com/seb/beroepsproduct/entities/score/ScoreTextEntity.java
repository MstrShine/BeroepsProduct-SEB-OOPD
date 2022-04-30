package com.seb.beroepsproduct.entities.score;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A simple entity to display the score of the {@link Player}
 */
public class ScoreTextEntity extends DynamicTextEntity implements TimerContainer {

	private Player player;

	/**
	 * Sets the text on a scene
	 * @param player The current {@link Player}
	 * @param initialLocation The location in the scene to set the text
	 */
	public ScoreTextEntity(Player player, Coordinate2D initialLocation) {
		super(initialLocation);

		this.player = player;

		setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
		setFill(Color.WHITE);
		updateScoreText();
	}

	/**
	 * Sets the score as text
	 */
	public void updateScoreText() {
		var scoreString = "" + player.getScore();
		setText(scoreString);
	}

	/**
	 * Adds the {@link ScoreTextUpdater}
	 */
	@Override
	public void setupTimers() {
		addTimer(new ScoreTextUpdater(this, 50));
	}

}
