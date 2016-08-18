package org.androidtown.tauction1;

import android.content.Context;
import android.content.Intent;
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
        return arrData.get(position).getRegion_detail();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.ask_list, parent, false);
        }

        ImageView image_region = (ImageView)convertView.findViewById(R.id.image_region);
        image_region.setImageResource(arrData.get(position).getImage_region());
        image_region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AskContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        TextView done = (TextView)convertView.findViewById(R.id.done);
        done.setText(arrData.get(position).getDone());

        TextView region_detail = (TextView)convertView.findViewById(R.id.region_detail);
        region_detail.setText(arrData.get(position).getRegion_detail());

        TextView day = (TextView)convertView.findViewById(R.id.day);
        day.setText(arrData.get(position).getDay());

        TextView price = (TextView)convertView.findViewById(R.id.price);
        price.setText(arrData.get(position).getPrice());

        TextView people_num = (TextView)convertView.findViewById(R.id.people_num);
        people_num.setText(arrData.get(position).getPeople_num());


        return convertView;
    }
}
