package com.seb.beroepsproduct.entities.characters.enemies.zombie;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.seb.beroepsproduct.entities.characters.health.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.bullets.Bullet;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;
import com.seb.beroepsproduct.scenes.GameScreen;

public class Zombie extends Enemy {

	public Zombie(Coordinate2D location, Player player, int health, int damage, GameScreen screen) {
		super(location, new Size(100, 100), player, health, damage, screen);
		this.screen = screen;
		this.damage = 10;
		this.player = player;
		var newSpeed =  0.6 + (getEnemyLevel() * 0.2);
		this.speed = newSpeed < 2.5 ? newSpeed : 2.5;
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
			var bullet = (Bullet) collidingObject;
			if (bullet.getCharacter() instanceof Player) {
				hit(bullet.getDamage());
			}
		}
	}

	@Override
	public void setupTimers() {
		this.createEnemyTimer();
	}

	@Override
	protected void setupEntities() {
		var zombieSprite = new ZombieSprite("sprites/zombie.gif", new Coordinate2D(0,0), new Size(80, 80), 1, 2);
		zombieSprite.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(zombieSprite);
		this.text = new CharacterHealthText(this, new Coordinate2D(-40, -60));
		addEntity(this.text);
	}
}
