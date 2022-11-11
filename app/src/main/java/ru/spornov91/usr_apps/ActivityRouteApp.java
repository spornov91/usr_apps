package ru.spornov91.usr_apps;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ru.spornov91.usr_apps.*;
import android.support.v4.view.*;
import android.util.*;

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
		
		ImageButton bfrag1 = findViewById(R.id.bfraglistsystemapps);
		bfrag1.setOnClickListener(this);
		ImageButton bfrag2 = findViewById(R.id.bfraglistuserapps);
		bfrag2.setOnClickListener(this);
		ImageButton bfrag3 = findViewById(R.id.bfraglistuserdataapps);
		bfrag3.setOnClickListener(this);
		ImageButton bfrag4 = findViewById(R.id.bfragsettings);
		bfrag4.setOnClickListener(this);
		
		ft = getFragmentManager().beginTransaction();
		ft.add(R.id.sframe, new FragListSystemApps());
		ft.commit();
	
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate main_menu.xml 
		
		getMenuInflater().inflate(R.menu.menu, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) searchItem.getActionView();
		searchView.setQueryHint("Поиск");
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
		
				@Override
				public boolean onQueryTextChange(String newText) {
					Toast.makeText(getApplicationContext(), "searchbartext...", Toast.LENGTH_LONG).show();
					return false;
				}
				
				@Override
				public boolean onQueryTextSubmit(String query) {
					return false;
				}
				
		});
		// Get the SearchView and set the searchable configuration
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


		ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		searchView.setLayoutParams(params);
		searchView.setIconified(false);
		searchView.setMaxWidth( Integer.MAX_VALUE );
		searchItem.expandActionView();
		
		//ActionBar actionBar = getActionBar(); // As you said you are using support library
//		LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View v = inflator.inflate(R.layout.actionbar_custom_theme, null);
//		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//		actionBar.setCustomView(v);
		//SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
		//searchView.setMaxWidth(Integer.MAX_VALUE);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.action_search:
				Toast.makeText(this, "Searching...", Toast.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}
	
};
