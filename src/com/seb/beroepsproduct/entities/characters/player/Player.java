package com.seb.beroepsproduct.entities.characters.player;

import java.util.Set;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseMovedListener;
import com.seb.beroepsproduct.entities.SimpleSprite;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.fireball.Fireball;
import com.seb.beroepsproduct.entities.characters.enemies.zombie.Zombie;
import com.seb.beroepsproduct.entities.characters.player.invulnerability.InvulnerabilityTimer;
import com.seb.beroepsproduct.entities.characters.bullets.Bullet;
import com.seb.beroepsproduct.entities.map.Door;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;
import com.seb.beroepsproduct.scenes.GameScreen;

import javafx.scene.input.KeyCode;

/**
 * A playable character listens to mouse movements and pressed keys
 */
public class Player extends Character implements KeyListener, MouseMovedListener {

	private double directionPlayer;
	private boolean shooting;
	private boolean isVulnerable;
	private int score;
	private int weaponLevel;
	private boolean playerHasKey;
	private double bulletDirection;

	/**
	 * Creates a playable character to add to a scene
	 * 
	 * @param startLocation the start location of the player
	 * @param health        the amount of max health and current health
	 * @param playerLevel   the level of the player
	 * @param screen        the screen where the player is located
	 */
	public Player(Coordinate2D startLocation, int health, int playerLevel, GameScreen screen) {
		super(startLocation, health, screen);
		this.shooting = false;
		this.speed = 2.5;
		this.isVulnerable = true;
		this.score = 0;
		this.maxHealth = 5;
		this.weaponLevel = 1;
		this.playerHasKey = false;
	}

	@Override
	public void setupEntities() {
		var pSprite = new SimpleSprite("sprites/player1v2.png", new Coordinate2D(0, 0), 0, new Size(80, 80));
		pSprite.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(pSprite);
	}

	/**
	 * Do damage to the {@link Player} and play sound. If players health is zero or
	 * lower the scene changes to the end game screen
	 */
	@Override
	public void hit(int damage) {
		this.health -= damage;
		var owSound = new SoundClip("audio/OW.mp3");
		owSound.setVolume(10);
		owSound.play();
		if (getHealth() <= 0) {
			gameScreen.getMain().setActiveScene(2);
		}
	}

	/**
	 * Calculates position of gun on the player sprite for shooting {@link Bullet}s out of the sprite
	 * @return Coordinates of the gun
	 */
	public Coordinate2D calcGunLocation() {
		double tempDirection = Math.toRadians(directionPlayer - 30);
		double xCoord = getAnchorLocation().getX() + Math.cos(tempDirection) * 46;
		double yCoord = getAnchorLocation().getY() - Math.sin(tempDirection) * 46;
		return new Coordinate2D(xCoord, yCoord);
	}

	@Override
	public void onMouseMoved(Coordinate2D mouseXY) {
		var radian = Math.atan2(mouseXY.getX() - (getLocationInScene().getX() + 40),
				mouseXY.getY() - (getLocationInScene().getY() + 40));
		this.directionPlayer = Math.toDegrees(radian) - 90;
		setRotate(this.directionPlayer);

		var gunLocation = calcGunLocation();
		var radian2 = Math.atan2(mouseXY.getX() - (gunLocation.getX()), mouseXY.getY() - (gunLocation.getY()));
		setBulletDirection(Math.toDegrees(radian2) - 90);
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		shootWeapon(pressedKeys);
		if (pressedKeys.contains(KeyCode.A) && pressedKeys.contains(KeyCode.W)) {
			move(225d);
		} else if (pressedKeys.contains(KeyCode.A) && pressedKeys.contains(KeyCode.S)) {
			move(315d);
		} else if (pressedKeys.contains(KeyCode.D) && pressedKeys.contains(KeyCode.W)) {
			move(135d);
		} else if (pressedKeys.contains(KeyCode.D) && pressedKeys.contains(KeyCode.S)) {
			move(45d);
		} else if (pressedKeys.contains(KeyCode.W)) {
			move(Direction.UP);
		} else if (pressedKeys.contains(KeyCode.A)) {
			move(Direction.LEFT);
		} else if (pressedKeys.contains(KeyCode.S)) {
			move(Direction.DOWN);
		} else if (pressedKeys.contains(KeyCode.D)) {
			move(Direction.RIGHT);
		} else if (pressedKeys.isEmpty()) {
			setSpeed(0);
		}
	}

