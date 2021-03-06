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
import com.seb.beroepsproduct.entities.characters.bullets.BulletShooter;
import com.seb.beroepsproduct.entities.items.ItemDropper;
import com.seb.beroepsproduct.entities.map.Door;
import com.seb.beroepsproduct.entities.map.Lock;
import com.seb.beroepsproduct.entities.obstacles.Obstacle;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;
import com.seb.beroepsproduct.entities.score.ScoreTextEntity;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Scene where the game takes place and handles placing entities
 */
public class GameScreen extends DynamicScene implements EntitySpawnerContainer {

	private Main main;
	private Player player1;
	private Coordinate2D playerStartLocation;
	private int level;

	private ArrayList<Obstacle> obstacles;
	private ArrayList<Enemy> enemies;
	private ArrayList<BulletShooter> bulletshooters;
	private ArrayList<ItemDropper> itemdroppers;

	private TextEntity scoreText;
	private ScoreTextEntity score;
	private Door door;
	private BulletShooter shooter;
	private HealthDisplay healthDisplay;

	/**
	 * Initialising the GameScreen
	 * 
	 * @param main Entry point of the game
	 */
	public GameScreen(Main main) {
		this.main = main;
		this.level = 1;

		this.obstacles = new ArrayList<Obstacle>();
		this.enemies = new ArrayList<Enemy>();
		this.bulletshooters = new ArrayList<BulletShooter>();
		this.itemdroppers = new ArrayList<ItemDropper>();
	}

	/**
	 * Sets up the background image of the scene
	 */
	@Override
	public void setupScene() {
		setBackgroundImage("sprites/map.png", true);
	}

	/**
	 * Sets up all the sprites that will be used on this scene first call will setup
	 * the player and then sets up the {@link Obstacle}s and {@link Enemy} list if
	 * called for second, third etc. only the obstacle and enemy lists will be set
	 */
	@Override
	public void setupEntities() {
		if (Objects.isNull(player1)) {
			playerStartLocation = new Coordinate2D(50, getHeight() / 2);
			var player = new Player(playerStartLocation, 5, 3, this);
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
			var score = new ScoreTextEntity(player1, new Coordinate2D(50, 80));
			this.score = score;
			var door = new Door(new Coordinate2D(getWidth() - 50, getHeight() / 2), new Size(60, 90), 270);
			door.setAnchorPoint(AnchorPoint.CENTER_CENTER);
			this.door = door;
		}
		addEntity(scoreText);
		addEntity(score);
		addEntity(door);

		var lock = new Lock(player1, "sprites/lock.png", new Coordinate2D(getWidth() - 50, getHeight() / 2),
				new Size(50, 50));
		lock.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(lock);
	}

	/**
	 * Sets up the entity spawners for the {@link HealthDisplay} and
	 * {@link BulletShooter} for {@link Player} and all the {@link BulletShooter}s
	 * and {@link ItemDropper}s for the enemies
	 */
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
	 * @return Current level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets current level
	 * 
	 * @param level New level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Picks location for {@link Obstacle}s, so no {@link Obstacle}s are not placed
	 * where the {@link Player} is located
	 * 
	 * @param player Current {@link Player}
	 * @return Coordinates for {@link Obstacle}
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
	 * Fills {@link Obstacle}s list for the game to place
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
	 * Picks location for spawning {@link Enemy}, while checking if {@link Enemy} is
	 * not placed on location of {@link Obstacle} and {@link Player}
	 * 
	 * @param player Current {@link Player}
	 * @return Coordinates for spawning the {@link Enemy}
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
	 * Fills the {@link Enemy} array with random enemies. Every 3 levels adds one
	 * {@link Enemy} in total
	 */
	private void fillEnemyArray() {

		var enemyHealth = 500 + (int) ((this.level - 1) * 50);

		enemies.clear();
		var nEnemies = 3 + Math.floor(level / 3); // every three levels a new enemy
		for (int i = 0; i < nEnemies; i++) {
			var randomNr = Math.random();
			if (randomNr < 0.5) { 
				var enemy = new Zombie(pickEnemyLocation(player1), player1, enemyHealth, this);
				enemies.add(enemy);
			} else {
				var enemy = new Fireball(pickEnemyLocation(player1), player1, enemyHealth, this);
				enemies.add(enemy);
			}
		}
	}

	/**
	 * Gets main entry point of application
	 * 
	 * @return main Entry point of the game
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * Gets current score of {@link Player}
	 * 
	 * @return Current score of player
	 */
	public int getScore() {
		return player1.getScore();
	}

	/**
	 * Gets current {@link Player}
	 * 
	 * @return Current {@link Player}
	 */
	public Player getPlayer() {
		return this.player1;
	}

	/**
	 * Sets current {@link Player}
	 * 
	 * @param player New {@link Player}
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

	/**
	 * Resets {@link Player} to the left side of the {@link GameScreen}
	 */
	public void resetPlayerLocation() {
		this.player1.setAnchorLocation(playerStartLocation);
	}
}
