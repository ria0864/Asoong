package org.androidtown.tauction1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eun on 2016-08-16.
 */
public class AskAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AskData> arrData;
    private LayoutInflater inflater;

    public AskAdapter(Context c, ArrayList<AskData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getName();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.ask_list, parent, false);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.image);
        image.setImageResource(arrData.get(position).getImage());

        TextView rank = (TextView)convertView.findViewById(R.id.rank);
        rank.setText(arrData.get(position).getRank());

        TextView rating_star = (TextView)convertView.findViewById(R.id.rating_star);
        rating_star.setText(arrData.get(position).getRating_star());


        TextView name = (TextView)convertView.findViewById(R.id.name);
        name.setText(arrData.get(position).getName());

        TextView address = (TextView)convertView.findViewById(R.id.address);
        address.setText(arrData.get(position).getAddress());


        return convertView;
    }
}
