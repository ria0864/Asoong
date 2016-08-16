package org.androidtown.tauction1;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<MyData> arrData;
    private LayoutInflater inflater;

    public MyAdapter(Context c, ArrayList<MyData> arr) {
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
            convertView = inflater.inflate(R.layout.list_layout, parent, false);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.image);
        image.setImageResource(arrData.get(position).getImage());
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccommodationInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        TextView rank = (TextView)convertView.findViewById(R.id.rank);
        rank.setText(arrData.get(position).getRank());

        TextView rating_star = (TextView)convertView.findViewById(R.id.rating_star);
        rating_star.setText(arrData.get(position).getRating_star());

        TextView rating_txt = (TextView)convertView.findViewById(R.id.rating_txt);
        rating_txt.setText(arrData.get(position).getRating_txt());

        TextView name = (TextView)convertView.findViewById(R.id.name);
        name.setText(arrData.get(position).getName());

        TextView address = (TextView)convertView.findViewById(R.id.address);
        address.setText(arrData.get(position).getAddress());


        return convertView;
    }


}
