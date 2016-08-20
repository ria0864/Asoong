package org.androidtown.tauction1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class HostingRegistrationActivity extends AppCompatActivity  {

    TextView tv;
    int choice = 0;
    final int DIALOG_MULTICHOICE = 4;
    ImageButton btn_back;
    TextView mTextView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting_registration);
        String[] optionLavala = getResources().getStringArray(R.array.spinnerRegion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,optionLavala);
        Spinner spi = (Spinner)findViewById(R.id.spinnerr);
        spi.setAdapter(adapter);

        btn_back=(ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setSpinner(R.id.spinnerh,R.array.spinnerHotel,android.R.layout.simple_spinner_dropdown_item);
        getSpinner(R.id.spinnerr).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?>parentView, View selectedView, int position, long id){
                printChecked(selectedView,position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );
        getSpinner(R.id.spinnerh).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?>parentView, View selectedView, int position, long id){
                printChecked(selectedView,position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        SeekBar seek_bar =(SeekBar)findViewById(R.id.seekbarh);
        mTextView = (TextView)findViewById(R.id.text_valueh);
        seek_bar.setProgressDrawable(getResources()
                .getDrawable(R.drawable.progress));
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

                // To Do
                mTextView.setText(progress + "만원");

            }



            public void onStartTrackingTouch(SeekBar seekBar){}   // tracking 시작

            public void onStopTrackingTouch(SeekBar seekBar){}   // tracking 끝

        });


        tv=(TextView)findViewById(R.id.serveplace);
        findViewById(R.id.myplace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice=0;
                showDialog(DIALOG_MULTICHOICE);

            }
        });



    }
    public void setSpinner(int objId, int objLabelId, int lifestyle) {
        setSpinner(objId, objLabelId, -1, lifestyle, null);
    }
    public void setSpinner(int objId, int optionLabelId, int optionId, int lifestyle, String defultVal) {
        String[] optionLavala = getResources().getStringArray(optionLabelId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,optionLavala);

        if(lifestyle>-1){
            adapter.setDropDownViewResource(lifestyle);
            Spinner spi =(Spinner)findViewById(objId);
            spi.setAdapter(adapter);
            if(defultVal != null){
                String[] optiona = getResources().getStringArray(optionId);
                int thei = 0;
                for (int a = 0; a < optiona.length; a++) {
                    if (defultVal.equals(optiona[a])) {
                        thei = a;
                        break;
                    }
                }
                spi.setSelection(adapter.getPosition(optionLavala[thei]));
            }else{
                spi.setSelection(adapter.getPosition(defultVal));
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
        Spinner sp1=(Spinner)findViewById(R.id.spinnerr);
        Spinner sp2=(Spinner)findViewById(R.id.spinnerh);
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
                        new AlertDialog.Builder(HostingRegistrationActivity.this);
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

    }
}