package org.androidtown.tauction1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SSU on 2016-08-11.
 */
public class FragmentA_1 extends Fragment{
    ImageButton goFragmentA;

    int type = 1;
    ListView list;
    AskAdapter adapter;
    ArrayList<AskListData> arrListData;
    ArrayList<AskData> arrData; //얘 원래 없음
    ViewGroup rootView;

    Button btn_filter;

    public void setType(int type){ this.type=type;}

    Button btn_all, btn_busan, btn_seoul, btn_incheon, btn_gangwon, btn_jeju, btn_jeolla, btn_gyeongsang, btn_chungcheong;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_a_1, container, false);

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
                Toast.makeText(getActivity(), "전체 클릭", Toast.LENGTH_SHORT).show();
                selectButton("all");
            }
        });
        btn_busan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton("busan");
                final ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                    @Override
                    public String handleResponse(HttpResponse response) throws IOException {

                        int eventType;
                        int ask_no = 0;
                        Date ask_date = null;
                        String ask_title = null, ask_contents = null;
                        int done= 0, reg_no= 0, ask_num= 0;
                        String ask_type = null, ask_gender = null;
                        String ask_trip = null;
                        int ask_budget = 0;
                        String ask_convin = null;
                        Date ask_startday = null, ask_endday = null;
                        String ask_pay = null;
                        int mem_no = 0;

                        HttpEntity entity = response.getEntity();

                        try {
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = factory.newPullParser();
                            InputStreamReader isreader = new InputStreamReader(entity.getContent(), "UTF-8");
                            parser.setInput(isreader);
                            if (arrListData == null) {
                                arrListData = new ArrayList<AskListData>();
                            }
                            else {
                                arrData.clear();
                            }

                            eventType = parser.getEventType();
                            System.out.println("start while");
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String tagName;
                                System.out.println("start switch");
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        tagName = parser.getName();
                                        System.out.println("start tagName : " + tagName);
                                        if (tagName != null && tagName.equals("ask_no")) {
                                            ask_no = Integer.parseInt(parser.nextText());
                                            System.out.println("ask_no ="+ask_no);
                                        }else if (tagName != null && tagName.equals("ask_date")) {
                                            ask_date = new Date(parser.nextText());
                                            System.out.println("ask_date");
                                        }else if (tagName != null && tagName.equals("ask_title")) {
                                            ask_title = parser.nextText();
                                            System.out.println("ask_title");
                                        }else if (tagName != null && tagName.equals("ask_contents")) {
                                            ask_contents = parser.nextText();
                                            System.out.println("ask_contents");
                                        }else if (tagName != null && tagName.equals("done")) {
                                            done = Integer.parseInt(parser.nextText());
                                            System.out.println("done");
                                        }else if (tagName != null && tagName.equals("reg_no")) {
                                            reg_no = Integer.parseInt(parser.nextText());
                                            System.out.println("reg_no");
                                        }else if (tagName != null && tagName.equals("ask_num")) {
                                            ask_num = Integer.parseInt(parser.nextText());
                                            System.out.println("ask_num");
                                        }else if (tagName != null && tagName.equals("ask_type")) {
                                            ask_type = parser.nextText();
                                            System.out.println("ask_type");
                                        }else if (tagName != null && tagName.equals("ask_gender")) {
                                            ask_gender = parser.nextText();
                                            System.out.println("ask_gender");
                                        }else if (tagName != null && tagName.equals("ask_trip")) {
                                            ask_trip = parser.nextText();
                                            System.out.println("ask_trip");
                                        }else if (tagName != null && tagName.equals("ask_budget")) {
                                            ask_budget = Integer.parseInt(parser.nextText());
                                            System.out.println("ask_budget");
                                        }else if (tagName != null && tagName.equals("ask_convin")) {
                                            ask_convin = parser.nextText();
                                            System.out.println("ask_convin");
                                        }else if (tagName != null && tagName.equals("ask_startday")) {
                                            ask_startday = new Date(parser.nextText());
                                            System.out.println("ask_startday");
                                        }else if (tagName != null && tagName.equals("ask_endday")) {
                                            ask_endday = new Date(parser.nextText());
                                            System.out.println("ask_endday");
                                        }else if (tagName != null && tagName.equals("ask_pay")) {
                                            ask_pay = parser.nextText();
                                            System.out.println("ask_pay");
                                        }else if (tagName != null && tagName.equals("mem_no")) {
                                            mem_no = Integer.parseInt(parser.nextText());
                                            System.out.println("mem_no");
                                        }else break;
                                    case XmlPullParser.END_TAG:
                                        tagName = parser.getName();
                                        System.out.println("end tagName : " + tagName);
                                        if (tagName != null && tagName.equals("asklist")) {
                                            arrListData.add(new AskListData(ask_no, ask_date, ask_title, ask_contents, done, reg_no, ask_num,
                                                    ask_type, ask_gender, ask_trip, ask_budget, ask_convin, ask_startday, ask_endday, ask_pay, mem_no));
                                            System.out.println("add AskListData");
                                        }
                                        break;
                                   /* default:
                                        tagName = parser.getName();
                                        System.out.println("default tagName : " + tagName);
                                        break;*/
                                }
                                eventType = parser.next();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        /*Message message = handler.obtainMessage();
                        Bundle bundle = new Bundle();

                        bundle.putString("RESULT", "success");
                        message.setData(bundle);
                        handler.sendMessage(message);*/

                        FragmentA_1.this.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                attachAdapter();
                            }
                        });

                        return "success";
                    }
                };

                new Thread() {
                    @Override
                    public void run() {
                        String url = "http://52.78.101.183:8080/tauction/ask.jsp";
                        getDataFromServer(responseHandler, url, "ask_list", true, -1);
                    }
                }.start(); //스레드 실행
               // goAll();
                Toast.makeText(getActivity(), "부산2 클릭", Toast.LENGTH_SHORT).show();
            }
        });
        //어댑터 연결
        attachAdapter();

        //리스트뷰에 클릭 리스터 연결
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 게시물 보기
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int ask = arrData.get(position).getAsk_no();
                final ResponseHandler<String> responseHandler =  new ResponseHandler<String>(){
                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

                        Intent intent = new Intent(getActivity(), PostingContentActivity.class);

                        AskData asking = null;
                        int eventType;
                        int image_region=1; //얘는 어떡하지..?

                        int ask_no = ask;
                        int done=0;
                        int reg_no = 0;
                        Date ask_startday = null;
                        Date ask_endday = null;
                        int ask_budget = 0;
                        int ask_num =0;
                        int mem_no = -1;
                        String mem_id = "";
                        int ask_commentNo=0;
                        HttpEntity entity = response.getEntity();

                        try{
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = factory.newPullParser();
                            InputStreamReader isreader = new InputStreamReader(entity.getContent(),"UTF-8");
                            parser.setInput(isreader);
                            eventType = parser.getEventType();

                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String tagName;
                                switch(eventType) {
                                    case XmlPullParser.START_TAG:
                                        tagName = parser.getName();
                                        if(tagName != null && tagName.equals("ask_no")) {
                                            ask_no = Integer.parseInt(parser.nextText());
                                        }else if(tagName != null && tagName.equals("done")) {
                                            done = Integer.parseInt(parser.nextText());
                                        }  else if(tagName != null && tagName.equals("reg_no")) {
                                            reg_no = Integer.parseInt(parser.nextText());
                                        } else if(tagName != null && tagName.equals("ask_startday")) {
                                            ask_startday = new Date(parser.nextText());
                                        } else if(tagName != null && tagName.equals("ask_endday")) {
                                            ask_endday = new Date(parser.nextText());
                                        } else if(tagName != null && tagName.equals("ask_budget")) {
                                            ask_budget = Integer.parseInt(parser.nextText());
                                        } else if(tagName != null && tagName.equals("ask_num")) {
                                            ask_num = Integer.parseInt(parser.nextText());
                                        } else if(tagName != null && tagName.equals("mem_no")) {
                                            mem_no = Integer.parseInt(parser.nextText());
                                        } else if(tagName != null && tagName.equals("mem_id")) {
                                            mem_id = parser.nextText();
                                        }else if(tagName != null && tagName.equals("ask_commentNo")) {
                                            ask_commentNo = Integer.parseInt(parser.nextText());
                                        }
                                        break;
                                    case XmlPullParser.END_TAG:
                                        tagName = parser.getName();
                                        if(tagName != null && tagName.equals("asking")) {
                                            asking = new AskData(image_region,ask_no,reg_no,done,ask_startday,ask_endday,ask_budget,ask_num, mem_no, mem_id,ask_commentNo);
                                        }
                                        break;
                                }
                                eventType = parser.next();
                            }
                        }catch(Exception e){e.printStackTrace();}

                        //게시글을 보여줄 액티비티에 데이터 전달
                        intent.putExtra("image_region",asking.getImage_region());
                        intent.putExtra("ask_no",asking.getAsk_no());
                        intent.putExtra("done",asking.getDone());
                        intent.putExtra("reg_no",asking.getReg_no());
                        intent.putExtra("ask_startday",asking.getAsk_startday());
                        intent.putExtra("ask_endday",asking.getAsk_startday());
                        intent.putExtra("ask_budget",asking.getAsk_budget());
                        intent.putExtra("ask_num",asking.getAsk_num());
                        intent.putExtra("mem_number",asking.getMem_no());
                        intent.putExtra("id",asking.getMem_id());
                        startActivity(intent);

                        return "success";
                    }
                };

                new Thread(){
                    @Override
                    public void run(){
                        String url = "http://52.78.101.183:8080/tauction/ask.jsp";
                        getDataFromServer(responseHandler, url,"get_ask", false, ask); // 위치로 검색
                    }
                }.start(); //스레드 실행
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

        return rootView;
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

    private void setData(){
        final ResponseHandler<String> responseHandler =  new ResponseHandler<String>(){
            @Override
            public String handleResponse(HttpResponse response) throws IOException {

                int eventType;
                int ask_no = 0;
                Date ask_date = null;
                String ask_title = null, ask_contents = null;
                int done= 0, reg_no= 0, ask_num= 0;
                String ask_type = null, ask_gender = null;
                String ask_trip = null;
                int ask_budget = 0;
                String ask_convin = null;
                Date ask_startday = null, ask_endday = null;
                String ask_pay = null;
                int mem_no = 0;
                HttpEntity entity = response.getEntity();

                try{
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    InputStreamReader isreader = new InputStreamReader(entity.getContent(),"UTF-8");
                    parser.setInput(isreader);
                    if(arrData == null) {
                        arrData = new ArrayList<AskData>();
                    } else {
                        arrData.clear();
                    }
                    eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        String tagName;
                        System.out.println("start switch");
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                tagName = parser.getName();
                                System.out.println("start tagName : " + tagName);
                                if (tagName != null && tagName.equals("ask_no")) {
                                    ask_no = Integer.parseInt(parser.nextText());
                                    System.out.println("ask_no ="+ask_no);
                                }else if (tagName != null && tagName.equals("ask_date")) {
                                    ask_date = new Date(parser.nextText());
                                    System.out.println("ask_date");
                                }else if (tagName != null && tagName.equals("ask_title")) {
                                    ask_title = parser.nextText();
                                    System.out.println("ask_title");
                                }else if (tagName != null && tagName.equals("ask_contents")) {
                                    ask_contents = parser.nextText();
                                    System.out.println("ask_contents");
                                }else if (tagName != null && tagName.equals("done")) {
                                    done = Integer.parseInt(parser.nextText());
                                    System.out.println("done");
                                }else if (tagName != null && tagName.equals("reg_no")) {
                                    reg_no = Integer.parseInt(parser.nextText());
                                    System.out.println("reg_no");
                                }else if (tagName != null && tagName.equals("ask_num")) {
                                    ask_num = Integer.parseInt(parser.nextText());
                                    System.out.println("ask_num");
                                }else if (tagName != null && tagName.equals("ask_type")) {
                                    ask_type = parser.nextText();
                                    System.out.println("ask_type");
                                }else if (tagName != null && tagName.equals("ask_gender")) {
                                    ask_gender = parser.nextText();
                                    System.out.println("ask_gender");
                                }else if (tagName != null && tagName.equals("ask_trip")) {
                                    ask_trip = parser.nextText();
                                    System.out.println("ask_trip");
                                }else if (tagName != null && tagName.equals("ask_budget")) {
                                    ask_budget = Integer.parseInt(parser.nextText());
                                    System.out.println("ask_budget");
                                }else if (tagName != null && tagName.equals("ask_convin")) {
                                    ask_convin = parser.nextText();
                                    System.out.println("ask_convin");
                                }else if (tagName != null && tagName.equals("ask_startday")) {
                                    ask_startday = new Date(parser.nextText());
                                    System.out.println("ask_startday");
                                }else if (tagName != null && tagName.equals("ask_endday")) {
                                    ask_endday = new Date(parser.nextText());
                                    System.out.println("ask_endday");
                                }else if (tagName != null && tagName.equals("ask_pay")) {
                                    ask_pay = parser.nextText();
                                    System.out.println("ask_pay");
                                }else if (tagName != null && tagName.equals("mem_no")) {
                                    mem_no = Integer.parseInt(parser.nextText());
                                    System.out.println("mem_no");
                                }else break;
                            case XmlPullParser.END_TAG:
                                tagName = parser.getName();
                                System.out.println("end tagName : " + tagName);
                                if (tagName != null && tagName.equals("asklist")) {
                                    arrListData.add(new AskListData(ask_no, ask_date, ask_title, ask_contents, done, reg_no, ask_num,
                                            ask_type, ask_gender, ask_trip, ask_budget, ask_convin, ask_startday, ask_endday, ask_pay, mem_no));
                                    System.out.println("add AskListData");
                                }
                                break;
                                   /* default:
                                        tagName = parser.getName();
                                        System.out.println("default tagName : " + tagName);
                                        break;*/
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FragmentA_1.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        attachAdapter();
                    }
                });
                return "success";
            }
        };

        new Thread(){
            @Override
            public void run(){
                String url = "http://52.78.101.183:8080/tauction/ask.jsp";
                getDataFromServer(responseHandler, url,"ask_list", false, -1); // 전체 검색
            }
        }.start(); //스레드 실행
    }

    private void getDataFromServer(ResponseHandler<String> responseHandler, String url, String action, boolean flag, int position) {
        //flag == true : 제목 검색, flag == false : 전체 조회
        //position : 게시물 클릭 시에만 사용
        HttpClient client = new DefaultHttpClient();
        try{
            String strType;
            ArrayList<NameValuePair> nameValuePairs =
                    new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("action",action));
            if(action.equals("ask_list")) {
                //nameValuePairs.add(new BasicNameValuePair("reg_name","all"));
                if (flag) {
                    //  nameValuePairs.add(new BasicNameValuePair("title", searchTitle.getText().toString()));
                }
            }
            else {
                if(position > -1) {
                    nameValuePairs.add(new BasicNameValuePair("askpost_no", "" + position));
                }
            }
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

    private void attachAdapter() {
        //어댑터 생성
        if(arrData == null) {
            arrData = new ArrayList<AskData>();
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new AskAdapter(rootView.getContext(), arrData);

            //리스트뷰에 어댑터 연결
            if (list != null) {
                list.setAdapter(adapter);
            } else {
                list = (ListView) rootView.findViewById(R.id.list_posting);
                list.setAdapter(adapter);
            }
        }
    }

}

