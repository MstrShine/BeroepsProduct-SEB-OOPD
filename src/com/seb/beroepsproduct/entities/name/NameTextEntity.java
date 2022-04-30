package com.seb.beroepsproduct.entities.name;

import java.util.Set;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.seb.beroepsproduct.scenes.EndGameScreen;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A text entity on the scene to display the typed name of the user
 */
public class NameTextEntity extends DynamicTextEntity implements KeyListener, TimerContainer {

	private EndGameScreen endGameScreen;
	private String name;

	/**
	 * Creates a name text entity to place on the end game screen
	 * @param endGameScreen The {@link EndGameScreen} of the game
	 * @param location The location on the scene
	 */
	public NameTextEntity(EndGameScreen endGameScreen, Coordinate2D location) {
		super(location);
		this.endGameScreen = endGameScreen;
		setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
		setFill(Color.WHITE);

		this.name = endGameScreen.getName();
		setText("Enter your name: " + name);
	}

	/**
	 * When keys are pressed the letterkeys and the backspace key will influence the name string
	 */
	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.BACK_SPACE) && name.length() > 0) {
			name = name.substring(0, (name.length() - 1));
		} else {
			if (name.length() < 9) {
				for (KeyCode code : pressedKeys) {
					if (code.isLetterKey())
						name = name + code;
				}
			}
		}
		
		endGameScreen.setName(name);
	}

	/**
	 * Adds the {@link NameTextUpdater}
	 */
	@Override
	public void setupTimers() {
		addTimer(new NameTextUpdater(this, 50, endGameScreen));
	}

	/**
	 * Sets the new name text on the screen
	 */
	public void updateNameText() {
		var nameString = "YOUR NAME: " + name;
		setText(nameString);
	}
}
