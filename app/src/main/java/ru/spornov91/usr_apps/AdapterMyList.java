package ru.spornov91.usr_apps;

import android.app.*;
import android.view.*;
import android.widget.*;
import ru.spornov91.usr_apps.*;
import android.graphics.drawable.*;

public class AdapterMyList extends ArrayAdapter<String>
 {  

    private final Activity context;  
    private final String[] maintitle;  
    private final String[] subtitle;  
    private final Drawable[] imgid;  

    public AdapterMyList(Activity context, String[] maintitle,String[] subtitle, Drawable[] imgid) {  
        super(context, R.layout.mylist, maintitle);  
        // TODO Auto-generated constructor stub  

        this.context=context;  
        this.maintitle=maintitle;  
        this.subtitle=subtitle;  
        this.imgid=imgid;  

    }  

    public View getView(int position,View view,ViewGroup parent) {  
        LayoutInflater inflater=context.getLayoutInflater();  
        View rowView=inflater.inflate(R.layout.mylist, null,true);  

        TextView titleText = (TextView) rowView.findViewById(R.id.title);  
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);  
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);  

        titleText.setText(maintitle[position]);  
        imageView.setImageDrawable(imgid[position]);  
        subtitleText.setText(subtitle[position]);  

        return rowView;  

    };  
}  
