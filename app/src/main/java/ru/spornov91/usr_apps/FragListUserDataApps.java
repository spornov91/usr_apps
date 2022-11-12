package ru.spornov91.usr_apps;

import android.*;
import android.app.*;
import android.content.pm.*;
import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import java.io.*;
import java.util.*;

public class FragListUserDataApps extends Fragment
{
	private String TAG = "spornov91";
	private ArrayAdapter<String> adapter;
	String[] notNullPkgList;
	String[] filtredPkgList;
	ListView listApps;
	View v;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		v = inflater.inflate(R.layout.frag_list_user_data_apps,null);
		
	    int REQUEST_CODE_PERMISSIONS = 1;
		int permissionStatus = getActivity().getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

		if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
			
			String path0 = Environment.getExternalStorageDirectory().toString();
			String path1 = "/Android/data";
			String path = path0 + path1;
			//Log.d("Files", "Path: " + path);
			File dir = new File(path);
			String[] pkgList = dir.list();
			filtredPkgList = DelEmptyRowArray(pkgList);
			notNullPkgList = filterSystemPkgName("",filtredPkgList);
			create_list_apps();
		} else {
			requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSIONS);
		}
		return v;
    };
	
	private static String[] filterSystemPkgName(String s,String[] arr)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr)
		{
			if(str.toLowerCase().contains(
				   s.toString().toLowerCase().trim()))
				list.add(str);
		}
		return list.toArray(new String[0]);
    };

	public void search_in_actionbar(String s)
	{
		//notNullPkgList = editSystemPkgName(notNullPkgList);
		notNullPkgList = filterSystemPkgName(s,filtredPkgList);
		create_list_apps();
    }

	public void create_list_apps(){
		//String[] clearPkgList = editSystemPkgName(notNullPkgList);
		// находим список
		listApps = v.findViewById(R.id.listApps);

		// создаем адаптер
		adapter = new ArrayAdapter<String>(
			getActivity().getApplicationContext(),
			android.R.layout.simple_list_item_1, notNullPkgList
		);

		// присваиваем адаптер списку
		listApps.setAdapter(adapter);
		listApps.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					String item1 = (String)((TextView) view).getText();
					String item2 = notNullPkgList[position];
					showPopup(item1, view);
				}
			});
	}
	
	private static String[] DelEmptyRowArray(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr) {
			if (str != null)
				list.add(str);
		}
		return list.toArray(new String[0]);
	};
	
	public void showPopup(String txt, View view) {
		FragDialogPopup myDialogFragment = new FragDialogPopup(txt);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		myDialogFragment.show(transaction, "dialog");
	}
	
};
