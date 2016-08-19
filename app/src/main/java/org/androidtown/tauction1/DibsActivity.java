package org.androidtown.tauction1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class DibsActivity extends AppCompatActivity {

    ImageButton goFragment;

    ListView list;
    DibsAdapter adapter;
    ArrayList<DibsData> arrData;

    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibs);

        goFragment=(ImageButton)findViewById(R.id.goFragment3);
        goFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //리스트에 보여줄 데이터를 세팅한다.
        setData();

        //어댑터 생성
        adapter = new DibsAdapter(this, arrData);

        //리스트뷰에 어댑터 연결
        list = (ListView)findViewById(R.id.list_dibs);
        list.setAdapter(adapter);

        //mScrollView=(ScrollView)findViewById(R.id.ScrollView);
        //setScroll(list);

    }
    private void setData(){
        arrData = new ArrayList<DibsData>();
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
        arrData.add(new DibsData("서진", "2016.5.6","전주 싸고 좋은 곳 추천해주세요^.^", "안녕하세요~ 부모님 결혼기념일을 맞아 부모님 모시고 제주도로 여행 다녀오려고 합니다 ^^.부모님이 불편하시지 않도록 시설이 갖춰진 곳으로 추천 부탁드립니다.","+1"));
    }


}