package com.seb.beroepsproduct.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.health.HealthDisplay;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletShooter;
import com.seb.beroepsproduct.entities.items.ItemDropper;
import com.seb.beroepsproduct.entities.map.Door;
import com.seb.beroepsproduct.entities.score.scoreTextEntity;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScreen extends DynamicScene implements EntitySpawnerContainer {

	public Main main;
	public Player player1;

	private ArrayList<Robot> robots = new ArrayList<Robot>();
	private int nRobots = 3;

	public GameScreen(Main main) {
		this.main = main;
	}

	@Override
	public void setupScene() {
		setBackgroundImage("sprites/map.png", true);
	}

	@Override
	public void setupEntities() {
		var player = new Player(new Coordinate2D(getWidth() / 2, getHeight() / 2), 5, 3, this);
		player1 = player;
		addEntity(player1);

		var scoreText = new TextEntity(new Coordinate2D(50, 40), "score");
		scoreText.setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
		scoreText.setFill(Color.WHITE);
		addEntity(scoreText);

		var score = new scoreTextEntity(player1, new Coordinate2D(50, 80));
		addEntity(score);

		var door = new Door(new Coordinate2D(getWidth() - 90, getHeight() / 2), new Size(60, 90), 270);
		addEntity(door);

		for (int i = 0; i < nRobots; i++) {
			var robot = new Robot(pickEnemyLocation(player1), player1, 500, 10, this);
			robots.add(robot);
		}
		for (Robot rbt : robots) {
			addEntity(rbt);
		}
	}

	@Override
	public void setupEntitySpawners() {
		var healthDisplay = new HealthDisplay(1000, player1);
		addEntitySpawner(healthDisplay);
		
		var shooter = new BulletShooter(player1, 20);
		addEntitySpawner(shooter);

		for (Robot rbt : robots) {
			var shooter2 = new BulletShooter(rbt, 500);
			addEntitySpawner(shooter2);
			var itemDropper = new ItemDropper(player1, rbt, 50);
			addEntitySpawner(itemDropper);
		}

	}

	/**
	 * picks location for spawning enemy
	 * @param player player object
	 * @return coordinates for spawning
	 */
	public Coordinate2D pickEnemyLocation(Player player) {
		boolean chosen = false;
		while (!chosen) {
			var xCoord = Math.random() * (getWidth() - 500);
			var yCoord = Math.random() * getHeight();
			Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
			if (tempCoord.distance(player.getAnchorLocation()) > 500) {
				return tempCoord;
			}
		}
		return (new Coordinate2D(0, 0));
	}

}
