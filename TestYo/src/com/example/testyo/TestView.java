package com.example.testyo;

import java.util.ArrayList;
import java.util.HashMap;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable.ConstantState;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.util.Log;
import android.media.SoundPool;
import android.media.AudioManager;

import java.util.HashMap;
import java.util.ArrayList;

import android.media.MediaPlayer;

public class TestView extends SurfaceView implements Callback/*,OnTouchListener,OnGestureListener*/ {

	
	boolean drawThreadAlive =true; 
	TestActivity ta;
	Bitmap back;
	DrawThread dt;
	ArrayList<Movable> movables = new ArrayList<Movable>();
	Rect rec;
	GestureDetector detector;
	MotionEvent e1,e2;
	float velocityX,velocityY;
	boolean intersect=false;
	AudioManager mgr;
	SoundPool soundPool;
	HashMap<Integer,Integer> soundPoolMap;
	MediaPlayer mediaPlayer;
	float volume;
	
	public TestView(TestActivity ta,AudioManager am){
		super(ta);
		this.ta = ta;
		mgr = am;
		this.getHolder().addCallback(this);
		this.setLongClickable(true);
		back = BitmapFactory.decodeResource(getResources(), R.drawable.backimg);
		Bitmap first = BitmapFactory.decodeResource(getResources(), R.drawable.jianto);
		this.dt=new DrawThread(this);
		initialSound();
		movables.add(new Movable(Constant.WIDTH/2,Constant.HEIGHT/2,3,Math.PI/4,first,this));
		//movables.add(new Movable(Constant.WIDTH/2-20,Constant.HEIGHT/2-20,2.5,Math.PI/6,first));
		//movables.add(new Movable(Constant.WIDTH/2+20,Constant.HEIGHT/2+20,5.48,Math.PI*1.3,first));
		//detector = new GestureDetector(this);
		//this.setOnTouchListener(this);
		drawThreadAlive = true;
		//System.out.println("TestViewBuilt");
		dt.start();
		rec = new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
	}
	
	public void initialSound(){
		
		float current = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		volume = current/maxVolume;
		soundPool = new SoundPool (4,AudioManager.STREAM_MUSIC,100);
		soundPoolMap = new HashMap<Integer,Integer>();
		soundPoolMap.put(1,soundPool.load(ta,R.raw.wall_sound,5));
	
	}
	
	
	
	public void toDraw(Canvas canvas){
		
		
		Paint black = new Paint();
		
		black.setARGB(255,0,0,0);
		canvas.drawRect(rec, black);
		canvas.save();
		canvas.translate(Constant.LCUX, Constant.LCUY);
		
		canvas.scale(Constant.RATIO, Constant.RATIO);
		
		canvas.drawBitmap(back, 0, 0, new Paint());
		for(Movable m :movables){
			m.drawSelf(canvas); //有可能drawself需要調整
		}
		
		canvas.restore();
	}
	
