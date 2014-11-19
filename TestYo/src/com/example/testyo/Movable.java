package com.example.testyo;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Movable {
	boolean active;
	public double x;
	public double y;
	public double speedX;
	public double speedY;
	public double speed;
	MovableThread mt;
	double angle;
	public Bitmap bitmap;
	boolean  forced ;
	boolean catched;
	
	public Movable(double x,double y,double speed,double angle,Bitmap bm,TestView tv){
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.speed=speed;
		catched =false;
		forced =false;
		this.bitmap=bm;
		mt = new MovableThread(this,tv);
		active = true;
		mt.start();
	}
	
	public void drawSelf(Canvas canvas){
		
		canvas.save();
		canvas.rotate((-1)* (float)angle*180/(float)Math.PI,(float)(x+bitmap.getWidth()/2),
				(float)(y+bitmap.getHeight()/2));
		canvas.drawBitmap(bitmap, (float)x,(float)y, new Paint());
		canvas.restore();
	}
	
	public boolean contain2(float x,float y){
		
		if(x>this.x-(bitmap.getWidth()*Constant.RATIO*0.75)&&this.x+(bitmap.getWidth()*Constant.RATIO*0.75)>x&&y>this.y-(Constant.RATIO*bitmap.getHeight()*0.75)&&this.y+(Constant.RATIO*bitmap.getHeight()*0.75)>y)
			return true;
		
		return false;
	}
	
	public boolean contain(float x,float y){
		
		if(x>this.x-(bitmap.getWidth()*Constant.RATIO*1.5)&&this.x+(bitmap.getWidth()*Constant.RATIO*1.5)>x&&y>this.y-(Constant.RATIO*bitmap.getHeight()*1.5)&&this.y+(Constant.RATIO*bitmap.getHeight()*1.5)>y)
			return true;
		//else if(x<this.x&&x>this.x-(bitmap.getWidth()*Constant.RATIO*1.5)&&y<this.y&&y>this.y-(bitmap.getHeight()*Constant.RATIO*1.5))
		// return true ;	
		return false;
		
	}
	
}
