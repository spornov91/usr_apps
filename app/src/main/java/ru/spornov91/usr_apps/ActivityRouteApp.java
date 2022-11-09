package ru.spornov91.usr_apps;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ru.spornov91.usr_apps.*;

public class ActivityRouteApp extends Activity implements View.OnClickListener 
{
	
	@Override
	public void onClick(View p1)
	{
		switch (p1.getId()) {
            case R.id.bfraglistsystemapps:
				ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.sframe, new FragListSystemApps());
				ft.commit();
                break;
            case R.id.bfraglistuserapps:
				ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.sframe, new FragListUserApps());
				ft.commit();
                break;
			case R.id.bfraglistuserdataapps:
				ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.sframe, new FragListUserDataApps());
				ft.commit();
                break;
			case R.id.bfragsettings:
				Intent intent = new Intent(getApplicationContext(), ActivitySettings.class);
				startActivity(intent);
				break;
			default : break;
		}
	};
	
	FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_start_app);
		
		Button bfrag1 = findViewById(R.id.bfraglistsystemapps);
		bfrag1.setOnClickListener(this);
		Button bfrag2 = findViewById(R.id.bfraglistuserapps);
		bfrag2.setOnClickListener(this);
		Button bfrag3 = findViewById(R.id.bfraglistuserdataapps);
		bfrag3.setOnClickListener(this);
		Button bfrag4 = findViewById(R.id.bfragsettings);
		bfrag4.setOnClickListener(this);
	};
};
