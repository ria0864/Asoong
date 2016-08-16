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
import android.widget.AdapterView.OnItemSelectedListener;

import org.w3c.dom.Text;
import android.view.View.OnClickListener;
/**
 * Created by Eun on 2016-08-16.
 */
public class TextActivity extends AppCompatActivity{

        final int DIALOG_MULTICHOICE = 4;
        TextView tv;
@Override
public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        String[] optionLavala = getResources().getStringArray(R.array.spinnerRegion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,optionLavala);
        Spinner obj = (Spinner)findViewById(R.id.spinner);
        obj.setAdapter(adapter);

        setSpinner(R.id.spinnerhotel,R.array.spinnerHotel,android.R.layout.simple_spinner_dropdown_item);
        getSpinner(R.id.spinner).setOnItemSelectedListener(new OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?>parentView, View selectedView, int position, long id){
                        printChecked(selectedView,position);
                }

          @Override
           public void onNothingSelected(AdapterView<?> parent) {

                                                                   }
                                                           } );
        getSpinner(R.id.spinnerhotel).setOnItemSelectedListener(new OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?>parentView, View selectedView, int position, long id){
                        printChecked(selectedView,position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
        } );

        tv=(TextView)findViewById(R.id.goodPlace);
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
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
