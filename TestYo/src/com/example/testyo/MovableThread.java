package com.example.testyo;

public class MovableThread extends Thread {
	
	Movable body;
	TestView tv;
	//TestView tv;
	public MovableThread(Movable m,TestView tv){
		
		//this.tv =tv;
		this.body=m;
		this.tv=tv;
	}
	
	
	public void friction(){
		
	
		
		body.speed -=0.005;
		if(body.speed<0)body.speed=0;
		
		body.speedX = (body.speed*Math.cos(body.angle));
		
		body.speedY = (-1.0)*(body.speed*Math.sin(body.angle));
		//angleCal();
		
	}
	
	public void angleCal(){
		if(body.speedX>0&&body.speedY>0){  //往右下跑
			
			body.angle =  Math.PI*2- Math.atan(body.speedY/body.speedX);
		}
		else if(body.speedX>0&&body.speedY<0){ //往右上跑
			body.angle = Math.atan((-1)*body.speedY/body.speedX);		
		}
		else if(body.speedX<0&&body.speedY>0){ //往左下跑
			body.angle = (Math.PI+ Math.atan((-1)*body.speedY/body.speedX));
		}
		else if(body.speedX<0&&body.speedY<0){  //往左上跑
			body.angle = (Math.PI - Math.atan(body.speedY/body.speedX));
		}
		else if(body.speedX==0&&body.speedY>0){
			body.angle =  (Math.PI)*(3/2);
		}
		else if(body.speedX==0&&body.speedY<0){
			body.angle = Math.PI/2;
		}
		else if(body.speedX>0&&body.speedY==0){
			body.angle = 0;
		}
		else if(body.speedX<0&&body.speedY==0){
			body.angle = Math.PI;
		}
		
	
		/*body.speedX = (float)(body.speed*Math.cos(body.angle));
		
		body.speedY = (-1)*(float)(body.speed*Math.sin(body.angle));*/
	}
	
	public void acel(double dx,double dy){
		
		//body.active=false;
		body.speedX = dx;
		body.speedY = dy;
		body.speed = Math.sqrt( body.speedX*body.speedX +body.speedY*body.speedY);
		angleCal();
		//body.active=true;
	}
	
	
	public void run(){
		//long start,end;
		//start =  System.currentTimeMillis();
		
		while(body.active){
		
				// 碰撞判定各種狀況會有誤差  需要再調整   鳩咪
			//*Math.sin(body.angle);
			
			if(body.speed>0)
			friction();
				
			body.x += body.speedX;//*Math.cos(body.angle);
			body.y += body.speedY;
			
			if(body.x+body.bitmap.getWidth()*Math.cos(body.angle)>Constant.WIDTH){ //撞右邊
				tv.soundPool.play(tv.soundPoolMap.get(1), tv.volume, tv.volume, 1, 0, 1f);
				body.speedX*=-1.0;
				angleCal();
				
				
			}
			else if(body.x/*+body.bitmap.getWidth()*Math.cos(body.angle)*/<0){
				body.speedX*=-1.0;
				tv.soundPool.play(tv.soundPoolMap.get(1), tv.volume, tv.volume, 1, 0, 1f);
				angleCal();
				
				
			}
			else if(body.y/*-body.bitmap.getWidth()*Math.sin(body.angle)*/<0){
				body.speedY*=-1.0;
				tv.soundPool.play(tv.soundPoolMap.get(1), tv.volume, tv.volume, 1, 0, 1f);
				angleCal();
				
			}
			else if(body.y-body.bitmap.getWidth()*Math.sin(body.angle)>Constant.HEIGHT){
				body.speedY*=-1.0;
				tv.soundPool.play(tv.soundPoolMap.get(1), tv.volume, tv.volume, 1, 0, 1f);
				angleCal();
				
			}
			
			else if (body.x<0&&body.y<0){
				tv.soundPool.play(tv.soundPoolMap.get(1), tv.volume, tv.volume, 1, 0, 1f);
				body.speedX*=-1.0;
				body.speedY*=-1.0;
				angleCal();
				
			}
			else if(body.x>Constant.WIDTH&&body.y>Constant.HEIGHT){
				tv.soundPool.play(tv.soundPoolMap.get(1), tv.volume, tv.volume, 1, 0, 1f);
				body.speedX*=-1.0;
				body.speedY*=-1.0;
				angleCal();
				
			}
			
						
			
		try{
			Thread.sleep(10);
		}
		catch(Exception e){ e.printStackTrace();}
		finally{
			
		}
	}
	}
}
