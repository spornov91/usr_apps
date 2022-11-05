package ru.spornov91.usr_apps;

import android.app.*;
import android.content.pm.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity 
{
	private String TAG = "spornov91";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Flags: See below
		int flags = 
		    PackageManager.GET_META_DATA | 
            PackageManager.GET_SHARED_LIBRARY_FILES |     
            PackageManager.GET_UNINSTALLED_PACKAGES;
			
		final PackageManager pm = getPackageManager();
        //get a list of installed apps.
		List<ApplicationInfo> packages = pm.getInstalledApplications(flags);
		int psize = packages.size();
		String[] pkgList = new String[psize];
		for (int i = 0; i < psize; i++) {
			if ((packages.get(i).flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
				// User application
				pkgList[i] = packages.get(i).sourceDir;
				if(((packages.get(i).packageName).toString()) == "com.aide.ui"){
				    Log.d(TAG, "package : " + packages.get(i).packageName + " :" + packages.get(i).sourceDir);
				}
			} 
			
		}
		String[] clearPkgList = DelEmptyRowArray(pkgList);
		// находим список
		ListView listApps = findViewById(R.id.listApps);

		// создаем адаптер
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, clearPkgList);

		// присваиваем адаптер списку
		listApps.setAdapter(adapter);
		
    };
	
	private static String[] DelEmptyRowArray(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr) {
			if (str != null)
				list.add(str);
		}
		return list.toArray(new String[0]);
	};
	
};
