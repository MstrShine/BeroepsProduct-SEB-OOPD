package com.seb.beroepsproduct.entities.characters.player.health;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.enemies.robot.Robot;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class HealthDisplay extends EntitySpawner {

	private Player player;
	protected ArrayList<HeartSprite> hearts = new ArrayList<HeartSprite>();

	public HealthDisplay(long refreshTime, Player player) {
		super(refreshTime);
		this.player = player;
	}

	@Override
	protected void spawnEntities() {
		for (var heart : hearts)
			heart.remove();

		hearts.clear();
		for (int i = 0; i < player.getMaxHealth(); i++) {
			var heartSprite = new HeartSprite(new Coordinate2D(150 + i * 40, 40), player, i, new Size(40, 40));
			hearts.add(heartSprite);
		}

		for (HeartSprite hrt : hearts) {
			spawn(hrt);
		}
	}
}
