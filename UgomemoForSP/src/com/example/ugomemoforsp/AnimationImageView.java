package com.example.ugomemoforsp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AnimationImageView extends ImageView {

	private ArrayList<Bitmap> mFrames = new ArrayList<Bitmap>();
	private int mFrameCount = 0;
	
	public AnimationImageView(Context context) {
		super(context, null);
	}
	
	public AnimationImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setFrame(ArrayList<Bitmap> frames) {
		mFrames = frames;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (this.mFrameCount >= mFrames.size()) {
			this.mFrameCount = 0;
		}
		if(this.mFrames.size()>0) {
			this.setImageBitmap(mFrames.get(mFrameCount++));	
		}
		 try{
			 Thread.sleep(35);
		 }catch(Exception e){
			 
		 }
	}
	
}
