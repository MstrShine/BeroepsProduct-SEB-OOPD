package com.seb.beroepsproduct.entities.characters.bullets;

import com.github.hanyaeger.api.Timer;

/**
 * A {@link Timer} that despawns the {@link Bullet} after a certain amount of time
 */
public class BulletTimer extends Timer {

	private Bullet bullet;

	/**
	 * Creates a {@link BulletTimer}
	 * @param bullet A {@link Bullet} that needs to be despawned
	 * @param intervalInMs The time in milliseconds for despawning the {@link Bullet}
	 */
	public BulletTimer(Bullet bullet, long intervalInMs) {
		super(intervalInMs);
		this.bullet = bullet;
	}

	/**
	 * When the time has run out on the interval the {@link Bullet} will despawn
	 */
	@Override
	public void onAnimationUpdate(long timestamp) {
		bullet.remove();
	}
}
