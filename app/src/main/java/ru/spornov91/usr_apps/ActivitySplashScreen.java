package ru.spornov91.usr_apps;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ru.spornov91.usr_apps.*;
import android.util.*;

public class ActivitySplashScreen extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_splash_screen);
		
//		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {         
//			getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//			getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
//		}
		
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
