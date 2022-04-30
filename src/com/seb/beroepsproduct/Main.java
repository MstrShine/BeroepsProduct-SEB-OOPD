package com.seb.beroepsproduct;

import java.awt.GraphicsEnvironment;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.seb.beroepsproduct.scenes.EndGameScreen;
import com.seb.beroepsproduct.scenes.GameScreen;
import com.seb.beroepsproduct.scenes.IntermissionScreen;
import com.seb.beroepsproduct.scenes.TitleScreen;

/**
 * The main entry point of the game and initializes the screen width and height
 * plus sets up the scenes that will be used
 */
public class Main extends YaegerGame {

	private final int WIDTH;
	private final int HEIGHT;

	/**
	 * Gets called when the game is inialized and sets the width and the height by
	 * getting the width and height of the main screen of the PC and removes some
	 * space
	 */
	public Main() {
		var gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); // get info of
																								// main screen
		var x = gd.getDefaultConfiguration().getDefaultTransform().getScaleX();
		var y = gd.getDefaultConfiguration().getDefaultTransform().getScaleY();

		WIDTH = (int) (gd.getDisplayMode().getWidth() / x - 100);
		HEIGHT = (int) (gd.getDisplayMode().getHeight() / y - 100);
	}

	/**
	 * Launches the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Sets the size of the screen where the game will be played
	 */
	@Override
	public void setupGame() {
		setSize(new Size(WIDTH, HEIGHT));
	}

	/**
	 * Sets up the scenes of the game
	 */
	@Override
	public void setupScenes() {
		var gs = new GameScreen(this);
		addScene(0, new TitleScreen(this));
		addScene(1, gs);
		addScene(2, new EndGameScreen(this, gs));
		addScene(3, new IntermissionScreen(this, gs));
	}

}
