package com.seb.beroepsproduct;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.seb.beroepsproduct.scenes.*;

public class Main extends YaegerGame {

	private final int WIDTH;
	private final int HEIGHT;
	
	public Main() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); // get info of main screen
		WIDTH = (int)(gd.getDisplayMode().getWidth()*0.66 - 100);
		HEIGHT = (int)(gd.getDisplayMode().getHeight()*0.66 - 100);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void setupGame() {
		setSize(new Size(WIDTH, HEIGHT));
	}

	@Override
	public void setupScenes() {
		addScene(0, new TitleScreen(this));
		addScene(1, new GameScreen());
		addScene(2, new EndGameScreen());
	}
	
}
