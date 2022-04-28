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

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class EndGameScreen extends DynamicScene {
	
	private GameScreen gameScreen;
	private Main main;
	public ArrayList<String> highscores = new ArrayList<String>();
	private int hsIndex;
	//private String playerName;
	//private int endScore;
	
	public EndGameScreen(Main main, GameScreen gs) {
		this.gameScreen = gs;
		this.main = main;
		//endScore = gs.getScore();
	}

	@Override
	public void setupScene() {
	}

	@Override
	public void setupEntities() {
		
		var nameTextEntity = new NameTextEntity(this, new Coordinate2D (getWidth()/4, 200));
		addEntity(nameTextEntity);
		
		readHighscores();
		updateHighscores();
		displayHighscores();
	}

	private void updateHighscores() {
		int finalScore = gameScreen.player1.getScore();
		for (int i = 1; i<highscores.size(); i=i+2) {
			if (finalScore < Integer.parseInt(highscores.get(i))) {
				hsIndex++;
			}
			System.out.println("De positie is: " + hsIndex);
		}
		highscores.add(hsIndex*2, "YOU");
		highscores.add(hsIndex*2+1, ""+finalScore);
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
		for (int i = 0; i<highscores.size(); i++) {
			var offsetWidth = 0;
			if (i%2== 1) {offsetWidth = 300;}
			var offsetHeight = Math.floor(i/2)*40;
			var highscoretext = new TextEntity(new Coordinate2D(getWidth()/2+offsetWidth, 300+offsetHeight), highscores.get(i));
			highscoretext.setAnchorPoint(AnchorPoint.CENTER_LEFT);
			highscoretext.setFill(Color.RED);
			highscoretext.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			addEntity(highscoretext);
		}
	}

}
