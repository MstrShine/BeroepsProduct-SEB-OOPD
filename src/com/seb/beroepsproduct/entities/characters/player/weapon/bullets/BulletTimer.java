package com.seb.beroepsproduct.entities.characters.player.weapon.bullets;

import com.github.hanyaeger.api.Timer;

public class BulletTimer extends Timer {

	private bullet bullet;
	
	protected BulletTimer(bullet bullet, long intervalInMs) {
		super(intervalInMs);
		this.bullet = bullet;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAnimationUpdate(long timestamp) {
			bullet.remove();
	}

}
