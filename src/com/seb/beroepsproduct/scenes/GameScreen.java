package com.seb.beroepsproduct.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class GameScreen extends DynamicScene{

	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupEntities() {
		// TODO Auto-generated method stub
		var player = new Player(new Coordinate2D(getWidth() /2, getHeight() / 2), 100);
		addEntity(player);
	}

}
