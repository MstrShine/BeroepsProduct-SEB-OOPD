package com.seb.beroepsproduct.entities.characters.player.weapon;

import java.util.List;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class WeaponSprite extends SpriteEntity {
	private int rotation;

	public WeaponSprite(String resource, Coordinate2D initialLocation, int rotation) {
		super(resource, initialLocation);
		this.rotation = rotation;
		setRotate(rotation);
	}

}
