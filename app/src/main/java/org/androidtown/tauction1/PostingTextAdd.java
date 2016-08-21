package org.androidtown.tauction1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * Created by Eun on 2016-08-19.
 */
public class PostingTextAdd extends AppCompatActivity {

    ImageButton goFragment5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_text);

        goFragment5 = (ImageButton)findViewById(R.id.goFragment5);
        goFragment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Spinner postSpinner = (Spinner)findViewById(R.id.spinneraddpost);
        ArrayAdapter postAdapter = ArrayAdapter.createFromResource(this, R.array.spinnerPost, android.R.layout.simple_spinner_item);
        postAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postSpinner.setAdapter(postAdapter);

    }
}
