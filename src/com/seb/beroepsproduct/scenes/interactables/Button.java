package com.seb.beroepsproduct.scenes.interactables;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class Button extends TextEntity
		implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {

	private static final String DEFAULT_FONT_FAMILY = "Roboto";
	private static final int DEFAULT_FONT_SIZE = 30;

	protected Color initialColor;
	protected Color hoverColor;

	/**
	 * Creates a button at given location with given text
	 * 
	 * @param initialLocation sets location on screen
	 * @param text            text to display on button
	 * @param initialColor    set text color of button
	 * @param hoverColor      set hover color of text
	 * @param fontFamily      font for text, if null gets default font
	 * @param fontSize        sets font size of text, if 0 or null gets default size
	 */
	public Button(Coordinate2D initialLocation, String text, Color initialColor, Color hoverColor, String fontFamily,
			int fontSize) {
		super(initialLocation, text);

		this.initialColor = initialColor;
		this.hoverColor = hoverColor;

		setFill(initialColor);
		setFont(Font.font(fontFamily == null ? DEFAULT_FONT_FAMILY : fontFamily, FontWeight.BOLD,
				fontSize == 0 ? DEFAULT_FONT_SIZE : fontSize));
	}

}
