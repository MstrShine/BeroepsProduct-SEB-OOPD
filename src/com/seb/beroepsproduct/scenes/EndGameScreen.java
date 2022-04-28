package com.seb.beroepsproduct.scenes;

import java.util.ArrayList;
import java.util.Scanner;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.entities.name.NameTextEntity;
import com.seb.beroepsproduct.scenes.interactables.buttons.NewGameButton;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; // Import the IOException class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter; // Import the FileWriter class
import java.util.ArrayList;

public class EndGameScreen extends DynamicScene {

	private GameScreen gameScreen;
	private Main main;
	public ArrayList<String> highscores = new ArrayList<String>();
	private int hsIndex;
	private String name;
	// private String playerName;
	// private int endScore;

	public EndGameScreen(Main main, GameScreen gs) {
		this.gameScreen = gs;
		this.main = main;
		name = "YOU";
		// endScore = gs.getScore();
	}

	@Override
	public void setupScene() {
	}

	@Override
	public void setupEntities() {
		var gameoverTextEntity = new TextEntity(new Coordinate2D(getWidth() / 4, 150), "YOU DIED. MANKIND IS DOOMED.");
		gameoverTextEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		gameoverTextEntity.setFill(Color.RED);
		gameoverTextEntity.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		addEntity(gameoverTextEntity);

		var nameTextEntity = new NameTextEntity(this, new Coordinate2D(getWidth() / 4, 200));
		addEntity(nameTextEntity);

		readHighscores();
		updateHighscores();
		displayHighscores();

		var newGameButton = new NewGameButton(main, this, gameScreen,
				new Coordinate2D(getWidth() / 4, getHeight() - 200), "Play again", Color.RED, Color.AQUA, "Roboto", 16);
		addEntity(newGameButton);
	}

	public void updateHighscores() {
		int finalScore = gameScreen.getPlayer().getScore();
		for (int i = 1; i < highscores.size(); i = i + 2) {
			if (finalScore < Integer.parseInt(highscores.get(i))) {
				hsIndex++;
			}
			System.out.println("De positie is: " + hsIndex);
		}
		highscores.add(hsIndex * 2, name);
		highscores.add(hsIndex * 2 + 1, "" + finalScore);
	}

	public void updateName() {
		highscores.set(hsIndex * 2, name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	private void displayHighscores() {
		for (int i = 0; i < 20; i++) { // laten alleen de top 10 zien
			var offsetWidth = 0;
			if (i % 2 == 1) {
				offsetWidth = 300;
			}
			var offsetHeight = Math.floor(i / 2) * 40;
			var highscoretext = new TextEntity(new Coordinate2D(getWidth() / 2 + offsetWidth, 300 + offsetHeight),
					highscores.get(i));
			highscoretext.setAnchorPoint(AnchorPoint.CENTER_LEFT);
			highscoretext.setFill(Color.RED);
			highscoretext.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			addEntity(highscoretext);
		}
	}

}
