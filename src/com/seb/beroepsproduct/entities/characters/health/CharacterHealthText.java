package com.seb.beroepsproduct.entities.characters.health;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.seb.beroepsproduct.entities.characters.Character;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CharacterHealthText extends DynamicTextEntity {

	// private String robotHealth;
	private Character character;
	// private String initialHealth;

	public CharacterHealthText(Character character, Coordinate2D initialLocation) {
		super(initialLocation);
		this.character = character;
		// this.initialHealth = character.GetHealth();
		setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
		setFill(Color.YELLOW);
		setHealthText();
	}

	public void update() {
		setHealthText();
	}

	public void setHealthText() {
		setText(character.getHealth() + " / " + character.getMaxHealthString());
	}

}
