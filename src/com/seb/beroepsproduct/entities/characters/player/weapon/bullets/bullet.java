package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.CircleEntity;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

import javafx.scene.paint.Color;

public class bullet extends DynamicCircleEntity implements TimerContainer, Collider{

	protected bullet(Player player, Coordinate2D initialLocation, double speed, int i) {
		super(initialLocation);
		setRadius(10);
		setFill(Color.YELLOW);
		setSpeed(speed);
		var totalBulletAngle = 15 * player.getPlayerLevel();;
		var direction = player.getDirectionPlayer()-(totalBulletAngle/2)+( (i+1)*15);
		setDirection(direction+90);
		setupTimers();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setupTimers() {
		// TODO Auto-generated method stub
		var bullettimer = new BulletTimer(this, 300);
		addTimer(bullettimer);
	}
	


}
