package com.seb.beroepsproduct.scenes.interactables.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.scenes.EndGameScreen;
import com.seb.beroepsproduct.scenes.GameScreen;
import com.seb.beroepsproduct.scenes.interactables.Button;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

/**
 * A {@link Button} to start a new game
 */
public class NewGameButton extends Button {

	private Main main;
	private GameScreen gameScreen;
	private EndGameScreen endGameScreen;

	/**
	 * Creates the new game button on the screen
	 * 
	 * @param main            Main entry point of the game
	 * @param endGameScreen   The end game screen of the game
	 * @param gameScreen      The game screen of the game
	 * @param initialLocation The location of the button on the screen
	 * @param text            The text to display
	 * @param initialColor    The initial color of the button
	 * @param hoverColor      The hover color of the button
	 * @param fontFamily      The font of the button text
	 * @param fontSize        The font size of the text
	 */
	public NewGameButton(Main main, EndGameScreen endGameScreen, GameScreen gameScreen, Coordinate2D initialLocation,
			String text, Color initialColor, Color hoverColor, String fontFamily, int fontSize) {
		super(initialLocation, text, initialColor, hoverColor, fontFamily, fontSize);
		this.main = main;
		this.endGameScreen = endGameScreen;
		this.gameScreen = gameScreen;
	}

	/**
	 * When the mouse button is pressed and the name is longer than 0 characters.
	 * The highscores are saved and the game will be reset so the user can play again
	 * and the user is send to the start game screen
	 */
	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		if (endGameScreen.getName().length() > 0) {
			endGameScreen.saveHighScores();
			main.setActiveScene(0);
			gameScreen.setLevel(1);
			gameScreen.getPlayer().setScore(0);
			gameScreen.getPlayer().remove();
			gameScreen.setPlayer(null);
			endGameScreen.setName("YOU");
			gameScreen.setupEntities();
			gameScreen.setupEntitySpawners();
		}
	}
}
