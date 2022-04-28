package com.seb.beroepsproduct.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class Lock extends DynamicSpriteEntity implements TimerContainer{
	
	private Player player;

	public Lock(Player player, String resource, Coordinate2D initialLocation, Size size) {
		super(resource, initialLocation, size);
		this.player = player;
		setVisible(!player.isPlayerHasKey());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setupTimers() {
		var lockUpdater = new LockUpdater(this, 500);
		addTimer(lockUpdater);
	}
	
	protected void updateLockVisible(){
		setVisible(!player.isPlayerHasKey());
	}

}
