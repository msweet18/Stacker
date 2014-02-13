package edu.msu.BlueSky.stacker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Brick {
	/**
	 * x location. 
	 * We use relative x locations in the range 0-1 for the center
	 * of the brick.
	 */
	private float xPosition = .5f;

	/**
	 * weight of the brick
	 */
	private int weight;
	
	/**
	 * image for the brick
	 */
	private Bitmap brick;
	
	private boolean IsPlayer1;

	public Brick(Context context, boolean Player1){
		IsPlayer1 = Player1;
		if(IsPlayer1)
		{
			//assign color brick
			brick = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick_blue);
		}
		else{
			brick = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick_green1);
		}
	}
	
	public float getX() {
		return xPosition;
	}

	public void setX(float x) {
		this.xPosition = x;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void draw(Canvas canvas, int brickNumber){
		int y = canvas.getHeight()-(brick.getHeight()*(brickNumber+1)); //y position to draw
		int x = (int)(xPosition*canvas.getWidth())-brick.getWidth()/2;
		Log.i("draw", "y = "+y);
		canvas.save();
		canvas.drawBitmap(brick, x, y, null);
		canvas.restore();
	}
}
