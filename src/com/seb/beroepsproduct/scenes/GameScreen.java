package com.seb.beroepsproduct.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.map.Door;

public class GameScreen extends DynamicScene{

	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("sprites/map.png", true);
	}

	@Override
	public void setupEntities() {
		var player = new Player(new Coordinate2D(getWidth() /2, getHeight() / 2), 100);
		addEntity(player);
		
		var door = new Door(new Coordinate2D(getWidth() - 90, getHeight() / 2), new Size(60, 90), 270);
		addEntity(door);
	}

}
