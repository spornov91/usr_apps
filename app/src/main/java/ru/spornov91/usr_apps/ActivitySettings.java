package ru.spornov91.usr_apps;

import android.app.*;
import android.os.*;
import android.util.*;

import java.io.*;
import android.content.pm.*;
import android.*;
import android.view.*;

public class ActivitySettings extends Activity
{
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_settings);

			getActionBar().setDisplayHomeAsUpEnabled(true);
			getActionBar().setDisplayShowHomeEnabled(true);
			
		};
		
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
};
