package com.seb.beroepsproduct.scenes;

import com.github.hanyaeger.api.scenes.StaticScene;

public class EndGameScreen extends StaticScene {

	private GameScreen gameScreen;
	private int endScore;
	
	public EndGameScreen(GameScreen gs) {
		this.gameScreen = gs;
		//endScore = gs.getScore();
	}

	@Override
	public void setupScene() {
	}

	@Override
	public void setupEntities() {
	}

}
