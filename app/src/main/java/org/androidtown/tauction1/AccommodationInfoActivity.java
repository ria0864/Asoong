package org.androidtown.tauction1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ToggleButton;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class AccommodationInfoActivity extends AppCompatActivity {

    ImageButton btn_back;

    ImageView image_info;
    ToggleButton toggle_like;
    TextView name_info, location_info, tel_info, like_number, introInfo;
    EditText edt_review;
    Button btn_register;

    ListView list;
    private ArrayList<MyData> arrData;
    ArrayList<MyDataReview> arrDataReview;
    MyAdapterReview adapter;

    ScrollView mScrollView;
    ProgressDialog pDialog;
    String name,mem_id,like,contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_info);


        image_info = (ImageView)findViewById(R.id.acco_image);
        name_info = (TextView)findViewById(R.id.name);
        toggle_like = (ToggleButton)findViewById(R.id.toggle_like); //업체 좋아요 토글버튼. 누르면 즐겨찾기에 추가됨
        location_info = (TextView)findViewById(R.id.location_info) ; //주소
        tel_info = (TextView)findViewById(R.id.tel_info); //전화번호
        like_number = (TextView)findViewById(R.id.like_number); //업체 좋아요 수
        introInfo = (TextView)findViewById(R.id.introInfo); //인사말
        edt_review = (EditText)findViewById(R.id.edt_review); //리뷰 editText
        btn_register = (Button)findViewById(R.id.btn_register); // 리뷰 등록 버튼

        Intent i = getIntent();
//        String image = i.getExtras().getString("image");
        name = i.getExtras().getString("name");

        SharedPreferences setting = getSharedPreferences("setting", 0);
        mem_id = setting.getString("mem_id",null);
        System.out.println("이게바로 내아이디여야해"+setting.getString("mem_id",null));





        //이 유저가 즐찾했는지 안했는지 서버에서 받아와야함
        //업체 리뷰정보도 받아와야
        arrDataReview = new ArrayList<MyDataReview>();
        go_server();
        get_review();

        //이미지도 받아와야해 근데 이미지 int형
