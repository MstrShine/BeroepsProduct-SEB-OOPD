package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.seb.beroepsproduct.entities.characters.enemies.Robot;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class textEntity extends DynamicTextEntity {

	//private String robotHealth;
	private Robot robot;
	
	public textEntity(Robot robot, Coordinate2D initialLocation) {
		super(initialLocation);
	    setFont(Font.font("Roboto",FontWeight.NORMAL, 30));
	    setFill(Color.YELLOW);
	    //this.robot = robot;
	    //robotHealth = ""+robot.health;
	    setText(robot.robotHealth);
	    // TODO Auto-generated constructor stub
	}
	
	  void update(){
		setText(robot.robotHealth);
		System.out.println("ik update nu");
	}
	
}
