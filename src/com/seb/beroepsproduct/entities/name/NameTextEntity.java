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

public class NameTextEntity extends DynamicTextEntity implements KeyListener, TimerContainer {

	private EndGameScreen screen;
	private String name;

	public NameTextEntity(EndGameScreen screen, Coordinate2D location) {
		super(location);
		this.screen = screen;
		setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
		setFill(Color.WHITE);
		name = screen.getName();
		setText("Enter your name: " + name);
	}

	public void setNameText() {
		var nameString = "YOUR NAME: " + name;
		setText(nameString);
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.BACK_SPACE) && name.length()>0) {
			name = name.substring(0, (name.length() - 1));
		} else {
			if (name.length() < 9) {
				for (KeyCode code : pressedKeys) {
					if (code.isLetterKey())
						name = name + code;
				}
			}
		}
		screen.setName(name);
		System.out.println(name);
	}

	@Override
	public void setupTimers() {
		addTimer(new NameUpdater(this, 50, screen));
	}

}
