package org.androidtown.tauction1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Calendar;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class FilterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    final int DIALOG_MULTICHOICE = 4;
    TextView tv;
    int choice = 0;
    DatePickerDialog datePickerDialog, datePickerDialog1 ;
    Button btnDatePicker, btnDatePicker1;
    private TextView mDateDisplay, mDateDisplay1;
    private int mYear, mYear1;
    private int mMonth, mMonth1;
    private int mDay, mDay1;
    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID1 = 0;
    Button plus_btn, minus_btn;  //인원수 설정 버튼
    TextView people;
    int count = 1;//사람수
    TextView sTextView;

    ImageButton btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        String[] optionLavala = getResources().getStringArray(R.array.spinnerRegion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,optionLavala);
        Spinner obj = (Spinner)findViewById(R.id.spinner);
        obj.setAdapter(adapter);

        btn_back=(ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setSpinner(R.id.spinnerhotel,R.array.spinnerHotel,android.R.layout.simple_spinner_dropdown_item);
        getSpinner(R.id.spinner).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?>parentView, View selectedView, int position, long id){
                printChecked(selectedView,position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
        getSpinner(R.id.spinnerhotel).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?>parentView, View selectedView, int position, long id){
                printChecked(selectedView,position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );


        tv=(TextView)findViewById(R.id.goodPlace);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice=0;
                showDialog(DIALOG_MULTICHOICE);

            }
        });


        btnDatePicker = (Button) findViewById(R.id.btn_datepicker);
        btnDatePicker.setOnClickListener(new MyClick());
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        // display the current date (this method is below)
        updateDisplay();

        btnDatePicker1 = (Button) findViewById(R.id.btn_datepicker1);
        btnDatePicker1.setOnClickListener(new MyClick());
        mDateDisplay = (TextView) findViewById(R.id.dateDisplay1);
        final Calendar c1 = Calendar.getInstance();
        mYear1 = c1.get(Calendar.YEAR);
        mMonth1 = c1.get(Calendar.MONTH);
        mDay1 = c1.get(Calendar.DAY_OF_MONTH);
        // display the current date (this method is below)
        updateDisplay1();

        minus_btn = (Button)findViewById(R.id.minus);
        plus_btn =(Button)findViewById(R.id.plus);
        people=(TextView)findViewById(R.id.peopleCount);

        minus_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (count>1){
                    count--;
                    people.setText(String.valueOf(count));

                }




            }
        });
        plus_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                people.setText(String.valueOf(count));
                return;
            }

        });


        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiosex);
        RadioGroup radioGroup1 = (RadioGroup)findViewById(R.id.radiotype);

        radioGroup.setOnCheckedChangeListener(this);
        radioGroup1.setOnCheckedChangeListener(this);

        radioGroup.check(R.id.female);
        radioGroup1.check(R.id.friend);

        SeekBar seek_bar =(SeekBar)findViewById(R.id.seekBar);
        sTextView = (TextView)findViewById(R.id.text_value);

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                // To Do
                sTextView.setText(progress + "만원");

            }



            public void onStartTrackingTouch(SeekBar seekBar){}   // tracking 시작

            public void onStopTrackingTouch(SeekBar seekBar){}   // tracking 끝

        });




    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }


    /*달력*/
    private class MyClick implements View.OnClickListener{
        @Override

        public void onClick(final View v) {

            // TODO Auto-generated method stub

            switch (v.getId()) {
                case R.id.btn_datepicker:
                    DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                            // TODO Auto-generated method stub
                            Toast.makeText(v.getContext(),
                                    year + ":" + (monthOfYear + 1) + ":" + dayOfMonth,Toast.LENGTH_SHORT).show();
                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;
                            updateDisplay();

                        }
                    };
                    datePickerDialog = new DatePickerDialog(v.getContext(), callBack, 2016, 9, 01);
                    datePickerDialog.show();
                    break;
                case R.id.btn_datepicker1:
                    DatePickerDialog.OnDateSetListener callBack2 = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            // TODO Auto-generated method stub
                            Toast.makeText(v.getContext(),
                                    year + ":" + (monthOfYear + 1) + ":" + dayOfMonth,Toast.LENGTH_SHORT).show();
                            mYear1 = year;
                            mMonth1 = monthOfYear;
                            mDay1 = dayOfMonth;
                            updateDisplay1();
                        }
                    };
                    datePickerDialog1 = new DatePickerDialog(v.getContext(), callBack2,2016, 9, 01);
                    datePickerDialog1.show();
                    break;
            }
        }}
    private void updateDisplay() {
        mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mYear).append("-")
                        .append(mMonth).append("-")
                        .append(mDay).append(" "));

    }
    private void updateDisplay1() {
        mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mYear).append("-")
                        .append(mMonth).append("-")
                        .append(mDay).append(" "));

    }







