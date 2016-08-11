package org.androidtown.tauction1;

import android.app.ProgressDialog;
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
public class SignupActivity extends AppCompatActivity {

    EditText editTextID, editTextPW, editTextPW2;
    Button buttonCancel;
    Button buttonSignup;

    String stringID,stringPW,stringPW2;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        buttonSignup = (Button)findViewById(R.id.SignupButton);
        buttonCancel = (Button)findViewById(R.id.signup_cancelButton);
        editTextID = (EditText)findViewById(R.id.signup_idInput);
        editTextPW = (EditText)findViewById(R.id.signup_passwordInput);
        editTextPW2 = (EditText)findViewById(R.id.signup_passwordInput_check);

        stringID="";stringPW="";

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                goSignup();
            }
        });
    }

    public void goSignup(){

        stringPW = editTextPW.getText().toString();
        stringPW2 = editTextPW2.getText().toString();
        if(!stringPW.equals(stringPW2)){
            Toast.makeText(SignupActivity.this, "비밀번호를 재확인해주세요.", Toast.LENGTH_LONG).show();
            return;
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

                if(result.equals("success"))
                    result="success";
                else if(result.equals("fail_duplicate"))
                    result="fail_duplicate";
                else
                    result="fail";

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
                    nameValuePairs.add(new BasicNameValuePair("action","signup"));
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
            Intent j = new Intent(SignupActivity.this,LoginActivity.class);
            j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            j.putExtra("mem_id",editTextID.getText().toString());
            if(result.equals("success")){
                Toast.makeText(SignupActivity.this, "성공", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
                startActivity(j);
                finish();
            }else if(result.equals("fail_duplicate")){
                Toast.makeText(SignupActivity.this, "동일한 아이디가 존재합니다.", Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }else{
                Toast.makeText(SignupActivity.this, "실패", Toast.LENGTH_LONG).show();
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


