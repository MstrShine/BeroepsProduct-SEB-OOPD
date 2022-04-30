package com.seb.beroepsproduct.entities.items;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.seb.beroepsproduct.entities.characters.player.Player;

/**
 * A basic item that an {@link Enemy} can drop for the {@link Player}
 */
abstract class Item extends DynamicSpriteEntity implements TimerContainer, Collided {

	private ItemDropper itemDropper;
	protected Player player;

	/**
	 * Creates a basic item that an {@link Enemy} could drop for the {@link Player}
	 * @param resource The path to the sprite on the PC
	 * @param initialLocation The location on the scene
	 * @param itemDropper That handles item dropping
	 * @param visible Sets the visibility of the item
	 * @param player The current {@link Player} that could take the {@link Item}
	 */
	public Item(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible,
			Player player) {
		super(resource, initialLocation, new Size(100, 100));
		this.itemDropper = itemDropper;
		this.player = player;

		setVisible(visible);
	}

	/**
	 * Sets up {@link ItemTimer} for {@link Item}
	 */
	@Override
	public void setupTimers() {
		addTimer(new ItemTimer(this, 8000));
	}

	/**
	 * Non-overrideable function to check if {@link Player} is colliding with
	 * {@link Item} and item is visible
	 * 
	 * @param collidingObject object that collided with item
	 * @return true if {@link Collider} is {@link Player} and {@link Item} is visible
	 */
	protected final boolean collisionCheckPlayer(Collider collidingObject) {
		return (collidingObject instanceof Player && isVisible());
	}
	
	/**
	 * Gets the current {@link ItemDropper} of the {@link Item}
	 * @return
	 */
	public final ItemDropper getItemDropper() {
		return this.itemDropper;
	}
}
