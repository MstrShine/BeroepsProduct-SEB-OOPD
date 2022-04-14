package com.seb.beroepsproduct.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.entities.characters.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletShooter;
import com.seb.beroepsproduct.entities.map.Door;

public class GameScreen extends DynamicScene implements EntitySpawnerContainer{

	public Player player1;
	
	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("sprites/map.png", true);
	}

	@Override
	public void setupEntities() {
		var player = new Player(new Coordinate2D(getWidth() /2, getHeight() / 2), 100, 3);
		player1 = player;
		addEntity(player1);
		
		var door = new Door(new Coordinate2D(getWidth() - 90, getHeight() / 2), new Size(60, 90), 270);
		addEntity(door);
		
		var robot = new Robot(500,0);
		addEntity(robot);
	}

	@Override
	public void setupEntitySpawners() {
		var shooter = new BulletShooter(player1, 20);
		addEntitySpawner(shooter);
	}

}
