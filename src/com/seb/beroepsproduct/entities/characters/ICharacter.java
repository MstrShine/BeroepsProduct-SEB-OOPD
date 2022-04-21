package com.seb.beroepsproduct.entities.characters;

import com.github.hanyaeger.api.entities.Direction;

public interface ICharacter {

	public void Hit(int damage);

	public void Move(double direction);

	public void Move(Direction direction);

	public String GetHealth();
}
