package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.seb.beroepsproduct.entities.characters.Enemy;
import com.seb.beroepsproduct.entities.characters.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Robot extends Enemy {
	
	public Robot(int health, int speed) {
		super(new Coordinate2D(), new Size(10, 10), health);
		this.damage = 10;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public void Hit(int damage) {
		this.health -= damage;
		if(this.health <= 0) {
			this.Die();
		}
	}

	@Override
	public void Move(double direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Move(Direction direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Bullet) {
			var bullet = (Bullet)collidingObject;
			Hit(bullet.getDamage());
			text.update();
		}
	}

	@Override
	protected void setupEntities() {
		var robotSprite = new RobotSprite("sprites/Robot.gif", new Coordinate2D(100, 200));
		addEntity(robotSprite);
		/*
		 * var robotText = new TextEntity( this.getLocationInScene() //new
		 * Coordinate2D(getWidth() / 2, getHeight() / 2) , ""+this.health );
		 * //robotText.setAnchorPoint(AnchorPoint.TOP_CENTER);
		 * robotText.setFill(Color.DARKBLUE); robotText.setFont(Font.font("Roboto",
		 * FontWeight.SEMI_BOLD, 80)); addEntity(robotText);
		 */
		// var textRobot = new textEntity(this, this.getAnchorLocation());
		this.text = new CharacterHealthText(this, new Coordinate2D(100, 200));
		addEntity(this.text);
	}

	@Override
	public String GetHealth() {
		var string = "" + this.health;
		return string;
	}
}
