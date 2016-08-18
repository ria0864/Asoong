package org.androidtown.tauction1;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAskAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<MyAskData> arrData;
    private LayoutInflater inflater;

    public MyAskAdapter(Context c, ArrayList<MyAskData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getMy_ask_title();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.my_ask_list, parent, false);
        }

        TextView my_ask_reg_day = (TextView)convertView.findViewById(R.id.my_ask_reg_day);
        my_ask_reg_day.setText(arrData.get(position).getMy_ask_reg_day());

        TextView my_ask_title = (TextView)convertView.findViewById(R.id.my_ask_title);
        my_ask_title.setText(arrData.get(position).getMy_ask_title());

        TextView my_ask_content = (TextView)convertView.findViewById(R.id.my_ask_content);
        my_ask_content.setText(arrData.get(position).getMy_ask_content());

        TextView my_ask_commentNo = (TextView)convertView.findViewById(R.id.my_ask_commentNo);
        my_ask_commentNo.setText(arrData.get(position).getMy_ask_commentNo());

        Button btn_modify = (Button)convertView.findViewById(R.id.btn_modify);
        btn_modify.setOnClickListener(new View.OnClickListener() {//내 문의글 수정버튼 클릭시
            @Override
            public void onClick(View v) {

            }
        });
        Button btn_delete = (Button)convertView.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {//내 문의글 삭제버튼 클릭시
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }


}
