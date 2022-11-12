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
	Fragment fragment;
	Fragment frag1;
	int num_frag = 0;
	@Override
	public void onClick(View p1)
	{
		switch (p1.getId()) {
            case R.id.bfraglistsystemapps:
				ft = getFragmentManager().beginTransaction();
				fragment = new FragListSystemApps();
				num_frag = 0;
                break;
            case R.id.bfraglistuserapps:
				ft = getFragmentManager().beginTransaction();
				fragment = new FragListUserApps();
				num_frag = 1;
                break;
			case R.id.bfraglistuserdataapps:
				ft = getFragmentManager().beginTransaction();
				fragment = new FragListUserDataApps();
				num_frag = 2;
                break;
			case R.id.bfragsettings:
				Intent intent = new Intent(getApplicationContext(), ActivitySettings.class);
				startActivity(intent);
				break;
			default : break;
		}
		ft.replace(R.id.sframe, fragment);
		ft.commit();
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
		fragment = new FragListSystemApps();
		ft.add(R.id.sframe, fragment);
		ft.commit();
	
	};
	
    public void onFragmentSendData(String txt) {
			switch(num_frag){
			case 0: 
				FragListSystemApps   frag0 = (FragListSystemApps)   getFragmentManager().findFragmentById(R.id.sframe);
				if (frag0 != null){  frag0.search_in_actionbar(txt);break; }
			case 1: 
				FragListUserApps     frag1 = (FragListUserApps)     getFragmentManager().findFragmentById(R.id.sframe);
				if (frag1 != null){  frag1.search_in_actionbar(txt);break; }
			case 2: 
				FragListUserDataApps frag2 = (FragListUserDataApps) getFragmentManager().findFragmentById(R.id.sframe);
				if (frag2 != null){  frag2.search_in_actionbar(txt);break; }
		}
        
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
					onFragmentSendData(newText);
					//Toast.makeText(getApplicationContext(), "searchbartext...", Toast.LENGTH_LONG).show();
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
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case R.id.action_search:
				// click on icon search in actionbar
				//Toast.makeText(this, "Searching...", Toast.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}
	
};
