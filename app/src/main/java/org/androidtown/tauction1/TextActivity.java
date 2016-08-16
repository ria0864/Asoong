package org.androidtown.tauction1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import android.view.View.OnClickListener;
/**
 * Created by Eun on 2016-08-16.
 */
public class TextActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
        SpinnerList list;
        SpinnerList list1;
        ArrayAdapter adapter;
        ArrayAdapter adapter1;
        final int DIALOG_MULTICHOICE = 4;
        TextView tv;
@Override
public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        Spinner spinner =(Spinner)findViewById(R.id.spinner);
        Spinner spinner1 =(Spinner)findViewById(R.id.spinnerhotel);
        list = new SpinnerList();
        list1 = new SpinnerList();

        adapter=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,list.getArrayList());
        adapter1=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,list1.getArrayList1());
        spinner.setAdapter(adapter);




        tv=(TextView)findViewById(R.id.goodPlace);
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                        showDialog(DIALOG_MULTICHOICE);

                }
        });

        }


@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Selected"+String.valueOf(position), Toast.LENGTH_SHORT).show();

        }

@Override
public void onNothingSelected(AdapterView<?> arg0) {

        }



        @Override
        @Deprecated
        protected Dialog onCreateDialog(int id){
                switch (id){
                        case DIALOG_MULTICHOICE :
                                AlertDialog.Builder builder=
                                        new AlertDialog.Builder(TextActivity.this);
                                final String data [] = {"바베큐", "수영장", "와이파이", "복층"};
                                final boolean checked [] = {true, false, true, false };
                                builder.setTitle("MultiChoice 다이얼로그")
                                        .setPositiveButton("선택완료", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                        String str ="선택된값은";
                                                                for(int i = 0; i<checked.length; i++){
                                                                        if(checked[i]){
                                                                                str=str+data[i]+",";
                                                                        }
                                                                }
                                                        tv.setText(str);
                                                }
                                        }).setNegativeButton("취소",null)
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
