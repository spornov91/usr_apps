package ru.spornov91.usr_apps;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ru.spornov91.usr_apps.*;

public class ActivitySplashScreen extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		// Hide ActionBar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		//getActionBar().hide();
		
        setContentView(R.layout.activity_splash_screen);
		
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				Intent i = new Intent(ActivitySplashScreen.this, ActivityRouteApp.class);
				startActivity(i);
				finish();
			}
		},5000);
		
	};
};
