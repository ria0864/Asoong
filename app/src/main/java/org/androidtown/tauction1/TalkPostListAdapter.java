package org.androidtown.tauction1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SSU on 2016-08-20.
 */
public class TalkPostListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TalkPostListData> arrData;
    private LayoutInflater inflater;

    public TalkPostListAdapter(Context c, ArrayList<TalkPostListData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getPosting_title();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.posting_list, parent, false);
        }

        TextView posting_title = (TextView)convertView.findViewById(R.id.posting_title);
        posting_title.setText(arrData.get(position).getPosting_title());

        TextView posting_username = (TextView)convertView.findViewById(R.id.posting_username);
        posting_username.setText(arrData.get(position).getMem_id());

        TextView posting_reg_day = (TextView)convertView.findViewById(R.id.posting_reg_day);
        posting_reg_day.setText(arrData.get(position).getPosting_date());

        return convertView;
    }


}
