package ru.spornov91.usr_apps;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import ru.spornov91.usr_apps.Utils;

public class FragDialogPopup extends DialogFragment
 {
	    public Utils utils = new Utils();
		public String txt = "";
		public int dirtype;
	    FragDialogPopup(String text){
			txt = text;
		}
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
            
			final String[] arrOptions = {"Скопировать", "Отправить", "Найти", "Скачать"};

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder
			      .setTitle("Что надо?")
				  .setItems(arrOptions, 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					Context ctx = getActivity();
						switch(id){
							case 0:
//								   Toast.makeText(ctx,"Выбрал: " + txt,
//							       Toast.LENGTH_SHORT).show();
								   utils.copyToClipboard(ctx,txt);
								   break;
							case 1:
//								   Toast.makeText(ctx,"Выбрал: " + txt,
//								   Toast.LENGTH_SHORT).show();
								   utils.sendAction(ctx, txt);
								   break;
						    case 2:
//								   Toast.makeText(ctx,"Выбрал: " + txt,
//								   Toast.LENGTH_SHORT).show();
								   utils.openFolder(ctx, txt);
								   break;
						    case 3:
//								   Toast.makeText(ctx,"Выбрал: " + txt,
//								   Toast.LENGTH_SHORT).show();
								   utils.openPkgInApkPure(ctx, txt);
								   break;
						}
					}
				});

			return builder.create();
		}
};