/*스피너*/

    public void setSpinner(int objId, int objLabelId, int lifestyle) {
        setSpinner(objId, objLabelId, -1, lifestyle, null);
    }
    public void setSpinner(int objId, int optionLabelId, int optionId, int lifestyle, String defultVal) {
        String[] optionLavala = getResources().getStringArray(optionLabelId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,optionLavala);

        if(lifestyle>-1){
            adapter.setDropDownViewResource(lifestyle);
            Spinner obj =(Spinner)findViewById(objId);
            obj.setAdapter(adapter);
            if(defultVal != null){
                String[] optiona = getResources().getStringArray(optionId);
                int thei = 0;
                for (int a = 0; a < optiona.length; a++) {
                    if (defultVal.equals(optiona[a])) {
                        thei = a;
                        break;
                    }
                }
                obj.setSelection(adapter.getPosition(optionLavala[thei]));
            }else{
                obj.setSelection(adapter.getPosition(defultVal));
            }
        }
    }
    public Spinner getSpinner(int objId){
        return (Spinner)findViewById(objId);

    }
    public String getSpinnerVal(int objId){
        return getSpinnerVal(objId, null);
    }
    private String getSpinnerVal(int objId, String[] optiona)
    {
        String rtn="";
        Spinner sp=((Spinner)findViewById(objId));
        if(sp !=null){
            int selectedIndex = sp.getSelectedItemPosition();
            if(optiona==null){
                rtn=""+selectedIndex;
            }else {
                if(optiona.length>selectedIndex){
                    rtn=optiona[selectedIndex];
                }
            }
        }
        return rtn;
    }

    public void printChecked(View v, int position) {
        Spinner sp1=(Spinner)findViewById(R.id.spinner);
        Spinner sp2=(Spinner)findViewById(R.id.spinnerhotel);
        String resultText="";
        if (sp1.getSelectedItemPosition()>0){
            resultText=(String)sp1.getAdapter().getItem(sp1.getSelectedItemPosition());
        }
        if (sp2.getSelectedItemPosition()>0){
            if(!"".equals(resultText))
                resultText+=",";
            resultText=(String)sp2.getAdapter().getItem(sp2.getSelectedItemPosition());
        }

    }



    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_MULTICHOICE:

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(FilterActivity.this);
                final String data[] = {"바베큐", "수영장", "와이파이", "복층"};
                final boolean checked[] = {false, false, false, false};
                builder.setTitle("MultiChoice 다이얼로그")
                        .setPositiveButton("선택완료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = ">>";
                                for (int i = 0; i < checked.length; i++) {
                                    if (checked[i]) {
                                        str = str + data[i] + ",";

                                    }
                                }
                                tv.setText(str);
                            }
                        }).setNegativeButton("취소", null)
                        .setMultiChoiceItems(data, checked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checked[which] = isChecked;
                            }
                        });
                return builder.create();

        }



        return super.onCreateDialog(id);

    }}
