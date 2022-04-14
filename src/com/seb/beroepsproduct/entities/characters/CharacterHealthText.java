package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.seb.beroepsproduct.entities.characters.enemies.Robot;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CharacterHealthText extends DynamicTextEntity {

	//private String robotHealth;
	private Character character;
	
	public CharacterHealthText(Character character, Coordinate2D initialLocation) {
		super(initialLocation);
		this.character = character;
	    setFont(Font.font("Roboto",FontWeight.NORMAL, 30));
	    setFill(Color.YELLOW);
	    setText(character.GetHealth());
	}
	
	  public void update(){
		setText(this.character.GetHealth());
	}
	
}
