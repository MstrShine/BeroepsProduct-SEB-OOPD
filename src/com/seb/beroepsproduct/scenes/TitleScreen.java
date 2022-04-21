package com.seb.beroepsproduct.scenes;

import com.seb.beroepsproduct.Main;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.seb.beroepsproduct.scenes.interactables.buttons.SceneChangeButton;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitleScreen extends StaticScene {

	private Main main;

	private static final String TITLE = "Escaping Pluto";

	public TitleScreen(Main main) {
		this.main = main;
	}

	@Override
	public void setupScene() {

	}

	@Override
	public void setupEntities() {
		var titleEntity = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 5), TITLE);
		titleEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		titleEntity.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 80));
		addEntity(titleEntity);

		var startButton = new SceneChangeButton(new Coordinate2D(getWidth() / 2, getHeight() / 2), "Start Game",
				Color.BLUE, Color.PURPLE, "Roboto", 30, 1, main);
		startButton.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
		addEntity(startButton);
	}

}
