package com.seb.beroepsproduct.entities.characters.player;

import java.awt.GraphicsEnvironment;
import java.util.Set;
//import java.lang.Math;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseMovedListener;
import com.seb.beroepsproduct.entities.characters.Character;

import javafx.scene.input.KeyCode;

public class Player extends Character implements MouseMovedListener, KeyListener, SceneBorderTouchingWatcher {
	double directionPlayer;
	
	public Player(Coordinate2D startLocation, int health) {
		super("sprites/player1.gif", startLocation, health);
		directionPlayer = 0;
		//setRotationSpeed(rotation);
	}

	@Override
	public void Hit(int damage) {
		this.health -= damage;
	}

	@Override
	public void Move(double direction) {
		setMotion(1, direction);
	}

	@Override
	public void Move(Direction direction) {
		setMotion(1, direction);
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.A) && pressedKeys.contains(KeyCode.W)) {
			Move((270d + 180d) / 2d);
		} else if (pressedKeys.contains(KeyCode.A) && pressedKeys.contains(KeyCode.S)) {
			Move((270d + 360d) / 2d);
		} else if (pressedKeys.contains(KeyCode.D) && pressedKeys.contains(KeyCode.W)) {
			Move((90d + 180d) / 2d);
		} else if (pressedKeys.contains(KeyCode.D) && pressedKeys.contains(KeyCode.S)) {
			Move((90d + 0d) / 2d);
		} else if (pressedKeys.contains(KeyCode.W)) {
			Move(Direction.UP);
		} else if (pressedKeys.contains(KeyCode.A)) {
			Move(Direction.LEFT);
		} else if (pressedKeys.contains(KeyCode.S)) {
			Move(Direction.DOWN);
		} else if (pressedKeys.contains(KeyCode.D)) {
			Move(Direction.RIGHT);
		} else if (pressedKeys.isEmpty()) {
			setSpeed(0);
		}
	}

	@Override
	public void notifyBoundaryTouching(SceneBorder border) {
		setSpeed(0);

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
	public void onMouseMoved(Coordinate2D mouseXY) {
		//werkt nog niet mooi want geen goede schermgrootte ingevoerd, volgens mij is de spriteentity ook niet vanuit midden maar linksboven getekend
		//3.14 ook niet de echte waarde van pi 
		// GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() voor schermgrootte?
		directionPlayer =  (180/3.14* (Math.atan2(800 - mouseXY.getX(), 400  - mouseXY.getY()))+90);
		setRotate(directionPlayer);
	}
}  
