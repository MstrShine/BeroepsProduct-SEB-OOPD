package com.seb.beroepsproduct.entities.characters.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.seb.beroepsproduct.entities.characters.Enemy;

public class Robot extends Enemy {

	public Robot(int health, int speed) {
		super("", new Coordinate2D(), new Size(10, 10), health);
		this.damage = 10;
	}
	
	@Override
	public int getDamage() {
		return damage;
	}
	
	@Override
	public void Hit(int damage) {
		this.health -= damage;
	}

	@Override
	public void Move(double direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Move(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollision(Collider collidingObject) {
		// TODO Auto-generated method stub
		
	}
}
