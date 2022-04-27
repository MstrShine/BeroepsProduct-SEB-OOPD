package com.seb.beroepsproduct.entities.characters.player;

import java.util.Set;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseMovedListener;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.EnemyTimer;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.enemies.zombie.Zombie;
import com.seb.beroepsproduct.entities.characters.health.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.player.invulnerability.InvulnerabilityTimer;
import com.seb.beroepsproduct.entities.characters.player.weapon.WeaponSprite;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;
import com.seb.beroepsproduct.entities.map.Door;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;
import com.seb.beroepsproduct.scenes.GameScreen;

import javafx.scene.input.KeyCode;

public class Player extends Character
		implements TimerContainer, Collided, KeyListener, MouseMovedListener, SceneBorderTouchingWatcher {

	private double directionPlayer;
	private boolean shooting;
	private boolean isVulnerable;
	private int score;
	private int weaponLevel;
	private boolean playerHasKey;

	public Player(Coordinate2D startLocation, int health, int playerLevel, GameScreen screen) {
		super(startLocation, new Size(150, 150), health, screen);
		this.shooting = false;
		this.speed = 3;
		this.isVulnerable = true;
		this.score = 0;
		this.maxHealth = 7;
		this.weaponLevel = 1;
		this.playerHasKey = false;
	}

	@Override
	protected void setupEntities() {
		var pSprite = new PlayerSprite("sprites/player1v2.png", new Coordinate2D(-50, -50), 0, new Size(100, 100));
		addEntity(pSprite);
	}

	@Override
	public void hit(int damage) {
		this.health -= damage;
		var owSound = new SoundClip("audio/OW.mp3");
		owSound.setVolume(10);
		owSound.play();
		if (getHealth() <= 0) {
			handleGameOver();
		}
	}
	
	private void handleGameOver() {
		
		 LocalDateTime myDateObj = LocalDateTime.now();
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);

		screen.getMain().setActiveScene(2);
	}
	
	@Override
	public void onMouseMoved(Coordinate2D mouseXY) {
		var radian = Math.atan2(mouseXY.getX() - (getLocationInScene().getX() + 75),
				mouseXY.getY() - (getLocationInScene().getY() + 75));
		this.directionPlayer = Math.toDegrees(radian) - 90;
		setRotate(this.directionPlayer);
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

	private void shootWeapon(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.SPACE)) {
			this.shooting = true;
		} else {
			this.shooting = false;
		}
	}

	@Override
	public void notifyBoundaryTouching(SceneBorder border) {
		switch (border) {
		case TOP:
			setAnchorLocationY(getBoundingBox().getHeight() / 1.7);
			break;
		case BOTTOM:
			setAnchorLocationY(getSceneHeight() - (getBoundingBox().getHeight() / 2));
			break;
		case LEFT:
			setAnchorLocationX(getBoundingBox().getWidth() / 1.75);
			break;
		case RIGHT:
			setAnchorLocationX(getSceneWidth() - (getBoundingBox().getWidth() / 1.75));
		default:
			break;
		}
	}

	@Override
	public void onCollision(Collider collidingObject) {
		Enemy enemy;
		Bullet bullet;
		
		if (collidingObject instanceof Rock) {
			this.changeDirection(180);
			this.setSpeed(0);
			//this.getDirection();
			//this.setAnchorLocation() 
		}
		if (this.isVulnerable) {
			if (collidingObject instanceof Toxic || collidingObject instanceof Zombie) {
				this.hit(1);
				setVulnerable(false);
				setupTimers();
			}
			if (collidingObject instanceof Robot) {
				enemy = (Robot) collidingObject;
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
			screen.main.setActiveScene(3);
			setScore(getScore()+500);
			setPlayerHasKey(false);
		}

	}

	@Override
	public void setupTimers() {
		addTimer(new InvulnerabilityTimer(this, 50));
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getDirectionPlayer() {
		return this.directionPlayer;
	}

	public void setDirectionPlayer(double directionPlayer) {
		this.directionPlayer = directionPlayer;
	}

	public boolean isShooting() {
		return this.shooting;
	}

	public boolean isPlayerHasKey() {
		return this.playerHasKey;
	}

	public void setPlayerHasKey(boolean playerHasKey) {
		this.playerHasKey = playerHasKey;
	}

	public int getWeaponLevel() {
		return this.weaponLevel;
	}

	public void setWeaponLevel(int weaponLevel) {
		this.weaponLevel = weaponLevel;
	}

	public int getScore() {
		return this.score;
	}

	public void setVulnerable(boolean vulnerable) {
		this.isVulnerable = vulnerable;
	}

}