	public void repaint(){
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//取得畫布
		long start = System.currentTimeMillis();
	
		try{
			synchronized(holder){
				toDraw(canvas);//繪製
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
		
		
		long end = System.currentTimeMillis();
		if(end-start<10){
			try{
				Thread.sleep(10);
			}
			catch(Exception e){e.printStackTrace();}
			finally{
			}
		}
		
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	/*@Override
	public boolean onTouch(View view, MotionEvent event) {
		Log.d("DEBUG","onTouch");
		return detector.onTouchEvent(event);
	}*/
	
	 float e1X,e1Y;
	 long e1Time;
	 //boolean catched=false;
	 @Override  
	    public boolean onTouchEvent(MotionEvent event) {  
	        // TODO Auto-generated method stub  
		 	//Log.d("DEBUG","onToucgEvent");
		 	float xx=(event.getX()/Constant.RATIO)-Constant.LCUX;
		 	float yy=(event.getY()/Constant.RATIO)-Constant.LCUY;
		 	
		 	switch(event.getAction()){
		 	
		 	
		 	case MotionEvent.ACTION_DOWN:
		 		for(Movable m:movables){
		 			if(m.contain2(xx,yy)){
		 				m.speedX=0;
		 				m.speedY=0;
		 				m.speed=0;
		 				m.catched =true;
		 			}
		 		}
		 		Log.d("DEBUG","onDown");
		 		e1X = event.getX();
		 		e1Y = event.getY();
		 		e1Time = event.getEventTime();
		 		break;
		 		
		 	case MotionEvent.ACTION_MOVE:
		 			
		 		for(Movable m:movables){
		 			if(m.catched){
		 				m.x=xx;
		 				m.y=yy;
		 			}
		 		}
		 		
		 		Movable M =movables.get(0);
		 			Log.d("DEBUG","Move "+Float.toString(xx)+" "
 						+Float.toString(yy)+" "+M.x+" "+M.y);
		 		
		 		
		 			if(M.contain(xx, yy)){
		 				Log.d("DEBUG","Contain "+Float.toString(xx)+" "
		 						+Float.toString(yy)+" "+M.x+" "+M.y);
		 				M.forced =true;
		 			}
		 		
		 		break;
		 	case MotionEvent.ACTION_UP:
		 		Log.d("DEBUG","onUp");
		 		
		 		
		 		long time = (event.getEventTime() - e1Time);
		    	velocityX =  (event.getX()-e1X)/time;
		    	velocityY =  (event.getY()-e1Y)/time;
		    	velocityX*=1000;
		    	velocityY*=1000;
		    	
		    	Log.d("DEBUG",Float.toString(e1X)+" "+Float.toString(e1Y)+" "+Float.toString(event.getX())+" "+
		    			Float.toString(event.getY())+" "+Float.toString(velocityX)+" "+Float.toString(velocityY));
		    	
		    		if(velocityX>300)velocityX=300;
		    		if(velocityY>300)velocityY=300;
		   		    		
		    	for(Movable m:movables){
		    		if(m.catched){
		    		m.catched=false;
		    		m.forced=false;
		    		}
			    	if(m.forced==true){
		    			Log.d("DEBUG","accelerate");
		    			m.mt.acel(velocityX/200,velocityY/200);	 		
		    			soundPool.play(soundPoolMap.get(1), volume, volume, 1, 0, 1f);
			    	}	 	
			    	m.forced=false;
		 		}
		 		
		 		break;
		 		
		 		default:
		 			break;
		 	}
	        return super.onTouchEvent(event);  
	    } 
	 
	 /*@Override  
	    public boolean onDown(MotionEvent e) {  
	        // TODO Auto-generated method stub  
		 Log.d("DEBUG","onDown");   
		 return false;  
	    }  
	  
	    @Override  
	    public void onShowPress(MotionEvent e) {  
	        // TODO Auto-generated method stub  
	          
	    }  
	  
	    @Override  
	    public boolean onSingleTapUp(MotionEvent e) {  
	        // TODO Auto-generated method stub  
	        return false;  
	    }  
	  
	    @Override  
	    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,  
	            float distanceY) {  
	    	Log.d("DEBUG","onScroll");
	    	// TODO Auto-generated method stub  
	        return false;  
	    }  
	  
	    @Override  
	    public void onLongPress(MotionEvent e) {  
	        // TODO Auto-generated method stub  
	    	Log.d("DEBUG","onLongPress");
	    	for(Movable m:movables){
	    		m.mt.acel(0, 0);
	    	}
	    }  
	    
	    @Override
	    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
	            float velocityY) {  
	    	long time; 
	    	boolean flag =false;
	    	time = (e2.getEventTime() - e1.getEventTime());
	    	velocityX =  (e2.getX()-e1.getX())/time;
	    	velocityY =  (e2.getY()-e1.getY())/time;
	    	velocityX*=1000;
	    	velocityY*=1000;
	    	Log.d("DEBUG","Fling");
	    	Log.d("DEBUG",Float.toString(e1.getX())+" "+Float.toString(e1.getY())+" "+Float.toString(e2.getX())+" "+
	    			Float.toString(e2.getY())+" "+Float.toString(velocityX)+" "+Float.toString(velocityY));
	    	
	    		if(velocityX>500)velocityX=500;
	    		if(velocityY>500)velocityY=500;
	    	float tempX = (e1.getX()+e2.getX())/2;
	    	float tempY = (e1.getY()+e2.getY())/2;
	    		
	    	for(Movable m:movables){
	    		if(((m.x>e1.getX()&&m.x<e2.getX())||(m.x<e1.getX()&&m.x>e2.getX()))
	    				&&((m.y>e1.getY()&&m.y<e2.getY())||(m.y<e1.getY()&&m.y>e2.getY())))
	    		
	    		
	    			
	    			
	    				Log.d("DEBUG","accelerate");
	    				m.mt.acel(velocityX/100,velocityY/100);
	    			
	    		
	    	}
	    	
	    
	    	return true;
	    }*/
	    
	    public double distance(double x1,double y1,double x2,double y2){
	    	
	    	return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	    }
	    
}
