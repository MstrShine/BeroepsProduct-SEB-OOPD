package com.seb.beroepsproduct.entities.score;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class scoreTextEntity extends DynamicTextEntity implements TimerContainer {

	private Player player;

	public scoreTextEntity(Player player, Coordinate2D initialLocation) {
		super(initialLocation);

		this.player = player;

		setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
		setFill(Color.WHITE);
		setScoreText();
	}

	public void setScoreText() {
		var scoreString = "" + player.getScore();
		setText(scoreString);
	}

	@Override
	public void setupTimers() {
		addTimer(new ScoreUpdater(this, 50));
	}

}
