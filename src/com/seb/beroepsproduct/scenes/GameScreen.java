package com.seb.beroepsproduct.scenes;

import java.util.ArrayList;
import java.util.Objects;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.fireball.Fireball;
import com.seb.beroepsproduct.entities.characters.enemies.zombie.Zombie;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.health.HealthDisplay;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletShooter;
import com.seb.beroepsproduct.entities.items.ItemDropper;
import com.seb.beroepsproduct.entities.map.Door;
import com.seb.beroepsproduct.entities.map.Lock;
import com.seb.beroepsproduct.entities.obstacles.Obstacle;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;
import com.seb.beroepsproduct.entities.score.scoreTextEntity;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameScreen extends DynamicScene implements EntitySpawnerContainer {

	private Main main;
	private Player player1;
	private int level = 1;

	public ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<BulletShooter> bulletshooters = new ArrayList<BulletShooter>();
	public ArrayList<ItemDropper> itemdroppers = new ArrayList<ItemDropper>();

	private TextEntity scoreText;
	private scoreTextEntity score;
	private Door door;
	private BulletShooter shooter;
	private HealthDisplay healthDisplay;

	public GameScreen(Main main) {
		this.main = main;
	}

	@Override
	public void setupScene() {
		setBackgroundImage("sprites/map.png", true);
	}

	@Override
	public void setupEntities() {
		if (Objects.isNull(player1)) {
			var player = new Player(new Coordinate2D(getWidth() - 200, getHeight() / 2), 5, 3, this);
			player1 = player;
			addEntity(player1);
		} else {
			addEntity(player1);
		}

		fillObstacleArray();
		for (Obstacle obst : obstacles) {
			addEntity(obst);
		}

		fillEnemyArray();
		for (Enemy nme : enemies) {
			addEntity(nme);
		}

		var levelTextEntity = new TextEntity(new Coordinate2D(50, 20), getLevelText());
		levelTextEntity.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
		levelTextEntity.setFill(Color.WHITE);
		addEntity(levelTextEntity);

		if (player1.getScore() == 0) {
			var scoreText = new TextEntity(new Coordinate2D(50, 40), "score");
			scoreText.setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
			scoreText.setFill(Color.WHITE);
			this.scoreText = scoreText;
			var score = new scoreTextEntity(player1, new Coordinate2D(50, 80));
			this.score = score;
			var door = new Door(new Coordinate2D(getWidth() - 50, getHeight() / 2), new Size(60, 90), 270);
			door.setAnchorPoint(AnchorPoint.CENTER_CENTER);
			this.door = door;
		}
		addEntity(scoreText);
		addEntity(score);
		addEntity(door);
		
		var lock = new Lock(player1, "sprites/lock.png", new Coordinate2D(getWidth() - 50, getHeight() / 2), new Size(50,50));
		lock.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(lock);
	}

	@Override
	public void setupEntitySpawners() {
		if (player1.getScore() == 0) {
			var healthDisplay = new HealthDisplay(1000, player1);
			this.healthDisplay = healthDisplay;
			var shooter = new BulletShooter(player1, 100);
			this.shooter = shooter;
		}

		addEntitySpawner(healthDisplay);
		addEntitySpawner(shooter);

		bulletshooters.clear();
		itemdroppers.clear();
		for (Enemy nme : enemies) {
			if (nme instanceof Fireball) {
				var shooter2 = new BulletShooter(nme, 500);
				bulletshooters.add(shooter2);

			}
			var itemDropper = new ItemDropper(player1, nme, 50);
			itemdroppers.add(itemDropper);
		}
		for (BulletShooter shtr : bulletshooters)
			addEntitySpawner(shtr);
		for (ItemDropper id : itemdroppers)
			addEntitySpawner(id);
	}

	/**
	 * Gets current level
	 * 
	 * @return current level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets current level
	 * 
	 * @param level new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Picks location for obstacles, so no obstacles are not placed where the player
	 * is located
	 * 
	 * @param player current player
	 * @return coordinates for obstacle
	 */
	private Coordinate2D pickObstacleLocation(Player player) {
		boolean chosen = false;
		while (!chosen) {
			var xCoord = 50 + Math.random() * (getWidth() - 150);
			var yCoord = 50 + Math.random() * getHeight() * 0.88;
			Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
			if (tempCoord.distance(player.getAnchorLocation()) > 500) {
				return tempCoord;
			}
		}
		return (new Coordinate2D(0, 0));
	}

	/**
	 * Fills obstacles for the game to place
	 */
	private void fillObstacleArray() {
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
	 * Picks location for spawning enemy, while checking if enemy is not placed on
	 * location of obstacle and player
	 * 
	 * @param player current player
	 * @return coordinates for spawning the enemy
	 */
	public Coordinate2D pickEnemyLocation(Player player) {
		boolean chosen = false;
		boolean possible;
		while (!chosen) {
			possible = true;
			var xCoord = 50 + Math.random() * (getWidth() * 0.95 - 400);
			var yCoord = 80 + Math.random() * (getHeight() * 0.88);
			Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
			for (int i = 0; i < this.obstacles.size(); i++) {
				possible = true;
				if (tempCoord.distance(player.getAnchorLocation()) < 600
						|| tempCoord.distance(this.obstacles.get(i).getAnchorLocation()) < 400) {
					possible = false;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				if (tempCoord.distance(enemies.get(i).getAnchorLocation()) < 200) {
					possible = false;
				}
			}
			if (possible) {
				return tempCoord;
			}
		}
		return (new Coordinate2D(0, 0));
	}

	/**
	 * Fills the enemy array with enemies, always one zombie and the rest are
	 * fireballs. Every 3 levels adds one enemy in total
	 */
	private void fillEnemyArray() {

		var enemyHealth = 500 + (int) ((this.level - 1) * 50);

		enemies.clear();
		var nEnemies = 3 + Math.floor(level / 3); // elke drie levels een enemy erbij
		System.out.println("" + nEnemies);
		for (int i = 0; i < nEnemies; i++) {
			if (i == 0) { // 1 zombie, rest vuurballen
				var enemy = new Zombie(pickEnemyLocation(player1), player1, enemyHealth, 10, this);
				enemies.add(enemy);
			} else {
				var enemy = new Fireball(pickEnemyLocation(player1), player1, enemyHealth, 10, this);
				enemies.add(enemy);
			}
		}
	}

	/**
	 * Gets main entry point of application
	 * @return main entry point of application
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * Gets current score of player
	 * 
	 * @return current score of player
	 */
	public int getScore() {
		return player1.getScore();
	}

	/**
	 * Gets current player
	 * 
	 * @return current player
	 */
	public Player getPlayer() {
		return this.player1;
	}

	/**
	 * Sets current {@link Player}
	 * 
	 * @param player new {@link Player}
	 */
	public void setPlayer(Player player) {
		this.player1 = player;
	}

	/**
	 * Gets current level in text
	 * 
	 * @return "Level {level}"
	 */
	private String getLevelText() {
		return "Level " + level;
	}
}
