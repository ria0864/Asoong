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

public class DibsAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<DibsData> arrData;
    private LayoutInflater inflater;

    public DibsAdapter(Context c, ArrayList<DibsData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getDibs_title();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.dibs_list, parent, false);
        }

        TextView dibs_userName = (TextView)convertView.findViewById(R.id.dibs_userName);
        dibs_userName.setText(arrData.get(position).getDibs_userName());

        TextView dibs_reg_day = (TextView)convertView.findViewById(R.id.dibs_reg_day);
        dibs_reg_day.setText(arrData.get(position).getDibs_reg_day());

        TextView dibs_title = (TextView)convertView.findViewById(R.id.dibs_title);
        dibs_title.setText(arrData.get(position).getDibs_title());

        TextView dibs_content = (TextView)convertView.findViewById(R.id.dibs_content);
        dibs_content.setText(arrData.get(position).getDibs_content());

        TextView dibs_commentNo = (TextView)convertView.findViewById(R.id.dibs_commentNo);
        dibs_commentNo.setText(arrData.get(position).getDibs_commentNo());

        LinearLayout dibs_linearLayout = (LinearLayout)convertView.findViewById(R.id.dibs_linearLayout);
        dibs_linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AskContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }


}
