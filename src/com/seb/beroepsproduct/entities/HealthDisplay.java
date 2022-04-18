package com.seb.beroepsproduct.entities;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class HealthDisplay extends DynamicCompositeEntity implements TimerContainer{
	
	private Player player;
	protected ArrayList<HeartSprite> hearts = new ArrayList<HeartSprite>();

	public HealthDisplay(Coordinate2D initialLocation, Player player) {
		super(initialLocation);
		this.player = player;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setupEntities() {
		for (int i = 0; i<player.getMaxHealth(); i++) {
			System.out.println(""+player.getMaxHealth());
			//var heartSprite = new HeartSprite(new Coordinate2D (this.getAnchorLocation().getX()+180*i, this.getAnchorLocation().getY()));
			var heartSprite = new HeartSprite(new Coordinate2D (0+i*180,0), player, i);
			hearts.add(heartSprite);
		}
		for (HeartSprite hrt : hearts) {addEntity(hrt);}
	}

	@Override
	public void setupTimers() {
		addTimer (new HealthDisplayUpdater(this, 50, player));
		// TODO Auto-generated method stub
		
	}

}
