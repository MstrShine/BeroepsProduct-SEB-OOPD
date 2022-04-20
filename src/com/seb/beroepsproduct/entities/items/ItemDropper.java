package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;

public class ItemDropper extends EntitySpawner{
	private Robot robot;
	private boolean itemDropped;
	 
	public ItemDropper(Robot rbt, long intervalInMs) {
		super(intervalInMs);
		this.robot = rbt;
		itemDropped = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void spawnEntities() {
		if (!robot.isVisible() && !itemDropped) {
			var tempLoc = new Coordinate2D(robot.getAnchorLocation().getX()-50, robot.getAnchorLocation().getY()-50);
			spawn(new MaxHealthItem("sprites/maxHealth.gif", tempLoc, this));
			itemDropped = true;
		}
		// TODO Auto-generated method stub
		
	}

	public void setItemDropped(boolean itemDropped) {
		this.itemDropped = itemDropped;
	}

}
