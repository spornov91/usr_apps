package ru.spornov91.usr_apps;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import java.io.*;
import java.util.*;
import android.text.*;
import android.graphics.drawable.*;


public class FragListSystemApps extends Fragment
{
//	interface OnFragmentSendDataListener {
//        void onSendData(String data);
//    }
//
//    private OnFragmentSendDataListener fragmentSendDataListener;
//	
//	@Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//		fragmentSendDataListener = (OnFragmentSendDataListener) context;
//    }

	private String TAG = "spornov91";
	private ArrayAdapter<String> adapter;
	String[] notNullPkgList;
	
	ListView listApps;
	View v;
	
	String[] arrNamesSystemPkgs;
    ArrayList<String> arrDirsSystemPkgs;
	ArrayList<Drawable> arrIconsSystemPkgs;
	String[] arrSubtitleSystemPkgList;
    Drawable[] arrIconsSystemPkgList;
	
	class InfoObject {
        public String appname = "";
        public String dirname = "";
        public Drawable iconpkg;

        public void InfoObjectAggregatePrint() {
            Log.v(appname, appname);
        }

    }
	ArrayList < InfoObject > obj = new ArrayList < InfoObject > ();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//Посылаем данные Activity
		//fragmentSendDataListener.onSendData("system");

		v = inflater.inflate(R.layout.frag_list_system_apps, null);
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
		for (int i = 0; i < psize; i++)
		{
			if (packages.get(i) != null){
			
			if ((packages.get(i).flags & ApplicationInfo.FLAG_SYSTEM) == 1)
			{
				// System application
				
				InfoObject newInfo = new InfoObject(); 
				newInfo.appname = packages.get(i).packageName;
				newInfo.dirname = packages.get(i).sourceDir; 
				newInfo.iconpkg = packages.get(i).loadIcon(pm);
				obj.add(newInfo);
				//Log.d("obj size: ", "" + newInfo.appname);
				if (((packages.get(i).packageName).toString()) == "com.aide.ui")
				{
				    Log.d(TAG, "package : " + packages.get(i).packageName + " :" + packages.get(i).sourceDir);
				}
			} 
			
			}

		}
		for(int i = 0;i < obj.size();i++){
			Log.d("PKG NAMES: ", "" + obj.get(i).dirname );
		}
		
		
		arrNamesSystemPkgs = objToArrNames(obj);//mockCreateArrNamesSystemPkgList(obj.size());
		arrSubtitleSystemPkgList = objToArrDirs(obj);//mockCreateArrDirsSystemPkgList(obj.size());
		notNullPkgList = filterSystemPkgName("",arrNamesSystemPkgs);
		arrIconsSystemPkgList = objToArrIcons(obj);//mockCreateArrIconsSystemPkgList(obj.size());
		create_list_apps();

		return v;

	};
	
	private static Drawable[] objToArrIcons(ArrayList < InfoObject > obj){
		ArrayList<Drawable> list = new ArrayList<Drawable>();
		for (int i = 0; i < obj.size();i++)
		{
		    list.add(obj.get(i).iconpkg);
		}
		return list.toArray(new Drawable[0]);
	};
	
	private static String[] objToArrDirs(ArrayList < InfoObject > obj){
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < obj.size();i++)
		{
		    list.add(obj.get(i).dirname);
		}
		return list.toArray(new String[0]);
	};
	
	private static String[] objToArrNames(ArrayList < InfoObject > obj){
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < obj.size();i++)
		{
		    list.add(obj.get(i).appname);
		}
		return list.toArray(new String[0]);
	};
	
	private static String[] mockCreateArrDirsSystemPkgList(int size)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < size;i++)
		{
		    list.add("dir/1");
		}
		return list.toArray(new String[0]);
    };
	
	private static String[] mockCreateArrNamesSystemPkgList(int size)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < size;i++)
		{
		    list.add("name1");
		}
		return list.toArray(new String[0]);
    };
	
	private static Integer[] mockCreateArrIconsSystemPkgList(int size)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < size;i++)
		{
		    list.add(R.drawable.ic_friends);
		}
		return list.toArray(new Integer[0]);
    };
	
	private static String[] filterSystemPkgName(String s,String[] arr)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr)
		{
			if(str.toLowerCase().contains(s.toString().toLowerCase().trim()))
				list.add(str);
		}
		return list.toArray(new String[0]);
    };
	
	public void search_in_actionbar(String s)
	{
		//notNullPkgList = editSystemPkgName(notNullPkgList);
		notNullPkgList = filterSystemPkgName(s,arrNamesSystemPkgs);
		create_list_apps();
    }

	public void create_list_apps(){
		//String[] clearPkgList = editSystemPkgName(notNullPkgList);
		// находим список
		listApps = v.findViewById(R.id.listApps);

		// создаем адаптер
		adapter=new AdapterMyList(getActivity(), notNullPkgList, arrSubtitleSystemPkgList, arrIconsSystemPkgList);  
//		adapter = new ArrayAdapter<String>(
//			getActivity().getApplicationContext(),
//			android.R.layout.simple_list_item_1, notNullPkgList
//		);

		// присваиваем адаптер списку
		listApps.setAdapter(adapter);
		listApps.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					//String item1 = (String)((TextView) view).getText();
					//String item2 = notNullPkgList[position];
					String item3 = parent.getAdapter().getItem(position).toString();
					Log.d("objj",item3);
					showPopup(item3, view);
				}
		});
	}
	
    private static String[] DelEmptyRowArray(String[] arr)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr)
		{
			if (str != null)
				list.add(str);
		}
		return list.toArray(new String[0]);
    };

	private static String[] editSystemPkgName(String[] arr)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (String str : arr)
		{
			String[] parts = str.split("/");
			str = parts[3];
			int index = str.indexOf("-");
            if (index != -1)
            {
                parts = str.split("-");
                str = parts[0];
            }
			index = str.indexOf(".apk");
            if (index != -1)
            {
                parts = str.split(".apk");
                str = parts[0];
            }
			list.add(str);
		}
		return list.toArray(new String[0]);
	};

    public void showPopup(String txt, View view)
	{
		FragDialogPopup myDialogFragment = new FragDialogPopup(txt);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		myDialogFragment.show(transaction, "dialog");
	}
};
