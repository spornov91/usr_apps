package ru.spornov91.usr_apps;



import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;

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

	public void openFolder(Context ctx , String dir4){
		//String dir1 = "/android/data/com.miui.screenrecorder";
		String dir0 = Environment.getExternalStorageDirectory().getPath();
		String dir2 = dir4 + File.separator;
		Log.d(TAG, "D: "+dir2);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(dir2), "*/*");
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
	}
}
