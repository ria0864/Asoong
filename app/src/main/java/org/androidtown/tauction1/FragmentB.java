package org.androidtown.tauction1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class FragmentB extends Fragment {
    //listView
    ListView list;
    MyAdapter adapter;
    ArrayList<MyData> arrData;


    EditText edit_search;
    Button btn_filter;
    Button btn_all, btn_busan, btn_seoul, btn_incheon, btn_gangwon, btn_jeju, btn_jeolla, btn_gyeongsang, btn_chungcheong;

    ProgressDialog pDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_b, container, false);
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_b,container,false);

        edit_search=(EditText)rootView.findViewById(R.id.edit_search);
        btn_filter=(Button)rootView.findViewById(R.id.btn_filter);

        btn_all=(Button)rootView.findViewById(R.id.btn_all);
        btn_busan=(Button)rootView.findViewById(R.id.btn_busan);
        btn_seoul=(Button)rootView.findViewById(R.id.btn_seoul);
        btn_incheon=(Button)rootView.findViewById(R.id.btn_incheon);
        btn_gangwon=(Button)rootView.findViewById(R.id.btn_gangwon);
        btn_jeju=(Button)rootView.findViewById(R.id.btn_jeju);
        btn_jeolla=(Button)rootView.findViewById(R.id.btn_jeolla);
        btn_gyeongsang=(Button)rootView.findViewById(R.id.btn_gyeongsang);
        btn_chungcheong=(Button)rootView.findViewById(R.id.btn_chungcheong);

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FilterActivity1.class));
            }
        });
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btn_all.setBackgroundResource(R.drawable.button_selected);
                //btn_all.setTextColor(Color.WHITE);

                selectButton("all");
                list.setAdapter(adapter);
            }
        });
        btn_busan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("busan");
                list.setAdapter(adapter);
            }
        });
        btn_seoul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("seoul");
                list.setAdapter(adapter);
            }
        });
        btn_incheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("incheon");
                list.setAdapter(adapter);
            }
        });
        btn_gangwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("gangwon");
                list.setAdapter(adapter);
            }
        });
        btn_jeju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("jeju");
                list.setAdapter(adapter);
            }
        });
        btn_jeolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("jeolla");
                list.setAdapter(adapter);
            }
        });
        btn_gyeongsang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("gyeongsang");
                list.setAdapter(adapter);
            }
        });
        btn_chungcheong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("chungcheong");
                list.setAdapter(adapter);
            }
        });

        arrData = new ArrayList<MyData>();
        //리스트에 보여줄 데이터를 세팅한다.
        selectButton("all");
        //setData();

        //어댑터 생성
        adapter = new MyAdapter(rootView.getContext(), arrData);

        //리스트뷰에 어댑터 연결
        list = (ListView)rootView.findViewById(R.id.list_accommodation);
        list.setAdapter(adapter);

        return rootView;
    }

    private void setData(){
       // arrData = new ArrayList<MyData>();

        //여기에서 리스트뷰 아이템 추가 코드 int image, String rank, String like_num, String name, String address
/*        arrData.add(new MyData(R.drawable.toscana, "1","259","토스카나 호텔","제주특별자치도 서귀포시 용흥로 66번길 158-7"));
        arrData.add(new MyData(R.drawable.maru_pension, "2","190","별빛마루 펜션","경기도 가평군 북면 적목리"));
        arrData.add(new MyData(R.drawable.sheraton_hotel, "3", "177","쉐라톤 그랜드 워커힐","서울특별시 광진구 워커힐로 177 쉐라톤워커힐호텔"));
    */

    }
    private void selectButton(final String selected_btn){//서버코드 넣기
        if(selected_btn.equals("all")){
            btn_all.setBackgroundResource(R.drawable.button_selected);
            btn_all.setTextColor(Color.WHITE);
        }else{
            btn_all.setBackgroundResource(R.drawable.button_region);
            btn_all.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("busan")){
            btn_busan.setBackgroundResource(R.drawable.button_selected);
            btn_busan.setTextColor(Color.WHITE);
        }else{
            btn_busan.setBackgroundResource(R.drawable.button_region);
            btn_busan.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("seoul")){
            btn_seoul.setBackgroundResource(R.drawable.button_selected);
            btn_seoul.setTextColor(Color.WHITE);
        }else{
            btn_seoul.setBackgroundResource(R.drawable.button_region);
            btn_seoul.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("incheon")){
            btn_incheon.setBackgroundResource(R.drawable.button_selected);
            btn_incheon.setTextColor(Color.WHITE);
        }else{
            btn_incheon.setBackgroundResource(R.drawable.button_region);
            btn_incheon.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("gangwon")){
            btn_gangwon.setBackgroundResource(R.drawable.button_selected);
            btn_gangwon.setTextColor(Color.WHITE);
        }else{
            btn_gangwon.setBackgroundResource(R.drawable.button_region);
            btn_gangwon.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("jeju")){
            btn_jeju.setBackgroundResource(R.drawable.button_selected);
            btn_jeju.setTextColor(Color.WHITE);
        }else{
            btn_jeju.setBackgroundResource(R.drawable.button_region);
            btn_jeju.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("jeolla")){
            btn_jeolla.setBackgroundResource(R.drawable.button_selected);
            btn_jeolla.setTextColor(Color.WHITE);
        }else{
            btn_jeolla.setBackgroundResource(R.drawable.button_region);
            btn_jeolla.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("gyeongsang")){
            btn_gyeongsang.setBackgroundResource(R.drawable.button_selected);
            btn_gyeongsang.setTextColor(Color.WHITE);
        }else{
            btn_gyeongsang.setBackgroundResource(R.drawable.button_region);
            btn_gyeongsang.setTextColor(Color.BLACK);
        }

        if(selected_btn.equals("chungcheong")){
            btn_chungcheong.setBackgroundResource(R.drawable.button_selected);
            btn_chungcheong.setTextColor(Color.WHITE);
        }else{
            btn_chungcheong.setBackgroundResource(R.drawable.button_region);
            btn_chungcheong.setTextColor(Color.BLACK);
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

        pDialog = ProgressDialog.show(getContext(), "", "데이타 전송중..");

        Thread workingThread = new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/enter.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs =
                            new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","enter_rank_region"));
                    nameValuePairs.add(new BasicNameValuePair("reg_name",selected_btn));
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

        try{
            workingThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();;
        }
    }
    private final Handler handler = new Handler(){
        public void handleMessage(Message msg){

            System.out.println("핸들러시작");
            String result = msg.getData().getString("RESULT");
            //Intent j = new Intent(FragmentB.this,MainActivity.class);
            //j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //j.putExtra("mem_id",editTextID.getText().toString());
            if(result.equals("success")){
                Toast.makeText(getContext(), "성공", Toast.LENGTH_LONG).show();
                pDialog.dismiss();

            }else{
                Toast.makeText(getContext(), "실패", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        }
    };

    public String parsingData(InputStream input){
        String result = null;
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
            arrData.clear();


            String[] r_split = result.split("\\/");
            System.out.println("action:"+r_split[0]);
            System.out.println("정보:"+r_split[1]);

            if(r_split[0].equals("enter_rank_region")){
                String[] token = r_split[1].split("\\$");

                int i=1;
                for(String str : token){
                    String[] token2 = str.split("\\|");

                    System.out.println(i);
                    System.out.println(token2[2]);
                    System.out.println(token2[0]);
                    System.out.println(token2[1]);
                    arrData.add(new MyData(R.drawable.toscana, Integer.toString(i++),token2[2],token2[0],token2[1]));
                }
                return "success";
            }else{
                return "fail";
            }
        }catch(Exception e){e.printStackTrace();}
        return "fail";
    }
}


