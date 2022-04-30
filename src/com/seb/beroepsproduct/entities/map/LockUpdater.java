package com.seb.beroepsproduct.entities.map;

import com.github.hanyaeger.api.Timer;

/**
 *  A updater for {@link Lock} that checks on a certain interval
 */
public class LockUpdater extends Timer{
	
	private Lock lock;
	
	/**
	 * Creates a LockUpdater for checking if the {@link Player} has a key on a certain interval in milliseconds 
	 * @param lock The {@link Lock} that needs checking
	 * @param intervalInMs The interval for checking in milliseconds
	 */
	public LockUpdater(Lock lock, long intervalInMs) {
		super(intervalInMs);
		this.lock = lock;
	}
	
	@Override
	public void onAnimationUpdate(long timestamp) {
		lock.updateLockVisible();
	}

}
