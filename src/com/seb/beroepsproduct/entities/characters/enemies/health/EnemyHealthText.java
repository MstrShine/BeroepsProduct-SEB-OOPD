package com.seb.beroepsproduct.entities.characters.enemies.health;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import com.seb.beroepsproduct.entities.characters.enemies.Enemy;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A sprite to display health of a {@link Enemy}
 */
public class EnemyHealthText extends DynamicTextEntity {

	private Enemy enemy;

	/**
	 * Creates a sprite for an {@link Enemy} to display his current health
	 * 
	 * @param enemy           The {@link Enemy} of which the health needs to display
	 * @param initialLocation The location in the scene
	 */
	public EnemyHealthText(Enemy enemy, Coordinate2D initialLocation) {
		super(initialLocation);
		this.enemy = enemy;

		setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
		setFill(Color.YELLOW);
		updateText();
	}

	/**
	 * Updates the text of the {@link Enemy} health
	 */
	public void updateText() {
		setText(enemy.getHealth() + " / " + enemy.getMaxHealthString());
	}
}
