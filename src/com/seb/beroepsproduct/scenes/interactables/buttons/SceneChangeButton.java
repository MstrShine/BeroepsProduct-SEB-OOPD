package com.seb.beroepsproduct.scenes.interactables.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.scenes.interactables.Button;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class SceneChangeButton extends Button {

	private Main main;
	private int sceneId;
	
	public SceneChangeButton(Coordinate2D initialLocation, String text, Color color, String fontFamily, int fontSize, int sceneToChangeTo, Main main) {
		super(initialLocation, text, color, fontFamily, fontSize);
		this.main = main;
		this.sceneId = sceneToChangeTo;
	}

	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		main.setActiveScene(sceneId);
	}

	@Override
	public void onMouseEntered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseExited() {
		// TODO Auto-generated method stub
		
	}
}
