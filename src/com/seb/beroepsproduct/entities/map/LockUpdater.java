package com.seb.beroepsproduct.entities.map;

import com.github.hanyaeger.api.Timer;

public class LockUpdater extends Timer{
	
	private Lock lock;
	
	protected LockUpdater(Lock lock, long intervalInMs) {
		super(intervalInMs);
		this.lock = lock;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onAnimationUpdate(long timestamp) {
		lock.updateLockVisible();
	}

}
