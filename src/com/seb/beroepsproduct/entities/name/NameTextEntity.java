package com.seb.beroepsproduct.entities.name;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.seb.beroepsproduct.scenes.EndGameScreen;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NameTextEntity extends DynamicTextEntity{
	
	private EndGameScreen screen;
	
	public NameTextEntity(EndGameScreen screen, Coordinate2D location) {
		super(location);
		this.screen = screen;
		setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
		setFill(Color.BLACK);
		setText("test");
		
	}
	

}
