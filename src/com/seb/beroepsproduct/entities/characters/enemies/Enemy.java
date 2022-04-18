package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletTimer;

public abstract class Enemy extends Character implements Collided, TimerContainer {

	protected int damage;
	protected double level;
	private Player player;

	public Enemy(Coordinate2D location, Size size, Player player, int health, int damage) {
		super(location, size, health);
		this.player = player;
		this.damage = damage;
		setSpeed(0.5);
		setDirection(90);
		level = 1;
	}

	abstract public int getDamage();


	protected void createEnemyTimer() {
		addTimer(new EnemyTimer(this, this.player, 50));
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = 1 + Math.floor(player.getScore()/100);
		//elke 100 punten die een speler haalt worden de tegenstanders sterker (1 level erbij)
	}
	

}
