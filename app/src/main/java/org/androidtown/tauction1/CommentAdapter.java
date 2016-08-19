package org.androidtown.tauction1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eun on 2016-08-16.
 */
public class CommentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CommentData> arrData;
    private LayoutInflater inflater;

    public CommentAdapter(Context c, ArrayList<CommentData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getComment_accomName();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.comment_list, parent, false);
        }

        TextView comment_accomName = (TextView)convertView.findViewById(R.id.comment_accomName);
        comment_accomName.setText(arrData.get(position).getComment_accomName());

        TextView comment_regDay = (TextView)convertView.findViewById(R.id.comment_regDay);
        comment_regDay.setText(arrData.get(position).getComment_regDay());

        TextView comment_price = (TextView)convertView.findViewById(R.id.comment_price);
        comment_price.setText(arrData.get(position).getComment_price());

        TextView comment_content = (TextView)convertView.findViewById(R.id.comment_content);
        comment_content.setText(arrData.get(position).getComment_content());

        Button comment_btn_accomInfo = (Button)convertView.findViewById(R.id.comment_btn_accomInfo);
        comment_btn_accomInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccommodationInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
