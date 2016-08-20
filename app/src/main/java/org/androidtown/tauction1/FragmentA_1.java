package org.androidtown.tauction1;

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
import android.widget.ImageButton;
import android.widget.ListView;

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
import java.util.StringTokenizer;

/**
 * Created by SSU on 2016-08-11.
 */
public class FragmentA_1 extends Fragment{

    ListView list;
    AskAdapter adapter;
    ArrayList<AskData> arrData;

    ImageButton goFragmentA;
    Button btn_filter;

    Button btn_all, btn_busan, btn_seoul, btn_incheon, btn_gangwon, btn_jeju, btn_jeolla, btn_gyeongsang, btn_chungcheong;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_a_1, container, false);
        btn_all=(Button)rootView.findViewById(R.id.btn_all);
        btn_busan=(Button)rootView.findViewById(R.id.btn_busan);
        btn_seoul=(Button)rootView.findViewById(R.id.btn_seoul);
        btn_incheon=(Button)rootView.findViewById(R.id.btn_incheon);
        btn_gangwon=(Button)rootView.findViewById(R.id.btn_gangwon);
        btn_jeju=(Button)rootView.findViewById(R.id.btn_jeju);
        btn_jeolla=(Button)rootView.findViewById(R.id.btn_jeolla);
        btn_gyeongsang=(Button)rootView.findViewById(R.id.btn_gyeongsang);
        btn_chungcheong=(Button)rootView.findViewById(R.id.btn_chungcheong);

        btn_filter=(Button)rootView.findViewById(R.id.btn_filter);

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FilterActivity.class));
            }
        });



        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btn_all.setBackgroundResource(R.drawable.button_selected);
                //btn_all.setTextColor(Color.WHITE);

                selectButton("all");
            }
        });
        btn_busan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("busan");
            }
        });
        btn_seoul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("seoul");
            }
        });
        btn_incheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("incheon");
            }
        });
        btn_gangwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("gangwon");
            }
        });
        btn_jeju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("jeju");
            }
        });
        btn_jeolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("jeolla");
            }
        });
        btn_gyeongsang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("gyeongsang");
            }
        });
        btn_chungcheong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("chungcheong");
            }
        });

        goFragmentA =(ImageButton)rootView.findViewById(R.id.goFragmentA);
        goFragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPage(0,0); //프레그먼트 A a1에서 a로 전환
            }
        });

        //리스트에 보여줄 데이터를 세팅한다.
        setData();

        //어댑터 생성
        adapter = new AskAdapter(rootView.getContext(), arrData);

        //리스트뷰에 어댑터 연결
        list = (ListView)rootView.findViewById(R.id.list_accommodation_region);
        list.setAdapter(adapter);

        return rootView;


    }

    public void goAll(){
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


        new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/ask.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs =
                            new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","ask_list"));
                    nameValuePairs.add(new BasicNameValuePair("reg_name","전체"));
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
        }.start(); //스레드 실행
    }
    public List<AskData> parse(String result){
        StringTokenizer st = new StringTokenizer(result, "|");
        AskData ad = null;
        List<AskData> list = new ArrayList<>();
        while(st.hasMoreElements()){
            ad.setAsk_commentNo((Integer.parseInt(st.nextToken())));
            java.util.Date ask_date = new java.util.Date(st.nextToken());
            ad.setAsk_date(ask_date);
            ad.setAsk_title(st.nextToken());
            ad.setAsk_contents(st.nextToken());
            ad.setReg_no(Integer.parseInt(st.nextToken()));
            ad.setAsk_num(Integer.parseInt(st.nextToken()));
            ad.setAsk_type(st.nextToken());
            ad.setAsk_gender(st.nextToken());
            ad.setAsk_trip(st.nextToken());
            ad.setAsk_budget(Integer.parseInt(st.nextToken()));
            ad.setAsk_convin(st.nextToken());
            java.util.Date startDay = new java.util.Date(st.nextToken());
            ad.setAsk_startday(startDay);
            java.util.Date endDay = new java.util.Date(st.nextToken());
            ad.setAsk_endday(endDay);
            ad.setAsk_pay(st.nextToken());
            ad.setMem_no(Integer.parseInt(st.nextToken()));
            list.add(ad);
        }
        return list;
    }
    private final Handler handler = new Handler(){
        public void handleMessage(Message msg){ //핸들러, 받고 보내는거

            String result = msg.getData().getString("RESULT");
            // Intent j = new Intent(FragmentA_1.this, MainActivity.class);
            // j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if(result.equals("success")){
                //Toast.makeText(this.getClass(), "성공", Toast.LENGTH_LONG).show();
                // pDialog.dismiss();
                // startActivity(j);
                // finish();
            }else{
                //Toast.makeText(FragmentA_1.this, "실패", Toast.LENGTH_LONG).show();
                // pDialog.dismiss();
            }
        }
    };
    public String parsingData(InputStream input){ //오는거 받아서 파싱하기
        String result = null;
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(input));
            while(parser.next() != XmlPullParser.END_DOCUMENT){
                String name = parser.getName();
                if(name != null && name.equals("result")) //<result> </result> 태그
                {
                    result = parser.nextText();
                    System.out.println(result);
                    parser.next();
                    name = parser.getName();
                    if(name != null && name.equals("context")){
                        List<AskData> list=parse(parser.nextText());
                        System.out.println(list);
                    }
                }
            }
        }catch(Exception e){e.printStackTrace();}
        System.out.println(result);
        return result;
    }

    private void setData(){
        arrData = new ArrayList<AskData>();
        //여기에서 리스트뷰 아이템 추가 코드
        /*arrData.add(new AskData(R.drawable.sheraton_hotel, "경매완료", "남대문", "7월 9일~7월 11일","~300,000","5명","+1"));
        arrData.add(new AskData(R.drawable.sheraton_hotel, "경매완료", "남산", "7월 20일","100,000~200,000","2명","+1"));
        arrData.add(new AskData(R.drawable.sheraton_hotel, "경매완료", "남산", "7월 20일","100,000~200,000","2명","+1"));
        arrData.add(new AskData(R.drawable.sheraton_hotel, "경매완료", "남산", "7월 20일","100,000~200,000","2명","+1"));
        arrData.add(new AskData(R.drawable.sheraton_hotel, "경매완료", "남산", "7월 20일","100,000~200,000","2명","+1"));*/

    }
    private void selectButton(String selected_btn){//서버코드 넣기
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
    }

    }

