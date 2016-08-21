package org.androidtown.tauction1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.MessageFormat;
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

    @Override//이거뭐야..?
    public Object getItem(int position) {
        return position;
    }

    @Override//이거뭐야..?
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

        TextView day = (TextView)convertView.findViewById(R.id.day);
        day.setText(MessageFormat.format("{0}~{1}", arrData.get(position).getAsk_startday().toString(), arrData.get(position).getAsk_endday().toString()));

        TextView price = (TextView)convertView.findViewById(R.id.price);
        price.setText(arrData.get(position).getAsk_budget());

        TextView people_num = (TextView)convertView.findViewById(R.id.people_num);
        people_num.setText(arrData.get(position).getAsk_num());

        TextView ask_commentNo = (TextView)convertView.findViewById(R.id.ask_commentNo);
        ask_commentNo.setText(arrData.get(position).getAsk_commentNo());

        return convertView;
    }
}
