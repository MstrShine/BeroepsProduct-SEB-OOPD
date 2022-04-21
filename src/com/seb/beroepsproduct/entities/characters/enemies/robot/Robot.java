package com.seb.beroepsproduct.entities.characters.enemies.robot;

import java.util.List;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;
import com.seb.beroepsproduct.entities.characters.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;
import com.seb.beroepsproduct.scenes.GameScreen;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Robot extends Enemy {

	protected int damage;
	protected Player player;
	protected GameScreen screen;

	public Robot(Coordinate2D spawnlocation, Player player, int health, int damage, GameScreen screen) {
		super(spawnlocation, new Size(10, 10), player, health, damage, screen);
		this.screen = screen;
		this.damage = 10;
		this.player = player;
		setSpeed(0.5);
		setDirection(90);
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public void Hit(int damage) {
		this.health -= damage;
		if (this.health <= 0 && this.isVisible()) {
			player.setScore(player.getScore() + 100 + (((int) this.getLevel() - 1) * 10));
			this.Die();
			System.out.println("" + player.getScore());
		}
	}

	@Override
	public void Move(double direction) {
	}

	@Override
	public void Move(Direction direction) {
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Bullet) {
			var bullet = (Bullet) collidingObject;
			if (bullet.character instanceof Player) {
				Hit(bullet.getDamage());
				text.setHealthText();
			}
		}
	}

	@Override
	protected void setupEntities() {
		var robotSprite = new RobotSprite("sprites/Robot.gif", new Coordinate2D(-50, -50), new Size(100, 100));
		addEntity(robotSprite);
		this.text = new CharacterHealthText(this, new Coordinate2D(-40, -60));
		addEntity(this.text);
		/*
		 * var robotText = new TextEntity( this.getLocationInScene() //new
		 * Coordinate2D(getWidth() / 2, getHeight() / 2) , ""+this.health );
		 * //robotText.setAnchorPoint(AnchorPoint.TOP_CENTER);
		 * robotText.setFill(Color.DARKBLUE); robotText.setFont(Font.font("Roboto",
		 * FontWeight.SEMI_BOLD, 80)); addEntity(robotText);
		 */
		// var textRobot = new textEntity(this, this.getAnchorLocation());

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
