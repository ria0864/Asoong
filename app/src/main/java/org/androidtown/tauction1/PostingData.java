package org.androidtown.tauction1;

import android.content.Intent;
import android.os.Message;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Handler;

/**
 * Created by SSU on 2016-08-16.
 */

public class PostingData {
    private int pos_no;
    private Date pos_date;
    private String pos_title;
    private String pos_contents;
    private int reg_no;
    private int pos_num;
    private String pos_type;
    private String pos_gender;
    private String pos_trip;
    private int pos_budget;
    private String pos_convin;
    private Date pos_startday;
    private Date pos_endday;
    private String pos_pay;
    private int mem_no;


    public PostingData(int pos_no, Date pos_date, String pos_title, String pos_contents, int reg_no, int pos_num, String pos_type, String pos_gender, String pos_trip,int pos_budget,String pos_convin,Date pos_startday,Date pos_endday,String pos_pay,int mem_no){
        this.pos_no = pos_no;
        this.pos_date = pos_date;
        this.pos_title = pos_title;
        this.pos_contents = pos_contents;
        this.reg_no = reg_no;
        this.pos_num = pos_num;
        this.pos_type = pos_type;
        this.pos_gender = pos_gender;
        this.pos_trip = pos_trip;
        this.pos_budget = pos_budget;
        this.pos_convin = pos_convin;
        this.pos_startday = pos_startday;
        this.pos_endday = pos_endday;
        this.pos_pay = pos_pay;
        this.mem_no = mem_no;
    }

    public void setPos_no(int pos_no) {
        this.pos_no = pos_no;
    }

    public void setPos_date(Date pos_date) {
        this.pos_date = pos_date;
    }

    public void setPos_contents(String pos_contents) {
        this.pos_contents = pos_contents;
    }

    public void setReg_no(int reg_no) {
        this.reg_no = reg_no;
    }

    public void setPos_num(int pos_num) {
        this.pos_num = pos_num;
    }

    public void setPos_type(String pos_type) {
        this.pos_type = pos_type;
    }

    public void setPos_gender(String pos_gender) {
        this.pos_gender = pos_gender;
    }

    public void setPos_trip(String pos_trip) {
        this.pos_trip = pos_trip;
    }

    public void setPos_budget(int pos_budget) {
        this.pos_budget = pos_budget;
    }

    public void setPos_convin(String pos_convin) {
        this.pos_convin = pos_convin;
    }

    public void setPos_startday(Date pos_startday) {
        this.pos_startday = pos_startday;
    }

    public void setPos_endday(Date pos_endday) {
        this.pos_endday = pos_endday;
    }

    public void setPos_pay(String pos_pay) {
        this.pos_pay = pos_pay;
    }

    public void setMem_no(int mem_no) {
        this.mem_no = mem_no;
    }

    public String getPos_title() {

        return pos_title;
    }

    public String getPos_contents() {
        return pos_contents;
    }

    public int getReg_no() {
        return reg_no;
    }

    public int getPos_num() {
        return pos_num;
    }

    public String getPos_type() {
        return pos_type;
    }

    public String getPos_gender() {
        return pos_gender;
    }

    public String getPos_trip() {
        return pos_trip;
    }

    public int getPos_budget() {
        return pos_budget;
    }

    public String getPos_convin() {
        return pos_convin;
    }

    public Date getPos_startday() {
        return pos_startday;
    }

    public Date getPos_endday() {
        return pos_endday;
    }

    public String getPos_pay() {
        return pos_pay;
    }

    public int getMem_no() {
        return mem_no;
    }

    public Date getPos_date() {
        return pos_date;

    }

    public int getPos_no() {
        return pos_no;
    }

    public void setPos_title(String pos_title) {
        this.pos_title = pos_title;
    }
/*
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
*/
    public ArrayList<PostingData> parse(InputStream input){
        String result = null;
        ArrayList<PostingData> postingDatas = new ArrayList<PostingData>();
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new InputStreamReader(input));
            while(parser.next() != XmlPullParser.END_DOCUMENT){
                /*String name = parser.getName();
                if(name != null && name.equals("result")) //<result> </result> 태그
                    result = parser.nextText();*/  //여기 수정하기~~ 아마도?
            }
        }catch(Exception e){e.printStackTrace();}
        System.out.println(result);
        return postingDatas;
    }
}
