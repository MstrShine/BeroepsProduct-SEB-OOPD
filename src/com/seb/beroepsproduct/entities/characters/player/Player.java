package com.seb.beroepsproduct.entities.characters.player;

import java.util.Set;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.seb.beroepsproduct.entities.characters.Character;

import javafx.scene.input.KeyCode;

public class Player extends Character implements KeyListener, SceneBorderTouchingWatcher {

	public Player(Coordinate2D startLocation, int health) {
		super("sprites/player1.gif", startLocation, health);
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
}
