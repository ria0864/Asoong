package org.androidtown.tauction1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class LoginActivity extends AppCompatActivity {

    EditText editTextID, editTextPW;
    Button buttonCancel;
    Button buttonLogin;

    String stringID,stringPW;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button)findViewById(R.id.loginButton);
        buttonCancel = (Button)findViewById(R.id.cancelButton);
        editTextID = (EditText)findViewById(R.id.idInput);
        editTextPW = (EditText)findViewById(R.id.passwordInput);
        stringID="";stringPW="";

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                goLogin();
            }
        });

    }

    public void goLogin(){
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

        pDialog = ProgressDialog.show(this, "", "데이타 전송중..");

        new Thread(){
            @Override
            public void run(){
                String url = "http://203.253.23.15:90/Tauction/recieve.jsp";
                HttpClient client = new DefaultHttpClient();
                try{
                    ArrayList<NameValuePair> nameValuePairs =
                            new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("action","login"));
                    nameValuePairs.add(new BasicNameValuePair("ID",editTextID.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("PW",editTextPW.getText().toString()));
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

    private final Handler handler = new Handler(){
        public void handleMessage(Message msg){

            String result = msg.getData().getString("RESULT");
            Intent j = new Intent(LoginActivity.this,MainActivity.class);
            j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            j.putExtra("mem_id",editTextID.getText().toString());
            if(result.equals("success")){
                Toast.makeText(LoginActivity.this, "성공", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
                startActivity(j);
                finish();
            }else{
                Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_LONG).show();
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
        }catch(Exception e){e.printStackTrace();}
        System.out.println(result);
        return result;
    }
}


