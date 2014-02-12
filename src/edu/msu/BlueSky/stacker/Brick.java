package edu.msu.BlueSky.stacker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Brick {
	/**
	 * x location. 
	 * We use relative x locations in the range 0-1 for the center
	 * of the brick.
	 */
	private float x = .5f;

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
			//assign color
			brick = BitmapFactory.decodeResource(context.getResources(), R.drawable.brick_blue);
		}
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void draw(Canvas canvas){
		
		canvas.save();
		canvas.drawBitmap(brick, 0, 0, null);
		canvas.restore();
	}
}
