package com.seb.beroepsproduct.entities.characters.player.health;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * A class to display the current health and maximum health of a {@link Player}
 */
public class HealthDisplay extends EntitySpawner {

	private Player player;
	private ArrayList<HeartSprite> hearts;

	/**
	 * Creates a {@link HealthDisplay} for showing {@link Player} current health and maximum health
	 * @param refreshTime The time in milliseconds for refreshing sprites
	 * @param player The {@link Player} of which the health needs to be displayed
	 */
	public HealthDisplay(long refreshTime, Player player) {
		super(refreshTime);
		this.player = player;
		this.hearts = new ArrayList<HeartSprite>();
	}

	/**
	 * First clears the hearts and then respawns them again with the correct sprite on the screen
	 */
	@Override
	public void spawnEntities() {
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
	
	/**
	 * Gets the list of {@link HeartSprite}s
	 * @return Current {@link HeartSprite} list
	 */
	public ArrayList<HeartSprite> getHeartList(){
		return this.hearts;
	}
	
	/**
	 * Clears the current {@link HeartSprite} list
	 */
	public void clearHeartList() {
		this.hearts.clear();
	}
}
