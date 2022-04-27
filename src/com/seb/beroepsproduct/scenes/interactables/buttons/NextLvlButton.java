package com.seb.beroepsproduct.scenes.interactables.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.scenes.GameScreen;
import com.seb.beroepsproduct.scenes.interactables.Button;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class NextLvlButton extends Button{
	
	private Main main;
	private GameScreen gameScreen;
	
	public NextLvlButton(Coordinate2D initialLocation, String text, Color initialColor, Color hoverColor,
			String fontFamily, int fontSize, Main main, GameScreen gameScreen) {
		super(initialLocation, text, initialColor, hoverColor, fontFamily, fontSize);
		this.main = main;
		this.gameScreen = gameScreen;
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		gameScreen.setupEntities();
		gameScreen.setupEntitySpawners();
		main.setActiveScene(1);
		
	}

	@Override
	public void onMouseEntered() {
		setCursor(Cursor.HAND);
		setFill(this.hoverColor);
	}

	@Override
	public void onMouseExited() {
		setCursor(Cursor.DEFAULT);
		setFill(this.initialColor);
	}

}