//        toggle_like.setChecked(true);

        toggle_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                set_like(isChecked);
            }
        });

                btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_review(edt_review.getText().toString());
                edt_review.setText("");
                Toast.makeText(getApplicationContext(), "후기가 등록되었습니다.", Toast.LENGTH_LONG).show();
                get_review();
                //list.setAdapter(adapter);후기새로고침안대ㅜㅜㅜㅜㅜ허우ㅜㅜㅜㅜ
            }
        });

        //리스트에 보여줄 데이터를 세팅한다.
       // setData();

        //어댑터 생성
        adapter = new MyAdapterReview(this, arrDataReview);

        //리스트뷰에 어댑터 연결
        list = (ListView)findViewById(R.id.list_review);

        list.setAdapter(adapter);

        //setScroll(list);

        setListViewHeightBasedOnChildren(list);
        mScrollView=(ScrollView)findViewById(R.id.ScrollView);
        ImageView mImage = (ImageView) findViewById(R.id.acco_image);
        mScrollView.requestChildFocus(null,mImage);
        mScrollView.scrollTo(0,0);
        mScrollView.invalidate();

    }
    private void setData(){//레이아웃 크기에 맞게 글자수 제한할 것
        arrDataReview = new ArrayList<MyDataReview>();
        arrDataReview.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrDataReview.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrDataReview.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrDataReview.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrDataReview.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrDataReview.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrDataReview.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));


    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight - (listView.getDividerHeight() * (listAdapter.getCount() - 1)*30);
        //Log.d("MyLog",""+ totalHeight);
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /*
    private void setScroll(ListView mListView){
        mListView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }
    */
    private void add_review(String review){
        contents = review;
        System.out.println(contents);


        new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/enter.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","add_review"));
                    nameValuePairs.add(new BasicNameValuePair("enter_name",name));
                    nameValuePairs.add(new BasicNameValuePair("mem_id",mem_id));
                    nameValuePairs.add(new BasicNameValuePair("review_contents",contents));
                    //타임아웃
                    HttpParams params = client.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 2000);
                    HttpConnectionParams.setSoTimeout(params, 2000);

                    HttpPost httpPost = new HttpPost(url);
                    UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                    httpPost.setEntity(entityRequest);
                    client.execute(httpPost, responseHandler);
                }catch(Exception e){e.printStackTrace();}
            }
        }.start();//스레드 실행
    }
    private void set_like(boolean isChecked){
        if(isChecked){
            like = "like";
        }else{
            like = "unlike";
        }

        new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/enter.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","set_enter_like"));
                    nameValuePairs.add(new BasicNameValuePair("enter_name",name));
                    nameValuePairs.add(new BasicNameValuePair("mem_id",mem_id));
                    nameValuePairs.add(new BasicNameValuePair("like",like));
                    //타임아웃
                    HttpParams params = client.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 2000);
                    HttpConnectionParams.setSoTimeout(params, 2000);

                    HttpPost httpPost = new HttpPost(url);
                    UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                    httpPost.setEntity(entityRequest);
                    client.execute(httpPost, responseHandler);
                }catch(Exception e){e.printStackTrace();}
            }
        }.start();//스레드 실행
    }

    private void get_review() {

        //     pDialog = ProgressDialog.show(getBaseContext(), "", "데이타 전송중..");

        Thread workingThread3 = new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/enter.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","get_review"));
                    nameValuePairs.add(new BasicNameValuePair("enter_name",name));
                    //타임아웃
                    HttpParams params = client.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 2000);
                    HttpConnectionParams.setSoTimeout(params, 2000);

                    HttpPost httpPost = new HttpPost(url);
                    UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                    httpPost.setEntity(entityRequest);
                    client.execute(httpPost, responseHandler);
                }catch(Exception e){e.printStackTrace();}
            }
        };
        workingThread3.start(); //스레드 실행

        try{
            workingThread3.join();
        }catch (InterruptedException e){
            e.printStackTrace();;
        }
    }
    private void go_server(){

        Thread workingThread = new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/enter.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","get_enter_info"));
                    nameValuePairs.add(new BasicNameValuePair("enter_name",name));
                    //타임아웃
                    HttpParams params = client.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 2000);
                    HttpConnectionParams.setSoTimeout(params, 2000);

                    HttpPost httpPost = new HttpPost(url);
                    UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                    httpPost.setEntity(entityRequest);
                    client.execute(httpPost, responseHandler);
                }catch(Exception e){e.printStackTrace();}
            }
        };
        workingThread.start(); //스레드 실행
        Thread workingThread2 = new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/enter.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","enter_like"));
                    nameValuePairs.add(new BasicNameValuePair("enter_name",name));
                    nameValuePairs.add(new BasicNameValuePair("mem_id",mem_id));
                    //타임아웃
                    HttpParams params = client.getParams();
                    HttpConnectionParams.setConnectionTimeout(params, 2000);
                    HttpConnectionParams.setSoTimeout(params, 2000);

                    HttpPost httpPost = new HttpPost(url);
                    UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                    httpPost.setEntity(entityRequest);
                    client.execute(httpPost, responseHandler);
                }catch(Exception e){e.printStackTrace();}
            }
        };
        workingThread2.start(); //스레드 실행
        try{
            workingThread.join();
            workingThread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();;
        }

    }

    final ResponseHandler<String> responseHandler =  new ResponseHandler<String>(){
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

            String result = null;
            HttpEntity entity = response.getEntity();
            result = parsingData(entity.getContent());
            //result = "success";
            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();

            if(result.equals("success")) result="success";
            else result="실패!";

            bundle.putString("RESULT", result);
            message.setData(bundle);
            handler.sendMessage(message);
            return result;
        }
    };

    private final Handler handler = new Handler(){
        public void handleMessage(Message msg){

            String result = msg.getData().getString("RESULT");
            //Intent j = new Intent(FragmentB.this,MainActivity.class);
            //j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //j.putExtra("mem_id",editTextID.getText().toString());
            if(result.equals("success")){
                Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_LONG).show();
    //            pDialog.dismiss();

            }else{
                Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_LONG).show();
     //           pDialog.dismiss();
            }
        }
    };

    public String parsingData(InputStream input){
        String result = null;
        System.out.println("데이터파싱시작");
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(input));
            while(parser.next() != XmlPullParser.END_DOCUMENT){
                String name = parser.getName();
                if(name != null && name.equals("result")) //<result> </result> 태그
                    result = parser.nextText();
            }

            System.out.println("서버에서 받아온 결과:"+result);
            //success/기|업|정|보$기|업|정|보
            //success/즐찾
            //success/리뷰
            if(result.equals("success")| result.equals("fail")) return result;

            String[] r_split = result.split("\\/");
            System.out.println("action:"+r_split[0]);
            System.out.println("정보:"+r_split[1]);

            if(r_split[0].equals("get_enter_info")){
                String[] token = r_split[1].split("\\$");

                for(String str : token){
                    String[] token2 = str.split("\\|");

                    System.out.println(token2[0]);
                    System.out.println(token2[1]);
                    System.out.println(token2[2]);
                    System.out.println(token2[3]);
                    System.out.println(token2[4]);

                    name_info.setText(token2[0]);
                    location_info.setText(token2[1]);
                    tel_info.setText(token2[2]);
                    like_number.setText(token2[3]);
                    introInfo.setText(token2[4]);

                }
                return "success";
            }else if(r_split[0].equals("enter_like")){
                String[] token = r_split[1].split("\\|");

                System.out.println(token[1]);
                if(token[1].equals("true")) {
                    toggle_like.setChecked(true);
                }else {
                    toggle_like.setChecked(false);
                }
                return "success";

            }else if(r_split[0].equals("enter_get_review")){
                arrDataReview.clear();
               String[] token = r_split[1].split("\\$");

                for(String str : token){
                    String[] token2 = str.split("\\|");

                    System.out.println(token2[0]);//mem_id
                    System.out.println(token2[1]);//date
                    System.out.println(token2[2]);//contents
                    arrDataReview.add(new MyDataReview(token2[0], token2[1], token2[2]));
                }
                return "success";
            }else  return "fail";

        }catch(Exception e){e.printStackTrace();}
        return "fail";
    }

}