package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.seb.beroepsproduct.entities.characters.health.CharacterHealthText;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.Bullet;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.scenes.GameScreen;

public class Zombie extends Enemy{
	
	protected GameScreen screen;
	protected double speed;
	protected double direction;

		public Zombie(Coordinate2D location, Player player, int health, int damage, GameScreen screen) {
		super(location, new Size(100,100), player, health, damage, screen);
		this.screen = screen;
		this.damage = 10;
		this.player = player;
		this.speed = 1;
		this.direction = 0;
	}

		@Override
		public void hit(int damage) {
			this.health -= damage;
			if (this.health <= 0 && this.isVisible()) {
				player.setScore(player.getScore() + 100 + (((int) this.getLevel() - 1) * 10));
				this.die();
				System.out.println("" + player.getScore());
			}
		}

		@Override
		public void onCollision(Collider collidingObject) {
			if (collidingObject instanceof Toxic) {}
			if (collidingObject instanceof Rock) {}
			if (collidingObject instanceof Bullet) {
				var bullet = (Bullet) collidingObject;
				if (bullet.getCharacter() instanceof Player) {
					hit(bullet.getDamage());
					text.setHealthText();
				}
			}
		}

		@Override
		public void setupTimers() {
		}

		@Override
		protected void move(double direction) {
			setMotion(this.speed, direction);
			System.out.println("2. de snelheid is: " + speed);
		}
		
		
		@Override
		protected void setupEntities() {
			var zombieSprite = new ZombieSprite("sprites/zombie.gif", new Coordinate2D(-50, -50), new Size(100, 100), 1, 2);
			addEntity(zombieSprite);
			this.text = new CharacterHealthText(this, new Coordinate2D(-40, -60));
			addEntity(this.text);
		}
		
		public void update(long timestamp) {
			setRotate(this.angleTo(player)-180);
			direction = this.angleTo(player);
			speed = 5;
			System.out.println("1. de snelheid is: " + speed);
			move(direction);
			
			//setMotion(speed, getDirection());
		}
}
