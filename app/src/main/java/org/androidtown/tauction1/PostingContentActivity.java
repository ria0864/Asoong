package org.androidtown.tauction1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class PostingContentActivity extends AppCompatActivity {

    ImageButton btn_back;

    ListView list;
    CommentAdapter adapter;
    ArrayList<CommentData> arrData;

    ScrollView mScrollView;

    TextView textTitle;
    TextView textId;
    TextView textDate;
    TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_content);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        textTitle = (TextView)findViewById(R.id.title1);
        textTitle.setText(intent.getExtras().getString("title"));

        textId = (TextView)findViewById(R.id.mem_id);
        textId.setText(intent.getExtras().getString("id"));

        textDate = (TextView)findViewById(R.id.date);
        textDate.setText(intent.getExtras().getString("date"));

        textContent = (TextView)findViewById(R.id.content);
        textContent.setText(intent.getExtras().getString("content"));
    }
}