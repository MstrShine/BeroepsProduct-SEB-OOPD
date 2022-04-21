package com.seb.beroepsproduct.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.entities.characters.player.Player;

public class Door extends SpriteEntity implements Collided {

	private boolean doorClosed;
	private Main main;

	public Door(Main main, Coordinate2D location, Size size, double rotation) {
		super("sprites/door.png", location, size);
		setRotate(rotation);
		doorClosed = true;
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Player) {
			if (doorClosed) {
			} // gebeurt niks bij door closed
			else {
				main.setActiveScene(2);
			} // als de deur open is dan gaan we naar een ander gamescreen
			System.out.println("hit player");
		} else {
			return;
		}
	}
}
