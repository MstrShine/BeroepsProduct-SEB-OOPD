package com.seb.beroepsproduct.entities.characters.player;

import java.util.Set;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseMovedListener;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.EnemyTimer;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.weapon.WeaponSprite;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;

import javafx.scene.input.KeyCode;

public class Player extends Character implements TimerContainer, Collided, KeyListener, MouseMovedListener, SceneBorderTouchingWatcher {

	double directionPlayer;
	private int PlayerLevel;
	private boolean shooting;
	private double speed;
	protected boolean vulnerable;

	public Player(Coordinate2D startLocation, int health, int PlayerLevel) {
		super(startLocation, new Size(150, 150), health);
		this.PlayerLevel = PlayerLevel;
		shooting = false;
		speed = 3;
		vulnerable = true;
	}

	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}

	public int getPlayerLevel() {
		return PlayerLevel;
	}

	public void setPlayerLevel(int playerLevel) {
		PlayerLevel = playerLevel;
	}

	@Override
	protected void setupEntities() {
		var pSpriteGun = new WeaponSprite("sprites/gun.gif", new Coordinate2D(-100, -100), -90);
		addEntity(pSpriteGun);
		var pSprite = new PlayerSprite("sprites/player1.gif", new Coordinate2D(-100, -100), 0);
		addEntity(pSprite);
		this.text = new CharacterHealthText(this, new Coordinate2D(-100, -100));
		addEntity(this.text);
	}

	@Override
	public void Hit(int damage) {
		this.health -= damage;
	}

	@Override
	public void Move(double direction) {
		setMotion(speed, direction);
	}

	@Override
	public void Move(Direction direction) {
		setMotion(speed, direction);
	}

	@Override
	public void onMouseMoved(Coordinate2D mouseXY) {
		var radian = Math.atan2(mouseXY.getX() - (getLocationInScene().getX() + 75),
				mouseXY.getY() - (getLocationInScene().getY() + 75));
		directionPlayer = Math.toDegrees(radian) - 90;
		setRotate(directionPlayer);
	}

	public double getDirectionPlayer() {
		return directionPlayer;
	}

	public void setDirectionPlayer(double directionPlayer) {
		this.directionPlayer = directionPlayer;
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		shootWeapon(pressedKeys);
		if (pressedKeys.contains(KeyCode.A) && pressedKeys.contains(KeyCode.W)) {
			Move(225d);
		} else if (pressedKeys.contains(KeyCode.A) && pressedKeys.contains(KeyCode.S)) {
			Move(315d);
		} else if (pressedKeys.contains(KeyCode.D) && pressedKeys.contains(KeyCode.W)) {
			Move(135d);
		} else if (pressedKeys.contains(KeyCode.D) && pressedKeys.contains(KeyCode.S)) {
			Move(45d);
		} else if (pressedKeys.contains(KeyCode.W)) {
			Move(Direction.UP);
		} else if (pressedKeys.contains(KeyCode.A)) {
			Move(Direction.LEFT);
		} else if (pressedKeys.contains(KeyCode.S)) {
			Move(Direction.DOWN);
		} else if (pressedKeys.contains(KeyCode.D)) {
			Move(Direction.RIGHT);
		} else if (pressedKeys.contains(KeyCode.SPACE)) {
			shooting = true;
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

	public boolean isShooting() {
		return shooting;
	}

	@Override
	public void notifyBoundaryTouching(SceneBorder border) {
		switch (border) {
		case TOP:
			setAnchorLocationY(1);
			break;
		case BOTTOM:
			setAnchorLocationY(getSceneHeight() - getHeight() - 1);
			break;
		case LEFT:
			setAnchorLocationX(1);
			break;
		case RIGHT:
			setAnchorLocationX(getSceneWidth() - getWidth() - 1);
		default:
			break;
		}
	}

	@Override
	public void onCollision(Collider collidingObject) {
		Enemy enemy;
		Bullet bullet;
		if (collidingObject instanceof Robot && vulnerable) {
			enemy = (Robot) collidingObject;
			this.Hit(enemy.getDamage());
			text.update();
			vulnerable = false;
			setupTimers();
		}
		if (collidingObject instanceof Bullet && vulnerable) {
			bullet = (Bullet) collidingObject;
			if (bullet.character instanceof Enemy) {
				this.Hit(bullet.getDamage());
				text.update();
				vulnerable = false;
				setupTimers();
			}
		}

	}

	@Override
	public String GetHealth() {
		return "" + health;
	}

	@Override
	public void setupTimers() {
		addTimer(new InvulnerabilityTimer(this, 50));
		// this.createInvulnerabilityTimer();
	}
	/*
	 * protected void createInvulnerabilityTimer() { addTimer(new
	 * InvulnerabilityTimer(this, 50)); }
	 */
}
