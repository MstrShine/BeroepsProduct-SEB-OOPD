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
import com.seb.beroepsproduct.entities.characters.enemies.zombie.Zombie;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.health.HealthDisplay;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletShooter;
import com.seb.beroepsproduct.entities.items.ItemDropper;
import com.seb.beroepsproduct.entities.map.Door;
import com.seb.beroepsproduct.entities.obstacles.Obstacle;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;
import com.seb.beroepsproduct.entities.score.scoreTextEntity;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScreen extends DynamicScene implements EntitySpawnerContainer {

	public Main main;
	public Player player1;
	private int level = 1;

	private ArrayList<Robot> robots = new ArrayList<Robot>();
	private int nRobots = 3;

	public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public GameScreen(Main main) {
		this.main = main;
	}

	@Override
	public void setupScene() {
		setBackgroundImage("sprites/map.png", true);
	}

	@Override
	public void setupEntities() {
		var player = new Player(new Coordinate2D(getWidth() - 200, getHeight() / 2), 5, 3, this);
		player1 = player;
		addEntity(player1);

		fillObstacleArray();
		for (Obstacle obst : obstacles) {
			addEntity(obst);
		}

		fillEnemyArray();
		for (Enemy nme : enemies) {
			addEntity(nme);
		}

		var scoreText = new TextEntity(new Coordinate2D(50, 40), "score");
		scoreText.setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
		scoreText.setFill(Color.WHITE);
		addEntity(scoreText);

		var score = new scoreTextEntity(player1, new Coordinate2D(50, 80));
		addEntity(score);

		var door = new Door(new Coordinate2D(getWidth() - 90, getHeight() / 2), new Size(60, 90), 270);
		addEntity(door);
		/*
		 * for (int i = 0; i < nRobots; i++) { var robot = new
		 * Robot(pickEnemyLocation(player1, obstacles), player1, 500, 10, this);
		 * robots.add(robot); } for (Robot rbt : robots) { addEntity(rbt); }
		 * 
		 */

	}

	@Override
	public void setupEntitySpawners() {
		var healthDisplay = new HealthDisplay(1000, player1);
		addEntitySpawner(healthDisplay);

		var shooter = new BulletShooter(player1, 20);
		addEntitySpawner(shooter);

		for (Enemy nme : enemies) {
			if (nme instanceof Robot) {
				var shooter2 = new BulletShooter(nme, 500);
				addEntitySpawner(shooter2);
			}
			var itemDropper = new ItemDropper(player1, nme, 50);
			addEntitySpawner(itemDropper);
		}

	}

	public Coordinate2D pickObstacleLocation(Player player) {
		boolean chosen = false;
		while (!chosen) {
			var xCoord = 50 + Math.random() * (getWidth()-150);
			var yCoord = 50 + Math.random() * getHeight()*0.88;
			Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
			if (tempCoord.distance(player.getAnchorLocation()) > 500) {
				return tempCoord;
			}
		}
		return (new Coordinate2D(0, 0));
	}

	private void fillObstacleArray() {
		System.out.println("ik begin nu met obstacle vullen");
		obstacles.clear();
		var nObstacles = 3 + Math.floor((Math.random() * 10) / 2.5);
		for (int i = 0; i < nObstacles; i++) {
			var width = 20 + Math.random() * 100;
			var height = width / 2 + Math.random() * 50;
			if (Math.random() > 0.5) {
				var obstacle = new Rock(pickObstacleLocation(player1), new Size(width + 25, height + 25));
				obstacles.add(obstacle);
			} else {
				var obstacle = new Toxic(pickObstacleLocation(player1), new Size(width, height));
				obstacles.add(obstacle);
			}
		}
	}

	/**
	 * picks location for spawning enemy
	 * 
	 * @param player player object
	 * @return coordinates for spawning
	 */
	public Coordinate2D pickEnemyLocation(Player player, ArrayList<Obstacle> obstacles) {
		boolean chosen = false;
		boolean possible;
		while (!chosen) {
			possible = true;
			var xCoord = 50+ Math.random() * (getWidth()*0.95 - 400);
			var yCoord = 80+ Math.random() * (getHeight()*0.88);
			Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
			for (int i = 0; i < obstacles.size(); i++) {
				possible = true;
				if (tempCoord.distance(player.getAnchorLocation()) < 600
						|| tempCoord.distance(obstacles.get(i).getAnchorLocation()) < 400) {
					possible = false;
				}
			}
			for (int j = 0; j < enemies.size(); j++) {
				if (tempCoord.distance(enemies.get(j).getAnchorLocation()) < 200) {
					possible = false;
				}
			}
			if (possible) {
				return tempCoord;
			}
		}
		return (new Coordinate2D(0, 0));
	}
	
	private void fillEnemyArray() {
		enemies.clear();
		var nEnemies = 3 + Math.floor(level / 3); // elke drie levels een enemy erbij
		System.out.println("" + nEnemies);
		for (int i = 0; i < nEnemies; i++) {
			if (Math.random() > 0.5) {
				var enemy = new Robot(pickEnemyLocation(player1, obstacles), player1, 500, 10, this);
				enemies.add(enemy);
			} else {
				var enemy = new Zombie(pickEnemyLocation(player1, obstacles), player1, 500, 10, this);
				enemies.add(enemy);
			}
		}
		var length = enemies.size();
		System.out.println("de lengte van de enemy array is: " + length);
	}

}
