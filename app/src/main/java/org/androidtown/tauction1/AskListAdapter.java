package org.androidtown.tauction1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SSU on 2016-08-20.
 */
public class AskListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AskListData> arrData;
    private LayoutInflater inflater;

    public AskListAdapter(Context c, ArrayList<AskListData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getAsk_no();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.posting_list, parent, false);
        }

        ImageView image_reg = (ImageView)convertView.findViewById(R.id.image_region);
        //image_reg.setImageDrawable(R.drawable.busan1);
        image_reg.setImageResource(arrData.get(position).getImage_region());

        TextView done = (TextView)convertView.findViewById(R.id.done);
        if(1 == arrData.get(position).getDone()){
            done.setText("경매완료");
        } else {
            done.setText("경매 진행중");
        }
        TextView day = (TextView)convertView.findViewById(R.id.day);
        day.setText(String.format("%s~%s", new Object[]{arrData.get(position).getAsk_startday(), arrData.get(position).getAsk_endday()}));

        TextView price = (TextView)convertView.findViewById(R.id.price);
        price.setText(arrData.get(position).getAsk_budget()+" 원");

        TextView people_num = (TextView)convertView.findViewById(R.id.people_num);
        people_num.setText(arrData.get(position).getAsk_num()+" 명");

        TextView ask_commentNo = (TextView)convertView.findViewById(R.id.ask_commentNo);
        ask_commentNo.setText("+"+arrData.get(position).getAsk_commentNo());

        return convertView;
    }


}
