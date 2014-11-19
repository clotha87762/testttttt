package com.example.testyo;

public class DrawThread extends Thread {
	
	TestView tv ;
	public DrawThread (TestView tv){
		
		this.tv=tv;
	}
	@Override
	public void run(){
		while(tv.drawThreadAlive){
			
			
				
			tv.repaint();	
				
			try{
				Thread.sleep(10);
			}
			catch(Exception e){
				//e.printStackTrace();
			}
			finally{
				
			}
			
		}
	
	}
	
}
