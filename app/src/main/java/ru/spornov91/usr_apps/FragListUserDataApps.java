package ru.spornov91.usr_apps;

import android.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import java.io.*;
import ru.spornov91.usr_apps.*;

public class FragListUserDataApps extends Fragment
{
	private String TAG = "spornov91";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.frag_list_user_data_apps,null);
		
	    int REQUEST_CODE_PERMISSIONS = 1;
		int permissionStatus = getActivity().getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

		if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

			Log.d(TAG, "granted: ");
			String path0 = Environment.getExternalStorageDirectory().toString();
			String path1 = "/Android/data";
			String path = path0 + path1;
			//Log.d("Files", "Path: " + path);
			File dir = new File(path);
			String[] clearDataList = dir.list();
			// находим список
			ListView listApps = v.findViewById(R.id.listApps);

			// создаем адаптер
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getActivity().getApplicationContext(),
				android.R.layout.simple_list_item_1, clearDataList
			);

			// присваиваем адаптер списку
			listApps.setAdapter(adapter);
			listApps.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						
						String item1 = (String)((TextView) view).getText();
						
						ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
						ClipData clip = ClipData.newPlainText("text", item1);
						if (clipboard != null) {
							clipboard.setPrimaryClip(clip);
						}
						
						Toast.makeText(getActivity(), "Скопированно", Toast.LENGTH_SHORT).show();
					}
			});
			
//			if(dir.canRead() && fs!=null) {
//				Log.d("Files", "Size: "+ fs.length);
//
//				for (int i = 0; i < fs.length; i++)
//				{
//					File isDir = new File(dir, fs[i].getName());
//					if(isDir.isDirectory()){
//						Log.d("Files", "D:" + fs[i].getName());
//					}
//				}
//			}
		} else {
			requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSIONS);
		}
		return v;
};
};
