package com.seb.beroepsproduct.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletShooter;
import com.seb.beroepsproduct.entities.map.Door;

public class GameScreen extends DynamicScene implements EntitySpawnerContainer {

	public Player player1;
	//public Robot robot;
	
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	private int nRobots = 3; 
	
	

	@Override
	public void setupScene() {
		// TODO Auto-generated method stub
		setBackgroundImage("sprites/map.png", true);
	}

	@Override
	public void setupEntities() {
		var player = new Player(new Coordinate2D(getWidth() / 2, getHeight() / 2), 100, 3);
		player1 = player;
		addEntity(player1);

		var door = new Door(new Coordinate2D(getWidth() - 90, getHeight() / 2), new Size(60, 90), 270);
		addEntity(door);
/*
		var robot = new Robot(pickEnemyLocation(player1), player1, 500, 10);
		// var robot = new Robot(new Coordinate2D(100,100), player1, 500);
		this.robot = robot;
		addEntity(robot);
*/
		
		for (int i = 0; i<nRobots; i++) {
			var robot = new Robot(pickEnemyLocation(player1), player1, 500, 10);
			robots.add(robot);
		}
		for (Robot rbt : robots) {addEntity(rbt);}
	}

	@Override
	public void setupEntitySpawners() {
		var shooter = new BulletShooter(player1, 20);
		addEntitySpawner(shooter);
/*
		var shooter2 = new BulletShooter(robot, 500);
		addEntitySpawner(shooter2);
*/ 		
		for (Robot rbt : robots) {
			var shooter2 = new BulletShooter(rbt, 500);
			addEntitySpawner(shooter2);
		}
		
		
		
		//var eSpawner = new EnemySpawner(player1, robot, pickEnemyLocation(player1), 500, 10, 3);
		//addEntitySpawner(eSpawner);
	}

	private Coordinate2D pickEnemyLocation(Player player) {
		var xCoord = Math.random() * getWidth();
		var yCoord = Math.random() * getHeight();
		Coordinate2D tempCoord = new Coordinate2D(xCoord, yCoord);
		return tempCoord;
	}

}