	/**
	 * Shoots the weapon of the player if space is pressed
	 * 
	 * @param pressedKeys the pressed keys of the user
	 */
	private void shootWeapon(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.SPACE)) {
			this.shooting = true;
		} else {
			this.shooting = false;
		}
	}

	@Override
	public void onCollision(Collider collidingObject) {
		Enemy enemy;
		Bullet bullet;

		if (collidingObject instanceof Rock) {
			this.changeDirection(180);
			this.setSpeed(0);
		}
		if (this.isVulnerable) {
			if (collidingObject instanceof Toxic) {
				this.hit(1);
				setVulnerable(false);
				setupTimers();
			}
			if (collidingObject instanceof Fireball) {
				enemy = (Fireball) collidingObject;
				if (enemy.isVisible()) {
					this.hit(1);
					setVulnerable(false);
					setupTimers();
				}
			}
			if (collidingObject instanceof Zombie) {
				enemy = (Zombie) collidingObject;
				if (enemy.isVisible()) {
					this.hit(1);
					setVulnerable(false);
					setupTimers();
				}
			} else if (collidingObject instanceof Bullet) {
				bullet = (Bullet) collidingObject;
				if (bullet.getCharacter() instanceof Enemy) {
					this.hit(1);
					setVulnerable(false);
					setupTimers();
				}
			}
		}

		if (collidingObject instanceof Door && playerHasKey) {
			gameScreen.getMain().setActiveScene(3);
			setScore(getScore() + 500);
			setPlayerHasKey(false);
		}

	}

	@Override
	public void setupTimers() {
		addTimer(new InvulnerabilityTimer(this, 50));
	}

	/**
	 * Get the calculated direction of the {@link Bullet}s from the {@link Player}
	 * @return The calculated direction of the {@link Bullet}s from the {@link Player}
	 */
	public double getBulletDirection() {
		return bulletDirection;
	}

	/**
	 * Sets the bullet direction of the {@link Bullet}s from the {@link Player}
	 * @param bulletDirection The bullet direction of the {@link Bullet}s from the {@link Player}
	 */
	public void setBulletDirection(double bulletDirection) {
		this.bulletDirection = bulletDirection;
	}
	
	/**
	 * Sets score of the player
	 * 
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the current direction of where the player is looking to in degrees
	 * 
	 * @return the direction where the player looks to in degrees
	 */
	public double getDirectionPlayer() {
		return this.directionPlayer;
	}

	/**
	 * Gets if the player is currently shooting his gun
	 * 
	 * @return true is shooting otherwise false
	 */
	public boolean isShooting() {
		return this.shooting;
	}

	/**
	 * Gets if the player is currently holding the key
	 * 
	 * @return true is player has key otherwise false
	 */
	public boolean isPlayerHasKey() {
		return this.playerHasKey;
	}

	/**
	 * Sets if the player has the key
	 * 
	 * @param playerHasKey
	 */
	public void setPlayerHasKey(boolean playerHasKey) {
		this.playerHasKey = playerHasKey;
	}

	/**
	 * Gets the current weapon level of the player
	 * 
	 * @return current weapon level of the player
	 */
	public int getWeaponLevel() {
		return this.weaponLevel;
	}

	/**
	 * Sets new weapon level of the player
	 * 
	 * @param weaponLevel new weapon level
	 */
	public void setWeaponLevel(int weaponLevel) {
		this.weaponLevel = weaponLevel;
	}

	/**
	 * Gets the current score of the player
	 * 
	 * @return The current score of the player
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Sets if the player is vulnerable for damage
	 * 
	 * @param vulnerable true is vulnerable, false is invulnerable
	 */
	public void setVulnerable(boolean vulnerable) {
		this.isVulnerable = vulnerable;
	}
}
