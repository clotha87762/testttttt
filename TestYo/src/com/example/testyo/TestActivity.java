package com.example.testyo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.*;
import java.util.HashMap;



import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class TestActivity extends Activity {

	AudioManager audio;
	Bitmap temp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("QQ");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,   
	        WindowManager.LayoutParams. FLAG_FULLSCREEN); 
	        //設置為橫式螢幕
	    	setVolumeControlStream(AudioManager.STREAM_MUSIC);//聲音控制
	       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        audio = (AudioManager) getSystemService(Service.AUDIO_SERVICE); 
			//取得屏幕尺寸
	        temp = BitmapFactory.decodeResource(getResources(), R.drawable.backimg);
	        Constant.WIDTH = temp.getWidth();
	        Constant.HEIGHT = temp.getHeight();
	        DisplayMetrics dm=new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);  
	        
	        if(dm.widthPixels>dm.heightPixels)
	        {
	        	 Constant.SCREEN_WIDTH=dm.widthPixels;
	        	 Constant.SCREEN_HEIGHT=dm.heightPixels;
	        }
	        else
	        {
	        	Constant.SCREEN_WIDTH=dm.widthPixels;
	        	Constant.SCREEN_HEIGHT=dm.heightPixels;
	        }
		TestView tv = new TestView(this,audio);
		ScreenScale.calScale(Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
		setContentView(tv);
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		
		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
