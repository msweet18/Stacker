package edu.msu.BlueSky.stacker;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;

public class Game {
	/**
	 * Collection of Bricks
	 */
	public ArrayList<Brick> bricks = new ArrayList<Brick>();
	
	private GameView gameView;

	public Game(Context context, GameView view){
		gameView = view;
		bricks.add(new Brick(context, true));
	}
	
	public void draw(Canvas canvas){
		for(Brick brick : bricks){
			brick.draw(canvas);
		}
	}
}