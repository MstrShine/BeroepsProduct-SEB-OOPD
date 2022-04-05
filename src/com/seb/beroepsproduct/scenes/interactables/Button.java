package com.seb.beroepsproduct.scenes.interactables;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class Button extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {

	/**
	 * Creates a button at given location with given text
	 * @param initialLocation sets location on screen
	 * @param text text to display on button
	 * @param color set body color of button
	 * @param fontSize sets font size of text
	 */
	public Button(Coordinate2D initialLocation, String text, Color color, String fontFamily, int fontSize) {
		super(initialLocation, text);
		setFill(color);
		setFont(Font.font(fontFamily, FontWeight.BOLD, fontSize));
	}

}
