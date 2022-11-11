package ru.spornov91.usr_apps;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import java.util.*;
import android.text.*;

public class FragListUserApps extends Fragment
{
	private String TAG = "spornov91";
	private ArrayAdapter<String> adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.frag_list_user_apps,null);
		// Flags: See below
		int flags = 
		    PackageManager.GET_META_DATA | 
            PackageManager.GET_SHARED_LIBRARY_FILES |     
            PackageManager.GET_UNINSTALLED_PACKAGES;

		final PackageManager pm = getActivity().getPackageManager();
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
		final String[] notNullPkgList = DelEmptyRowArray(pkgList);
		String[] clearPkgList = editPkgName(notNullPkgList);
		// находим список
		ListView listApps = v.findViewById(R.id.listApps);

		// создаем адаптер
		adapter = new ArrayAdapter<String>(
		getActivity().getApplicationContext(),
			android.R.layout.simple_list_item_1, notNullPkgList
		);

		// присваиваем адаптер списку
		listApps.setAdapter(adapter);
		listApps.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

					String item1 = (String)((TextView) view).getText();
				    String item2 = notNullPkgList[position];
					showPopup(item1,view);
				}
		});
		
		searchEditView(v);
		
		return v;

};

	public void searchEditView(View v){
		EditText filter = v.findViewById(R.id.search_filter);
		filter.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					adapter.getFilter().filter(s);
				}

				@Override
				public void afterTextChanged(Editable s) {
				};

			});
	};

private static String[] DelEmptyRowArray(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr) {
			if (str != null)
				list.add(str);
		}
		return list.toArray(new String[0]);
};

	private static String[] editPkgName(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr) {
			String[] parts = str.split("/");
			str = parts[3];
			parts = str.split("-");
			str = parts[0];
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
