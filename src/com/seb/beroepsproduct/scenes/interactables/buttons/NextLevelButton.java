package com.seb.beroepsproduct.scenes.interactables.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.scenes.GameScreen;
import com.seb.beroepsproduct.scenes.interactables.Button;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

/**
 * A {@link Button} to send user to the next level
 */
public class NextLevelButton extends Button {

	private Main main;
	private GameScreen gameScreen;

	/**
	 * Creates a {@link Button} to send the user to the next level
	 * @param initialLocation The location of the button on the screen
	 * @param text The text to display
	 * @param initialColor The initial color of the button
	 * @param hoverColor The hover color of the button
	 * @param fontFamily The font of the button text
	 * @param fontSize The font size of the text
	 * @param main Main entry point of the game
	 * @param gameScreen The game screen of the game
	 */
	public NextLevelButton(Coordinate2D initialLocation, String text, Color initialColor, Color hoverColor,
			String fontFamily, int fontSize, Main main, GameScreen gameScreen) {
		super(initialLocation, text, initialColor, hoverColor, fontFamily, fontSize);
		this.main = main;
		this.gameScreen = gameScreen;
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		gameScreen.setupEntities();
		gameScreen.setupEntitySpawners();
		gameScreen.setLevel(gameScreen.getLevel() + 1);
		gameScreen.resetPlayerLocation();
		main.setActiveScene(1);
	}

	@Override
	public void onMouseEntered() {
		setCursor(Cursor.HAND);
		setFill(this.hoverColor);
	}

	@Override
	public void onMouseExited() {
		setCursor(Cursor.DEFAULT);
		setFill(this.initialColor);
	}
}
