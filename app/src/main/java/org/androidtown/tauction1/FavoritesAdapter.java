package org.androidtown.tauction1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<FavoritesData> arrData;
    private LayoutInflater inflater;

    public FavoritesAdapter(Context c, ArrayList<FavoritesData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getFavorites_name();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_favorites, parent, false);
        }

        ImageView favorites_image = (ImageView)convertView.findViewById(R.id.favorites_image);
        favorites_image.setImageResource(arrData.get(position).getFavorites_image());
        favorites_image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccommodationInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        TextView favorites_like_num = (TextView)convertView.findViewById(R.id.favorites_like_num);
        favorites_like_num.setText(arrData.get(position).getFavorites_like_num());

        TextView favorites_name = (TextView)convertView.findViewById(R.id.favorites_name);
        favorites_name.setText(arrData.get(position).getFavorites_name());

        TextView favorites_address = (TextView)convertView.findViewById(R.id.favorites_address);
        favorites_address.setText(arrData.get(position).getFavorites_address());


        return convertView;
    }


}
