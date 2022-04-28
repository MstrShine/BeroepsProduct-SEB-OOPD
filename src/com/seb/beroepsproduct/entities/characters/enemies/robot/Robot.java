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
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.health.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;
import com.seb.beroepsproduct.scenes.GameScreen;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Robot extends Enemy {

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
	public void hit(int damage) {
		this.health -= damage;
		if (this.health <= 0 && this.isVisible()) {
			player.setScore(player.getScore() + 100 + (((int) this.getEnemyLevel() - 1) * 10));
			this.die();
			System.out.println("" + player.getScore());
		}
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Toxic) {
		}
		if (collidingObject instanceof Rock) {
			this.changeDirection(180);
			this.setSpeed(1);

		}
		if (collidingObject instanceof Bullet) {
			var bullet = (Bullet) collidingObject;
			if (bullet.getCharacter() instanceof Player) {
				hit(bullet.getDamage());
				text.setHealthText();
			}
		}
	}

	@Override
	protected void setupEntities() {
		double rotationSpeed = -5 + Math.random() * 10;
		var robotSprite = new RobotSprite("sprites/fire2.gif", new Coordinate2D(-50, -50), new Size(100, 100),
				rotationSpeed);
		addEntity(robotSprite);
		this.text = new CharacterHealthText(this, new Coordinate2D(-40, -60));
		addEntity(this.text);
	}

	@Override
	public void setupTimers() {
		this.createEnemyTimer();
	}
}
