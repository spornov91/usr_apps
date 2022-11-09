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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.frag_list_user_data_apps,null);
		
	    int REQUEST_CODE_PERMISSIONS = 1;
		int permissionStatus = getActivity().getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

		if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
			
			String path0 = Environment.getExternalStorageDirectory().toString();
			String path1 = "/Android/data";
			String path = path0 + path1;
			//Log.d("Files", "Path: " + path);
			File dir = new File(path);
			String[] clearDataList = dir.list();
			// находим список
			ListView listApps = v.findViewById(R.id.listApps);

			// создаем адаптер
			adapter = new ArrayAdapter<String>(
				getActivity().getApplicationContext(),
				android.R.layout.simple_list_item_1, clearDataList
			);

			// присваиваем адаптер списку
			listApps.setAdapter(adapter);
			listApps.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						
						String item1 = (String)((TextView) view).getText();
					    //int dirType = 2;
						showPopup(item1,view);
					}
			});
			searchEditView(v);
				
		} else {
			requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSIONS);
		}
		return v;
    };
	
	public void showPopup(String txt, View view) {
		FragDialogPopup myDialogFragment = new FragDialogPopup(txt);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		myDialogFragment.show(transaction, "dialog");
	}
	
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
};
