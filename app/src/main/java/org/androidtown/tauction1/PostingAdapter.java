package org.androidtown.tauction1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PostingAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<PostingData1> arrData;
    private LayoutInflater inflater;

    public PostingAdapter(Context c, ArrayList<PostingData1> arr) {
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
        posting_username.setText(arrData.get(position).getPosting_username());

        TextView posting_reg_day = (TextView)convertView.findViewById(R.id.posting_reg_day);
        posting_reg_day.setText(arrData.get(position).getPosting_reg_day());

        LinearLayout posting_linearLayout = (LinearLayout)convertView.findViewById(R.id.posting_linearLayout);
        posting_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PostingContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }


}
