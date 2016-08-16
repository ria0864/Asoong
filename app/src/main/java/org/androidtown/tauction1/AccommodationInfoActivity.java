package org.androidtown.tauction1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class AccommodationInfoActivity extends AppCompatActivity {

    ImageButton btn_back;

    ListView list;
    MyAdapterReview adapter;
    ArrayList<MyDataReview> arrData;

    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_info);

        btn_back=(ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //리스트에 보여줄 데이터를 세팅한다.
        setData();

        //어댑터 생성
        adapter = new MyAdapterReview(this, arrData);

        //리스트뷰에 어댑터 연결
        list = (ListView)findViewById(R.id.list_review);
        list.setAdapter(adapter);

        mScrollView=(ScrollView)findViewById(R.id.ScrollView);
        setScroll(list);

    }
    private void setData(){
        arrData = new ArrayList<MyDataReview>();
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래 ♪♬저는 이번 여름휴가때 3박4일로 제주도 여행을 다녀왔어요검색을 통해 알게된 토스카나 호텔!!어디서 많이 들어본 호텔인데~라고 생각하다가 아시는 분들은 아시겠지만시아준수호텔로 유명하죠^^?제주항공 비행기를 타고 아침일찍 출발!! 비행기사진은 언제봐도 설레이는 것 같아요..♥ 공항에 도착해서 렌트카를 빌리고."));
        arrData.add(new MyDataReview("쥬얼리", "010-3333-4444", "juerly@daum.net"));
        arrData.add(new MyDataReview("시크릿", "010-5555-6666", "secret@paran.com"));
    }
    private void setScroll(ListView mListView){
        mListView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

}