package com.seb.beroepsproduct.entities.characters.enemies.fireball;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.entities.characters.health.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.scenes.GameScreen;

public class Fireball extends Enemy {

	protected GameScreen screen;

	public Fireball(Coordinate2D spawnlocation, Player player, int health, int damage, GameScreen screen) {
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
		text.setHealthText();
		if (this.health <= 0 && this.isVisible()) {
			player.setScore(player.getScore() + 100 + (((int) this.getEnemyLevel() - 1) * 10));
			this.die();
		}
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Bullet) {
			//System.out.println("ik heb gebotst met een kogel");
			var bullet = (Bullet) collidingObject;
			if (bullet.getCharacter() instanceof Player) {
				hit(bullet.getDamage());
			}
		}
		if (collidingObject instanceof Rock) {
			//System.out.println("ik heb gebotst met een rots");
			this.changeDirection(180);
			this.setSpeed(1);
		}
	}

	@Override
	protected void setupEntities() {
		double rotationSpeed = -5 + Math.random() * 10;
		var fireballSprite = new FireballSprite("sprites/fire2.gif", new Coordinate2D(-40, -40), new Size(80, 80),
				rotationSpeed);
		addEntity(fireballSprite);
		this.text = new CharacterHealthText(this, new Coordinate2D(-40, -60));
		addEntity(this.text);
	}

	@Override
	public void setupTimers() {
		this.createEnemyTimer();
	}
}
