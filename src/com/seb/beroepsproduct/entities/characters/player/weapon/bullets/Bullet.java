package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.CircleEntity;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.seb.beroepsproduct.entities.characters.Enemy;
import com.seb.beroepsproduct.entities.characters.player.Player;

import javafx.scene.paint.Color;

public class Bullet extends DynamicCircleEntity implements TimerContainer, Collider, Collided{

	private int damage;
	
	protected Bullet(Player player, Coordinate2D initialLocation, double speed, int bulletOffset) {
		super(initialLocation);
		setRadius(10);
		setFill(Color.YELLOW);
		setSpeed(speed);
		var totalBulletAngle = 15 * player.getPlayerLevel();;
		var direction = player.getDirectionPlayer()-(totalBulletAngle/2)+( (bulletOffset+1)*15);
		setDirection(direction+90);
		setupTimers();
		// TODO Auto-generated constructor stub
		
		this.damage = 3;
	}

	@Override
	public void setupTimers() {
		// TODO Auto-generated method stub
		var bullettimer = new BulletTimer(this, 300);
		addTimer(bullettimer);
	}
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Enemy)
		remove();
		
	}

}
