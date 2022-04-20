package com.seb.beroepsproduct.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.entities.HealthDisplay;
import com.seb.beroepsproduct.entities.scoreTextEntity;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletShooter;
import com.seb.beroepsproduct.entities.map.Door;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScreen extends DynamicScene implements EntitySpawnerContainer {
	
	private Main main;
	public Player player1;
	//public Robot robot;
	
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	private int nRobots = 3; 
	
	public GameScreen(Main main) {
		this.main = main;
	}

	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("sprites/map.png", true);
	}

	@Override
	public void setupEntities() {
		var player = new Player(new Coordinate2D(getWidth() / 2, getHeight() / 2), 5, 3);
		player1 = player;
		addEntity(player1);
		
		var healthDisplay = new HealthDisplay(new Coordinate2D(getWidth()/2-400, 100), player1);
		addEntity(healthDisplay);
		
		var scoreText = new TextEntity(new Coordinate2D(50,50), "score");
	    scoreText.setFont(Font.font("Roboto",FontWeight.NORMAL, 30));
	    scoreText.setFill(Color.WHITE);
		addEntity(scoreText);
		
		var score = new scoreTextEntity(player1, new Coordinate2D(50, 90));
		addEntity(score);

		var door = new Door(main, new Coordinate2D(getWidth() - 90, getHeight() / 2), new Size(60, 90), 270);
		addEntity(door);
/*
		var robot = new Robot(pickEnemyLocation(player1), player1, 500, 10);
		// var robot = new Robot(new Coordinate2D(100,100), player1, 500);
		this.robot = robot;
		addEntity(robot);
*/
		
		for (int i = 0; i<nRobots; i++) {
			var robot = new Robot(pickEnemyLocation(player1), player1, 500, 10, this);
			robots.add(robot);
		}
		for (Robot rbt : robots) {addEntity(rbt);}
	}

	@Override
	public void setupEntitySpawners() {
		var shooter = new BulletShooter(player1, 20);
		addEntitySpawner(shooter);
/*
		var shooter2 = new BulletShooter(robot, 500);
		addEntitySpawner(shooter2);
*/ 		
		for (Robot rbt : robots) {
			var shooter2 = new BulletShooter(rbt, 500);
			addEntitySpawner(shooter2);
		}
		
		
		
		//var eSpawner = new EnemySpawner(player1, robot, pickEnemyLocation(player1), 500, 10, 3);
		//addEntitySpawner(eSpawner);
	}
	
	/*
	private Coordinate2D pickEnemyLocation(Player player) {
		var xCoord = Math.random() * getWidth();
		var yCoord = Math.random() * getHeight();
		Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
		return tempCoord;
	}
	*/
	
	public Coordinate2D pickEnemyLocation(Player player) {
		boolean chosen = false;
		while (!chosen) {
		var xCoord = Math.random() * getWidth();
		var yCoord = Math.random() * getHeight();
		Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
		if (tempCoord.distance(player.getAnchorLocation()) > 500){return tempCoord;}
		}
		return (new Coordinate2D(0,0));
	}

}
