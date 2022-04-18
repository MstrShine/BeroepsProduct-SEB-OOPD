package com.seb.beroepsproduct.entities.characters.enemies.robot;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.seb.beroepsproduct.entities.characters.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Robot extends Enemy {

	protected int damage;
	protected Player player;
	
	public Robot(Coordinate2D spawnlocation, Player player, int health, int damage) {
		super(spawnlocation, new Size(10, 10), player, health, damage);
		this.damage = 10;
		this.player = player;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public void Hit(int damage) {
		this.health -= damage;
		if (this.health <= 0 && this.isVisible()) {
			player.setScore(player.getScore()+ 100+( ( (int)this.getLevel()-1)*50) );
			this.Die();
			System.out.println(""+player.getScore());
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
			var bullet = (Bullet) collidingObject;
				if (bullet.character instanceof Player) {
					Hit(bullet.getDamage());
					text.update();
				}
		}
	}

	@Override
	protected void setupEntities() {
		var robotSprite = new RobotSprite("sprites/Robot.gif", new Coordinate2D(-75,-75), new Size(150,150));
		addEntity(robotSprite);
		/*
		 * var robotText = new TextEntity( this.getLocationInScene() //new
		 * Coordinate2D(getWidth() / 2, getHeight() / 2) , ""+this.health );
		 * //robotText.setAnchorPoint(AnchorPoint.TOP_CENTER);
		 * robotText.setFill(Color.DARKBLUE); robotText.setFont(Font.font("Roboto",
		 * FontWeight.SEMI_BOLD, 80)); addEntity(robotText);
		 */
		// var textRobot = new textEntity(this, this.getAnchorLocation());
		this.text = new CharacterHealthText(this, new Coordinate2D(0,-75));
		addEntity(this.text);
	}

	@Override
	public String GetHealth() {
		var string = "" + this.health;
		return string;
	}

	@Override
	public void setupTimers() {
		this.createEnemyTimer();
	}
}
