package ru.spornov91.usr_apps;



import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import java.util.*;

public class Utils
{
	private String TAG = "spornov91";
	public void copyToClipboard(Context ctx, String txt){
		ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("text", txt);
		if (clipboard != null) {
			clipboard.setPrimaryClip(clip);
		}
		Toast.makeText(ctx, "Скопированно", Toast.LENGTH_SHORT).show();
	};

	public static String[] addpreffixarr(String[] arr, String preffix) {
		ArrayList<String> list = new ArrayList<String>();
		for (String pkgname : arr) {
			//String preffix = "-_android/app";
			pkgname += pkgname + preffix;
			list.add(pkgname);
		}
		return list.toArray(new String[0]);
	};
	
	public static String[] removepreffixarr(String[] arr) {
		ArrayList<String> list = new ArrayList<String>();
		for (String pkgname : arr) {
			String[] notpreffix = pkgname.split("-_");
			pkgname = notpreffix[0];
			list.add(pkgname);
		}
		return list.toArray(new String[0]);
	};
	
	public String removepreffixstr(String pkgname){
		String[] notpreffix = pkgname.split("-_");
		pkgname = notpreffix[0];
		return pkgname;
	}
	
	public int getpreffixstr(String pkgname){
		String[] notpreffix = pkgname.split("-_");
		pkgname = notpreffix[1];
		return Integer.parseInt(pkgname);
	}
	
	public void openFolder(Context ctx , String dir4){
        int type = getpreffixstr(dir4);
		removepreffixstr(dir4);
		
		String dir0 = "";
		switch(type){
		        case 0 : 
			            dir0="/system/app/"+dir4;
				        break;
				case 1 :
			            dir0="/data/app/"+dir4;
				        break;
				case 2 :
			            dir0 = Environment.getExternalStorageDirectory().getPath();
				        dir0 = dir0 + "/android/data/"+dir4;
				        break;
		}
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(dir0), "*/*");
		ctx.startActivity(Intent.createChooser(intent, "Open folder"));
	};

	public void sendAction(Context ctx,String txt){
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, txt);
		sendIntent.setType("text/plain");
		ctx.startActivity(Intent.createChooser(sendIntent, txt));
	};
	
	public void isDirectory(){
//		if(dir.canRead() && fs!=null) {
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
	};
	
	public void openPkgInApkPure(Context ctx,String pkgName){
		Uri url = Uri.parse("https://m.apkpure.com/ru/" + pkgName);
		Log.d(TAG, url.toString());
		Intent browser = new Intent();
		browser.setAction(Intent.ACTION_VIEW);
		browser.setData(url);
		ctx.startActivity(browser);
	}
	
};
