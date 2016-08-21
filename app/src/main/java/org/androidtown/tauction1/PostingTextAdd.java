package org.androidtown.tauction1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Eun on 2016-08-19.
 */
public class PostingTextAdd extends AppCompatActivity {

    EditText editText;
    EditText main;
    Spinner postSpinner;

    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_text);

        postSpinner = (Spinner)findViewById(R.id.spinneraddpost);
        ArrayAdapter postAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerPost, android.R.layout.simple_spinner_item);
        postAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postSpinner.setAdapter(postAdapter);

        editText = (EditText)findViewById(R.id.editText);
        main = (EditText)findViewById(R.id.main);

        ImageButton btnBack = (ImageButton)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnOK = (Button)findViewById(R.id.ok_button);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        String result;
                        HttpEntity entity = response.getEntity();
                        result = parsingData(entity.getContent());
                        //result = "success";
                        Message message = handler.obtainMessage();
                        Bundle bundle = new Bundle();

                        if (result.equals("success")) {
                            result = "success";
                        } else if(result.equals("no_login")) {
                            result = "no_login";
                        } else if(result.equals("no_posting")) {
                            result = "no_posting";
                        } else {
                            result = "fail";
                        }
                        bundle.putString("RESULT", result);
                        message.setData(bundle);
                        handler.sendMessage(message);

                        return result;
                    }
                };

                pDialog = ProgressDialog.show(PostingTextAdd.this, "", "데이타 전송중..");

                new Thread() {
                    @Override
                    public void run() {
                        String title = editText.getText().toString();
                        String content = main.getText().toString();
                        Calendar calendar = Calendar.getInstance();

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String date = format.format(calendar.getTime());
                        int type = postSpinner.getSelectedItemPosition()+1;
                        String strType = TalkPostingData.getTypeToString(type);
                        String mem_id;
                        String url = "http://52.78.101.183:8080/tauction/add_talkpost.jsp";

                        Intent intent = PostingTextAdd.this.getIntent();
                        if(intent.hasExtra("mem_id")) {
                            mem_id = intent.getExtras().getString("mem_id");
                        } else {
                            mem_id = "Anonymous";
                        }
                        HttpClient client = new DefaultHttpClient();
                        try {
                            ArrayList<NameValuePair> nameValuePairs =
                                    new ArrayList<NameValuePair>();

                            nameValuePairs.add(new BasicNameValuePair("action", "addTalkPosting"));
                            nameValuePairs.add(new BasicNameValuePair("type", strType));
                            nameValuePairs.add(new BasicNameValuePair("date", date));
                            nameValuePairs.add(new BasicNameValuePair("title", title));
                            nameValuePairs.add(new BasicNameValuePair("content", content));
                            nameValuePairs.add(new BasicNameValuePair("mem_id", mem_id));

                            //타임아웃
                            HttpParams params = client.getParams();
                            HttpConnectionParams.setConnectionTimeout(params, 2000);
                            HttpConnectionParams.setSoTimeout(params, 2000);

                            HttpPost httpPost = new HttpPost(url);
                            UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                            httpPost.setEntity(entityRequest);
                            client.execute(httpPost, responseHandler);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    private final Handler handler = new Handler(){
        public void handleMessage(Message msg){

            String result = msg.getData().getString("RESULT");
            //Intent j = new Intent(SignupActivity.this,LoginActivity.class);
            //j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //j.putExtra("mem_id",editTextID.getText().toString());
            if(result.equals("success")){
                Toast.makeText(PostingTextAdd.this, "성공", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
                finish();
            }   else if(result.equals("no_login")) {
                Toast.makeText(PostingTextAdd.this, "로그인이 필요합니다.", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
                finish();
            }   else{
                Toast.makeText(PostingTextAdd.this, "실패", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        }
    };

    public String parsingData(InputStream input){
        String result = null;
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            InputStreamReader isreader = new InputStreamReader(input,"EUC-KR");
            parser.setInput(isreader);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName;
                String name = parser.getName();
                System.out.println(name);
                switch(eventType) {
                    case XmlPullParser.START_TAG:
                        if (name != null && name.equals("result")) {//<result> </result> 태그
                            result = parser.nextText();
                            System.out.println("result tag");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = parser.next();
            }
        }catch(Exception e){e.printStackTrace();}
        System.out.println(result);
        return result;
    }
}
