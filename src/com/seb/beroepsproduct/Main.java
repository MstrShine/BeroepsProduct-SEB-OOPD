package com.seb.beroepsproduct;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.seb.beroepsproduct.scenes.*;


public class Main extends YaegerGame {

	private final int WIDTH;
	private final int HEIGHT;


	public Main() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); // get info of
																										// main screen
		var x = gd.getDefaultConfiguration().getDefaultTransform().getScaleX();
		var y = gd.getDefaultConfiguration().getDefaultTransform().getScaleY();

		WIDTH = (int) (gd.getDisplayMode().getWidth() / x - 100);
		HEIGHT = (int) (gd.getDisplayMode().getHeight() / y - 100);
	}

	public static void main(String[] args) {
		launch(args);
		
		/*try {
		      File myObj = new File("highscores.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		    */
	}


	@Override
	public void setupGame() {
		setSize(new Size(WIDTH, HEIGHT));
	}

	@Override
	public void setupScenes() {
		var gs = new GameScreen(this);
		addScene(0, new TitleScreen(this));
		addScene(1, gs);
		addScene(2, new EndGameScreen(this, gs));
		addScene(3, new IntermissionScreen(this, gs));
	}

}
