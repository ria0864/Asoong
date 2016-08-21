package org.androidtown.tauction1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
        return arrData.get(position).getAsk_title();
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

        TextView mem_id = (TextView)convertView.findViewById(R.id.ask_content_userName);
        mem_id.setText(arrData.get(position).getMem_id());

        TextView ask_date = (TextView)convertView.findViewById(R.id.ask_content_reg_day);
        ask_date.setText((CharSequence) arrData.get(position).getAsk_date());

        ToggleButton heart = (ToggleButton)convertView.findViewById(R.id.toggle_heart);
        if(1==arrData.get(position).getIsLike()){
            heart.setChecked(true);
        }else{
            heart.setChecked(false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.ask_content_title);
        title.setText(arrData.get(position).getAsk_title());

        TextView content = (TextView)convertView.findViewById(R.id.content);
        content.setText(arrData.get(position).getAsk_contents());

        TextView region = (TextView)convertView.findViewById(R.id.ask_content_region);
        switch (arrData.get(position).getReg_no()){
            case '1':
                region.setText("지역 : 전체");
                break;
            case '2':
                region.setText("지역 : 부산");
                break;
            case '3':
                region.setText("지역 : 서울");
                break;
            case '4':
                region.setText("지역 : 인천");
                break;
            case '5':
                region.setText("지역 : 강원도");
                break;
            case '6':
                region.setText("지역 : 제주도");
                break;
            case '7':
                region.setText("지역 : 전라도");
                break;
            case '8':
                region.setText("지역 : 경상도");
                break;
            case '9':
                region.setText("지역 : 충천도");
                break;
        }

        TextView ask_num = (TextView)convertView.findViewById(R.id.ask_content_peopleNum);
        ask_num.setText("인원수 : "+arrData.get(position).getAsk_num()+"명");

        TextView ask_type = (TextView)convertView.findViewById(R.id.ask_content_type);
        ask_type.setText("숙소유형 : "+arrData.get(position).getAsk_type());

        TextView ask_gender = (TextView)convertView.findViewById(R.id.ask_content_gender);
        ask_gender.setText("성별 : "+arrData.get(position).getAsk_gender());

        TextView ask_trip = (TextView)convertView.findViewById(R.id.ask_content_travel);
        ask_trip.setText("여행유형 : "+arrData.get(position).getAsk_trip());

        TextView price = (TextView)convertView.findViewById(R.id.price);
        price.setText("가격범위 : ~"+arrData.get(position).getAsk_budget());

        TextView convin = (TextView)convertView.findViewById(R.id.ask_content_facility);
        convin.setText("편의시설 : "+arrData.get(position).getAsk_convin());

        TextView sday = (TextView)convertView.findViewById(R.id.ask_content_startDay);
        sday.setText("입실날짜 : " + arrData.get(position).getAsk_startday());

        TextView eday = (TextView)convertView.findViewById(R.id.ask_content_endDay);
        eday.setText("퇴실날짜 : " + arrData.get(position).getAsk_endday());

        TextView pay = (TextView)convertView.findViewById(R.id.ask_content_payOption);
        pay.setText("결제수단 : " + arrData.get(position).getAsk_pay());

        return convertView;
    }
}
