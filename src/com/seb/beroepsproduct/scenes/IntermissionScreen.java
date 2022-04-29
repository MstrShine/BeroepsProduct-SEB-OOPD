package com.seb.beroepsproduct.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.scenes.interactables.buttons.NextLevelButton;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * The Screen for between levels to give {@link Player} a short breath between levels and show the bonus points for the {@link Player}
 */
public class IntermissionScreen extends StaticScene {

	private Main main;
	private GameScreen gameScreen;

	/**
	 * Initialises IntermissionScreen for between levels
	 * @param main Main entry point of the Game
	 * @param gameScreen The {@link GameScreen}
	 */
	public IntermissionScreen(Main main, GameScreen gameScreen) {
		this.main = main;
		this.gameScreen = gameScreen;
	}

	@Override
	public void setupScene() {
		setBackgroundImage("sprites/curtains2.gif", true);

	}

	@Override
	public void setupEntities() {
		var lvlComplete = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 4 + 200),
				"LEVEL " + gameScreen.getLevel() + " COMPLETED!");
		lvlComplete.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		lvlComplete.setFill(Color.WHITE);
		lvlComplete.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 120));
		addEntity(lvlComplete);

		var bonus = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 4 + 300), "500 BONUSPOINTS ADDED!");
		bonus.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		bonus.setFill(Color.GOLD);
		bonus.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 100));
		addEntity(bonus);

		var ready = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 4 + 450), "ARE YOU READY?");
		ready.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		ready.setFill(Color.WHITE);
		ready.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 50));
		addEntity(ready);

		var nextLvlButton = new NextLevelButton(new Coordinate2D(getWidth() / 2, getHeight() / 4 + 550),
				"LET'S KICK SOME @SS", Color.RED, Color.GREEN, "Roboto", 50, main, gameScreen);
		nextLvlButton.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
		addEntity(nextLvlButton);

	}

}
