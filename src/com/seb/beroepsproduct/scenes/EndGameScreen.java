package com.seb.beroepsproduct.scenes;

import java.util.ArrayList;
import java.util.Scanner;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.entities.name.NameTextEntity;
import com.seb.beroepsproduct.scenes.interactables.buttons.NewGameButton;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; // Import the IOException class to handle errors
import java.io.FileWriter; // Import the FileWriter class

/**
 * The end screen of the game showing the leaderboard with highscores and the points of the user
 */
public class EndGameScreen extends DynamicScene {

	private GameScreen gameScreen;
	private Main main;
	private ArrayList<String> highscores;
	private int hsIndex;
	private String name;

	/**
	 * Initialises the end game screen for showing the current leaderboard and the points of the user
	 * @param main The main entry point of the game
	 * @param gameScreen The game screen of the game
	 */
	public EndGameScreen(Main main, GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.main = main;
		this.highscores = new ArrayList<String>();
		this.name = "YOU";
	}

	@Override
	public void setupScene() {
		setBackgroundImage("sprites/gameOverScreen.gif", true);
	}

	@Override
	public void setupEntities() {
		readHighscores();
		updateHighscores();
		displayHighscores();

		var gameoverTextEntity = new TextEntity(new Coordinate2D(getWidth() / 4, 150), "YOU DIED. MANKIND IS DOOMED.");
		gameoverTextEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		gameoverTextEntity.setFill(Color.RED);
		gameoverTextEntity.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		addEntity(gameoverTextEntity);

		if (hsIndex < 10) {
			var madeHS = new TextEntity(new Coordinate2D(getWidth() / 4, 180),
					"But at least you got a new highscore right?");
			madeHS.setAnchorPoint(AnchorPoint.CENTER_CENTER);
			madeHS.setFill(Color.RED);
			madeHS.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			addEntity(madeHS);

			var nameTextEntity = new NameTextEntity(this, new Coordinate2D(getWidth() / 4, 240));
			nameTextEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);

			addEntity(nameTextEntity);
		}

		var newGameButton = new NewGameButton(main, this, gameScreen,
				new Coordinate2D(getWidth() / 4, getHeight() - 100), "PLAY AGAIN", Color.RED, Color.GREEN, "Roboto",
				50);
		newGameButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(newGameButton);
	}

	/**
	 * Updates the highscores list with the score of the {@link Player}
	 */
	private void updateHighscores() {
		int finalScore = gameScreen.getPlayer().getScore();
		for (int i = 1; i < highscores.size(); i = i + 2) {
			if (finalScore < Integer.parseInt(highscores.get(i))) {
				hsIndex++;
			}
		}
		highscores.add(hsIndex * 2, name);
		highscores.add(hsIndex * 2 + 1, "" + finalScore);
	}

	/**
	 * Saves the highscores to the file for showing the next time in correct
	 * positions
	 */
	public void saveHighScores() {
		try {
			FileWriter myWriter = new FileWriter("highscores.txt");
			for (String data : highscores) {
				myWriter.write(data);
				myWriter.write("\n");
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Reads the highscores out of the text file and adds them to the highscores
	 * list
	 */
	private void readHighscores() {
		highscores.clear();
		try {
			File myObj = new File("highscores.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				highscores.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Loops through the highscores list to show the top 10 at the screen
	 */
	private void displayHighscores() {
		for (int i = 0; i < 20; i++) { // Only show top 10
			if (i <= highscores.size()) {
				var offsetWidth = 0;
				if (i % 2 == 1) {
					offsetWidth = 300;
				}
				var offsetHeight = Math.floor(i / 2) * 40;
				var highscoretext = new TextEntity(new Coordinate2D(getWidth() / 2 + offsetWidth, 300 + offsetHeight),
						highscores.get(i));
				highscoretext.setAnchorPoint(AnchorPoint.CENTER_LEFT);

				if (i == hsIndex * 2 || i == hsIndex * 2 + 1) {
					highscoretext.setFill(Color.WHITE);
				} else {
					highscoretext.setFill(Color.GOLD);
				}
				highscoretext.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
				addEntity(highscoretext);
			}
		}
	}

	/**
	 * Updates the name on the highscore index
	 */
	public void updateName() {
		highscores.set(hsIndex * 2, name);
	}

	/**
	 * Returns the name that the user has put in
	 * @return The current name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of the user
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
