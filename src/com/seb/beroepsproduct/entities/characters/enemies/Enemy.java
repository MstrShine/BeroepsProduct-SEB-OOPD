package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.scenes.YaegerScene;
import com.seb.beroepsproduct.entities.characters.Character;
import com.seb.beroepsproduct.entities.characters.player.Player;
import com.seb.beroepsproduct.entities.characters.player.weapon.bullets.BulletTimer;
import com.seb.beroepsproduct.scenes.GameScreen;

public abstract class Enemy extends Character implements Collided, TimerContainer {

	protected int damage;
	protected double level;
	private Player player;
	protected GameScreen screen;

	public Enemy(Coordinate2D location, Size size, Player player, int health, int damage, GameScreen screen) {
		super(location, size, health);
		this.player = player;
		this.damage = damage;
		this.screen = screen;
		level = 1;
	}

	abstract public int getDamage();

	protected void createEnemyTimer() {
		addTimer(new EnemyTimer(this, this.player, 50, screen));
	}

	public double getLevel() {
		return level;
	}

	public void setLevel() {
		this.level = 1 + Math.floor(player.getScore() / 200);
		// elke 200 punten die een speler haalt worden de tegenstanders sterker (1 level
		// erbij)
	}

}
