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

public class MyAdapterReview extends BaseAdapter{

    private Context context;
    private ArrayList<MyDataReview> arrData;
    private LayoutInflater inflater;

    public MyAdapterReview(Context c, ArrayList<MyDataReview> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getReview_user_name();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_layout_review, parent, false);
        }

        TextView review_user_name = (TextView)convertView.findViewById(R.id.review_user_name);
        review_user_name.setText(arrData.get(position).getReview_user_name());

        TextView review_reg_day = (TextView)convertView.findViewById(R.id.review_reg_day);
        review_reg_day.setText(arrData.get(position).getReview_reg_day());

        TextView review_txt = (TextView)convertView.findViewById(R.id.review_txt);
        review_txt.setText(arrData.get(position).getReview_txt());

        return convertView;
    }


}
