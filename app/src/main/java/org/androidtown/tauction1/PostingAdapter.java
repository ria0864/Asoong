package org.androidtown.tauction1;
// 데이터 서버에서 받아오고, 이 객체에서
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PostingAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<PostingData> arrData;
    private LayoutInflater inflater;

    public PostingAdapter(Context c, ArrayList<PostingData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getPos_title();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.posting_list, parent, false);
        }

        TextView posting_title = (TextView)convertView.findViewById(R.id.posting_title);
        posting_title.setText(arrData.get(position).getPos_title());

        TextView posting_username = (TextView)convertView.findViewById(R.id.posting_username);
        // posting_username.setText(arrData.get(position).getPos_username()); //mem_no에 해당하는 mem_name(user_name) 받아오기.

        TextView posting_reg_day = (TextView)convertView.findViewById(R.id.posting_reg_day);
        //posting_reg_day.setText(arrData.get(position).getPos_reg_day()); // 이게 뭘까여.......?

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

    private final Handler handler = new Handler(){
        public void handleMessage(Message msg){

            String result = msg.getData().getString("RESULT");
           // Intent j = new Intent(this.getClass(), PostingContentActivity.class);
            //j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //j.putExtra("mem_id",editTextID.getText().toString());
            if(result.equals("success")){
             //   Toast.makeText(PostingAdapter.this, "성공", Toast.LENGTH_LONG).show();
                    /*pDialog.dismiss();
                    startActivity(j);
                    finish();*/
            }else{
               // Toast.makeText(PostingAdapter.this, "실패", Toast.LENGTH_LONG).show();
                //pDialog.dismiss();
            }
        }
    };

    public ArrayList<PostingData> parse(InputStream input){
        String result = null;
        ArrayList<PostingData> postingDatas = new ArrayList<PostingData>();
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(input));
            while(parser.next() != XmlPullParser.END_DOCUMENT){
                String name = parser.getName();
                if(name != null && name.equals("result")){
                    result = parser.nextText();
                }
                /*String name = parser.getName();
                if(name != null && name.equals("result")) //<result> </result> 태그
                    result = parser.nextText();*/  //여기 수정하기~~ 아마도?
            }
        }catch(Exception e){e.printStackTrace();}
        System.out.println(result);
        return postingDatas;
    }
}