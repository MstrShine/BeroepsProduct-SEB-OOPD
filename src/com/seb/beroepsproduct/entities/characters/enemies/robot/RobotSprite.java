package com.seb.beroepsproduct.entities.characters.enemies.robot;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class RobotSprite extends DynamicSpriteEntity {

	public RobotSprite(String resource, Coordinate2D initialLocation, Size size, double rotationSpeed) {
		super(resource, initialLocation, size, 1, 9);
		setOpacity(0.7);
		setAutoCycle(50);
		setRotationSpeed(rotationSpeed);
	}

}
