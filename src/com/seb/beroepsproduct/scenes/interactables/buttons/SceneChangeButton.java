package com.seb.beroepsproduct.scenes.interactables.buttons;

import com.seb.beroepsproduct.Main;

import com.github.hanyaeger.api.Coordinate2D;
import com.seb.beroepsproduct.scenes.interactables.Button;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class SceneChangeButton extends Button {

	private Main main;
	private int sceneId;

	/**
	 * Creates a button on given location with given text in color for changing scenes
	 * 
	 * @param initialLocation sets location on screen
	 * @param text            text to display on button
	 * @param initialColor    set text color of button
	 * @param hoverColor      set hover color of text
	 * @param fontFamily      font for text, if null gets default font
	 * @param fontSize        size of font, if 0 or null gets default size
	 * @param sceneToChangeTo id of scene where to change to
	 * @param main	          main class that handles the scenes
	 */
	public SceneChangeButton(Coordinate2D initialLocation, String text, Color initialColor, Color hoverColor,
			String fontFamily, int fontSize, int sceneToChangeTo, Main main) {
		super(initialLocation, text, initialColor, hoverColor, fontFamily, fontSize);
		this.main = main;
		this.sceneId = sceneToChangeTo;
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		main.setActiveScene(sceneId);
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
