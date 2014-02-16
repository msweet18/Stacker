package edu.msu.BlueSky.stacker;

import java.util.ArrayList;

import android.R.string;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Game {
	
	/**
	 * The name of the bundle keys to save the puzzle
	 */
	private final static String XLOCATIONS = "Game.xLocations";
	
	/**
	 * Collection of Bricks
	 */
	public ArrayList<Brick> bricks = new ArrayList<Brick>();
	
    /**
     * How much we scale the bricks
     */
    private float scaleFactor;
	
	//strings of the names...might make a player class if needed
	public string Player1;
	public string Player2;
	
	private GameView gameView;
	
	private int screenWidth;
	private int screenHeight;
	
	/**
     * This variable is set to a brick we are dragging. If
     * we are not dragging, the variable is null.
     */
    private Brick dragging = null;
    
    /**
     * Most recent relative X touch when dragging
     */
    private float lastRelX;
    
    /**
     * Most recent relative Y touch when dragging
     */
    private float lastRelY;

	public Game(Context context, GameView view){
		gameView = view;
		bricks.add(new Brick(context, true));
	}
	
	public void draw(Canvas canvas){
		screenWidth = canvas.getWidth();
		screenHeight = canvas.getHeight();
		
		int wid = Resources.getSystem().getDisplayMetrics().widthPixels;
		int hit = Resources.getSystem().getDisplayMetrics().heightPixels;
		
		// Determine the minimum of the two dimensions
		int minDim = wid < hit ? wid : hit;
		
		scaleFactor = (float)screenWidth/minDim;
		
		Log.i("scale", scaleFactor+", "+wid+", "+hit+", "+screenWidth);
		
		for(Brick brick : bricks){
			brick.draw(canvas, bricks.indexOf(brick), scaleFactor);
		}
	}
	
	/**
     * Handle a touch event from the view.
     * @param view The view that is the source of the touch
     * @param event The motion event describing the touch
     * @return true if the touch is handled.
     */
    public boolean onTouchEvent(View view, MotionEvent event) {

    	float relX = (event.getX()/screenWidth);
    	float relY = (event.getY()/screenHeight);
    	
    	switch (event.getActionMasked()) {

        case MotionEvent.ACTION_DOWN:
        	return onTouched(relX, relY);
        case MotionEvent.ACTION_UP:
        	return onReleased();
        case MotionEvent.ACTION_CANCEL:
        	Log.i("action cancel", "dragging set to null");
        	if(dragging != null) {
        		dragging = null;
                return true;
            }
            break;
        case MotionEvent.ACTION_MOVE:
        	if(dragging != null)
        	{
        		dragging.move(relX-lastRelX);
        		Log.i("dragging", "top brick is being dragged");
        		lastRelX = relX;
        		view.invalidate();
        		return true;
        	}
            break;
        }
        return false;
    }
    
    private boolean onReleased() {
		// TODO Auto-generated method stub
    	if(dragging != null){
    		Log.i("on released", "dragging set to null");
    		dragging = null;
    		return true;
    	}
		return false;
	}

	/**
	 * Save the puzzle to a bundle
	 * @param bundle The bundle we save to
	 */
	public void saveInstanceState(Bundle bundle) {
		float [] xLocations = new float[bricks.size()];
		
		
		for(int i=0;  i<bricks.size(); i++) {
			Brick brick = bricks.get(i);
			xLocations[i] = brick.getX();
		}
		
		bundle.putFloatArray(XLOCATIONS, xLocations);
	}
	
	/**
	 * Read the puzzle from a bundle
	 * @param bundle The bundle we save to
	 */
	public void loadInstanceState(Bundle bundle) {
		float [] xLocations = bundle.getFloatArray(XLOCATIONS);
		for(int i=0; i<xLocations.length; i++){
			bricks.get(i).setX(xLocations[i]);
		}
	}
	
	private boolean onTouched(float x, float y){
		Log.i("touched", x+" "+y);
		Brick topBrick = bricks.get(bricks.size()-1); 
		if(topBrick.hit(x, y, screenWidth, screenHeight, scaleFactor)) {
            // We hit a piece!
        	dragging = topBrick;
        	Log.i("dragging", "dragging set to top brick");
            lastRelX = x;
            lastRelY = y;
            return true;
        }
		return false;
	}
	
}