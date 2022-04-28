package com.seb.beroepsproduct.scenes;

import com.seb.beroepsproduct.Main;
import com.seb.beroepsproduct.entities.characters.enemies.fireball.FireballSprite;
import com.seb.beroepsproduct.entities.characters.enemies.zombie.ZombieSprite;
import com.seb.beroepsproduct.entities.characters.player.PlayerSprite;
import com.seb.beroepsproduct.entities.map.Door;
import com.seb.beroepsproduct.entities.obstacles.Rock;
import com.seb.beroepsproduct.entities.obstacles.Toxic;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.seb.beroepsproduct.scenes.interactables.buttons.SceneChangeButton;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitleScreen extends DynamicScene {

	private Main main;

	public TitleScreen(Main main) {
		this.main = main;
	}

	@Override
	public void setupScene() {
		setBackgroundImage("sprites/titleScreen.gif", true);
	}

	@Override
	public void setupEntities() {

		var startButton = new SceneChangeButton(new Coordinate2D(getWidth() / 2, 920), "LET'S DO THIS THING.",
				Color.RED, Color.GREEN, "Courier", 30, 1, main);
		startButton.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
		addEntity(startButton);

		var iconSize = new Size(50, 50);
		var hero = new PlayerSprite("sprites/player1v2.png", new Coordinate2D(100, 300), 90, iconSize);
		var fireball = new FireballSprite("sprites/fire2.gif", new Coordinate2D(100, 350), iconSize, 2);
		fireball.setOpacity(100);
		var zombie = new ZombieSprite("sprites/zombie.gif", new Coordinate2D(100, 400), iconSize, 1, 2);
		zombie.setOpacity(100);
		var toxic = new Toxic(new Coordinate2D(100, 450), iconSize);
		var rock = new Rock(new Coordinate2D(100, 500), iconSize);
		var door = new Door(new Coordinate2D(110, 550), iconSize, 270);
		var key = new PlayerSprite("sprites/keyGif.gif", new Coordinate2D(100, 600), 0, iconSize);
		var max = new PlayerSprite("sprites/maxHealth.gif", new Coordinate2D(100, 650), 0, iconSize);
		var health = new PlayerSprite("sprites/health.gif", new Coordinate2D(100, 700), 0, iconSize);
		var gun = new PlayerSprite("sprites/gunUpgrade.gif", new Coordinate2D(100,750), 0, iconSize);
		addEntity(hero);
		addEntity(fireball);
		addEntity(zombie);
		addEntity(toxic);
		addEntity(rock);
		addEntity(door);
		addEntity(key);
		addEntity(max);
		addEntity(health);
		addEntity(gun);

		String[] explanations = { "You -- Our hero. The chosen one. Or a pathetic wannabe...we'll see soon enough.",
				"Fire -- It hurts. Try not to touch it. We really shouldn't have to explain these things.",
				"Zombies -- The undead coming to make you one of their own. You better run.",
				"Toxic Gas -- You've heard of laughing gas? Well this stuff will make you cry.",
				"Rock -- Bullets and fireballs won't pass through this terrain, but zombies will!",
				"Door -- Will this door lead to freedom? Likely not. Give it a try anyways.",
				"Key -- A key can unlock a locked door. Duh.",
				"Increased max health -- Performance enhancing drugs are a-okay if you're fighting for your life.",
				"Health restored -- Just like brand new!",
				"Weapon level increased -- YEAH BABY! Gun goes brrrrrrrrrrrr!! MUAHAHahaha! Uhmm. Sorry." };

		for (int i = 0; i < explanations.length; i++) {
			var explanation = new TextEntity(new Coordinate2D(180, 320 + 50 * i), explanations[i]);
			explanation.setAnchorPoint(AnchorPoint.CENTER_LEFT);
			explanation.setFill(Color.WHITE);
			explanation.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
			addEntity(explanation);
		}
	}

}
