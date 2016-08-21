package org.androidtown.tauction1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

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

/**
 * Created by SSU on 2016-08-11.
 */
public class FragmentC_1 extends Fragment{
    ImageButton goFragmentC;

    int type = 1;
    ListView list;
    Button textaddbtn;
    TalkPostListAdapter adapter;
    ArrayList<TalkPostListData> arrData;
    ViewGroup rootView;

    EditText searchTitle;
    Button btnSearch;

    Spinner yearSpinner;

    public void setType(int type) {
        this.type = type;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_c_1, container, false);

        goFragmentC=(ImageButton)rootView.findViewById(R.id.goFragmentC);
        goFragmentC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPage(2,0);
            }
        });

        yearSpinner = (Spinner)rootView.findViewById(R.id.spinner_post);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinnerPost, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(type-1); // 현재 들어온 타입으로 설정
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setType(position+1); // 타입 변경
                setDataByType(); // 데이터 다시 불러옴
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        textaddbtn = (Button)rootView.findViewById(R.id.btn_addPosting);
        textaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 게시물 쓰기
                Intent intent = new Intent(getActivity(),PostingTextAdd.class);
                if(((MainActivity) getActivity()).getMemId() != null){
                    intent.putExtra("mem_id", ((MainActivity) getActivity()).getMemId());
                }
                startActivity(intent);
            }
        });

        searchTitle = (EditText)rootView.findViewById(R.id.edit_posting_search);
        btnSearch = (Button)rootView.findViewById(R.id.btn_posting_search);
        btnSearch.setOnClickListener(new View.OnClickListener() { // 게시물 검색
            @Override
            public void onClick(View v) {

                final ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                    @Override
                    public String handleResponse(HttpResponse response) throws IOException {

                        int eventType;
                        int talkpost_no = -1;
                        String talkpost_type = TalkPostingData.getTypeToString(type);
                        String talkpost_date = "";
                        String talkpost_title = searchTitle.getText().toString();
                        String mem_id = "";
                        HttpEntity entity = response.getEntity();

                        try {
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = factory.newPullParser();
                            InputStreamReader isreader = new InputStreamReader(entity.getContent(), "UTF-8");
                            parser.setInput(isreader);
                            if (arrData == null) {
                                arrData = new ArrayList<TalkPostListData>();
                            }
                            else {
                                arrData.clear();
                            }

                            eventType = parser.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                String tagName;
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        tagName = parser.getName();
                                        if (tagName != null && tagName.equals("talkpost_no")) {
                                            talkpost_no = Integer.parseInt(parser.nextText());
                                        } else if (tagName != null && tagName.equals("talkpost_type")) {
                                            talkpost_type = parser.nextText();
                                        } else if (tagName != null && tagName.equals("talkpost_date")) {
                                            talkpost_date = parser.nextText();
                                        } else if (tagName != null && tagName.equals("talkpost_title")) {
                                            talkpost_title = parser.nextText();
                                        } else if (tagName != null && tagName.equals("mem_id")) {
                                            mem_id = parser.nextText();
                                        } else break;
                                    case XmlPullParser.END_TAG:
                                        tagName = parser.getName();
                                        if (tagName != null && tagName.equals("postlist")) {
                                            arrData.add(new TalkPostListData(talkpost_no, talkpost_type, talkpost_date, talkpost_title, mem_id));
                                        }
                                        break;
                                }
                                eventType = parser.next();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        FragmentC_1.this.getActivity().runOnUiThread(new Runnable() {
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
                        String url = "http://52.78.101.183:8080/tauction/talkpost_list.jsp";
                        getDataFromServer(responseHandler, url, "getTalkPostList", true, -1); // 제목으로 검색
                    }
                }.start(); //스레드 실행
            }
        });

        //어댑터 연결
        attachAdapter();

        //리스트뷰에 클릭 리스터 연결
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 게시물 보기
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos = arrData.get(position).getPosting_no();
                final ResponseHandler<String> responseHandler =  new ResponseHandler<String>(){
                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

                        Intent intent = new Intent(getActivity(), PostingContentActivity.class);

                        TalkPostingData posting = null;
                        int eventType;
                        int talkpost_no = pos;
                        String talkpost_type = "";
                        String talkpost_date = "";
                        String talkpost_title = "";
                        String talkpost_content = "";
                        int mem_no = -1;
                        String mem_id = "";
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
                                        if(tagName != null && tagName.equals("talkpost_no")) {
                                            talkpost_no = Integer.parseInt(parser.nextText());
                                        } else if(tagName != null && tagName.equals("talkpost_type")) {
                                            talkpost_type = parser.nextText();
                                        } else if(tagName != null && tagName.equals("talkpost_date")) {
                                            talkpost_date = parser.nextText();
                                        } else if(tagName != null && tagName.equals("talkpost_title")) {
                                            talkpost_title = parser.nextText();
                                        } else if(tagName != null && tagName.equals("talkpost_content")) {
                                            talkpost_content = parser.nextText();
                                        } else if(tagName != null && tagName.equals("mem_no")) {
                                            mem_no = Integer.parseInt(parser.nextText());
                                        } else if(tagName != null && tagName.equals("mem_id")) {
                                            mem_id = parser.nextText();
                                        }
                                        break;
                                    case XmlPullParser.END_TAG:
                                        tagName = parser.getName();
                                        if(tagName != null && tagName.equals("posting")) {
                                            posting = new TalkPostingData(talkpost_no, talkpost_type, talkpost_date, talkpost_title, talkpost_content, mem_no, mem_id);
                                        }
                                        break;
                                }
                                eventType = parser.next();
                            }
                        }catch(Exception e){e.printStackTrace();}

                        //게시글을 보여줄 액티비티에 데이터 전달
                        intent.putExtra("number",posting.getPosting_no());
                        intent.putExtra("type",posting.getPosting_type());
                        intent.putExtra("date",posting.getPosting_date());
                        intent.putExtra("title",posting.getPosting_title());
                        intent.putExtra("content",posting.getPosting_content());
                        intent.putExtra("mem_number",posting.getMem_no());
                        intent.putExtra("id",posting.getMem_id());
                        startActivity(intent);

                        return "success";
                    }
                };

                new Thread(){
                    @Override
                    public void run(){
                        String url = "http://52.78.101.183:8080/tauction/talkposting.jsp";
                        getDataFromServer(responseHandler, url,"getTalkPosting", false, pos); // 위치로 검색
                    }
                }.start(); //스레드 실행
            }
        });

        return rootView;
    }
    private void setDataByType() {

        final ResponseHandler<String> responseHandler =  new ResponseHandler<String>(){
            @Override
            public String handleResponse(HttpResponse response) throws IOException {

                int eventType;
                int talkpost_no = -1;
                String talkpost_type = TalkPostingData.getTypeToString(type);
                String talkpost_date = "";
                String talkpost_title = "";
                String mem_id = "";
                HttpEntity entity = response.getEntity();

                try{
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    InputStreamReader isreader = new InputStreamReader(entity.getContent(),"UTF-8");
                    parser.setInput(isreader);
                    if(arrData == null) {
                        arrData = new ArrayList<TalkPostListData>();
                    } else {
                        arrData.clear();
                    }
                    eventType = parser.getEventType();
                        while (eventType != XmlPullParser.END_DOCUMENT) {
                            String tagName;
                            switch(eventType) {
                                case XmlPullParser.START_TAG:
                                    tagName = parser.getName();
                                    if(tagName != null) {
                                        switch(tagName) {
                                            case "talkpost_no":
                                                talkpost_no = Integer.parseInt(parser.nextText());
                                                break;
                                            case "talkpost_type":
                                                talkpost_type = parser.nextText();
                                                break;
                                            case "talkpost_date":
                                                talkpost_date = parser.nextText();
                                                break;
                                            case "talkpost_title":
                                                talkpost_title = parser.nextText();
                                                break;
                                            case "mem_id":
                                                mem_id = parser.nextText();
                                                break;
                                        }
                                    }
                                    break;
                                case XmlPullParser.END_TAG:
                                    tagName = parser.getName();
                                    if(tagName != null && tagName.equals("postlist")) {
                                        arrData.add(new TalkPostListData(talkpost_no, talkpost_type, talkpost_date, talkpost_title, mem_id));
                                    }
                                    break;
                            }
                            eventType = parser.next();
                        }
                }catch(Exception e){e.printStackTrace();}

                FragmentC_1.this.getActivity().runOnUiThread(new Runnable() {
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
                String url = "http://52.78.101.183:8080/tauction/talkpost_list.jsp";
                getDataFromServer(responseHandler, url,"getTalkPostList", false, -1); // 전체 검색
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
            if(action.equals("getTalkPostList")) {
                nameValuePairs.add(new BasicNameValuePair("type", TalkPostingData.getTypeToString(type)));
                if (flag) {
                    nameValuePairs.add(new BasicNameValuePair("title", searchTitle.getText().toString()));
                }
            } else {
                if(position > -1) {
                    nameValuePairs.add(new BasicNameValuePair("talkpost_no", "" + position));
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
            arrData = new ArrayList<TalkPostListData>();
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            adapter = new TalkPostListAdapter(rootView.getContext(), arrData);

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