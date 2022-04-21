package com.seb.beroepsproduct.entities.items;

import java.util.Optional;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

import javafx.scene.Node;

public class HealthItem extends DynamicSpriteEntity {

	protected ItemDropper itemDropper;

	protected HealthItem(String resource, Coordinate2D initialLocation, ItemDropper itemDropper, boolean visible) {
		super(resource, initialLocation, new Size(100, 100));
		this.itemDropper = itemDropper;
		setVisible(visible);
	}
}