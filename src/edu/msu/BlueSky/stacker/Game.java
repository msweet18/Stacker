package edu.msu.BlueSky.stacker;

import java.util.ArrayList;

import android.R.string;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class Game {
	/**
	 * Collection of Bricks
	 */
	public ArrayList<Brick> bricks = new ArrayList<Brick>();
	
	//strings of the names...might make a player class if needed
	public string Player1;
	public string Player2;
	
	private GameView gameView;

	public Game(Context context, GameView view){
		gameView = view;
		bricks.add(new Brick(context, true));
	}
	
	public void draw(Canvas canvas){
		for(Brick brick : bricks){
			brick.draw(canvas, bricks.indexOf(brick));
		}
	}
	
	/**
     * Handle a touch event from the view.
     * @param view The view that is the source of the touch
     * @param event The motion event describing the touch
     * @return true if the touch is handled.
     */
    public boolean onTouchEvent(View view, MotionEvent event) {

    	switch (event.getActionMasked()) {

        case MotionEvent.ACTION_DOWN:
            break;

        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
            break;

        case MotionEvent.ACTION_MOVE:
            break;
        }
        return false;
    }
}