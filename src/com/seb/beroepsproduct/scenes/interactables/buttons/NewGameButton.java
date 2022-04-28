package com.seb.beroepsproduct.scenes.interactables.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.scenes.EndGameScreen;
import com.seb.beroepsproduct.scenes.GameScreen;
import com.seb.beroepsproduct.scenes.interactables.Button;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class NewGameButton extends Button{
	
	private EndGameScreen screen;
	private Main main;
	private GameScreen gs;

	public NewGameButton(Main main, EndGameScreen screen, GameScreen gs, Coordinate2D initialLocation, String text, Color initialColor, Color hoverColor,
			String fontFamily, int fontSize) {
		super(initialLocation, text, initialColor, hoverColor, fontFamily, fontSize);
		this.main = main;
		this.screen = screen;
		this.gs = gs;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		screen.saveHighScores();
		main.setActiveScene(0);
		gs.setLevel(1);
		gs.player1.setScore(0);
		gs.player1.remove();
		gs.setupEntities();
		gs.setupEntitySpawners();
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
