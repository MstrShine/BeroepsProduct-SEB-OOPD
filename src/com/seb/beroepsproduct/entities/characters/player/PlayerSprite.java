package com.seb.beroepsproduct.entities.characters.player;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class PlayerSprite extends SpriteEntity{

	protected PlayerSprite(String resource, Coordinate2D initialLocation, int rotation, Size size) {
		super(resource, initialLocation, size);
		setRotate(rotation);
		// TODO Auto-generated constructor stub
	}

}
