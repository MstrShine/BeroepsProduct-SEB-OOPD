package com.seb.beroepsproduct.entities.characters.player.weapon;


import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class WeaponSprite extends SpriteEntity {

	public WeaponSprite(String resource, Coordinate2D initialLocation, int rotation) {
		super(resource, initialLocation);
		setRotate(rotation);
	}
}
