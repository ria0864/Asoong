package org.androidtown.tauction1;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

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

        //setScroll(list);

        setListViewHeightBasedOnChildren(list);
        mScrollView=(ScrollView)findViewById(R.id.ScrollView);
        ImageView mImage = (ImageView) findViewById(R.id.acco_image);
        mScrollView.requestChildFocus(null,mImage);
        mScrollView.scrollTo(0,0);
        mScrollView.invalidate();

    }
    private void setData(){//레이아웃 크기에 맞게 글자수 제한할 것
        arrData = new ArrayList<MyDataReview>();
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));
        arrData.add(new MyDataReview("헬륨이", "2016.5.6", " 3박4일 추천제주도 여행 계획중이신 분들에게 추천해드려요! 떠나요~둘이서 모든 것 훌훌버리고제주도 푸른밤 그별아래"));


    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight - (listView.getDividerHeight() * (listAdapter.getCount() - 1)*30);
        Log.d("MyLog",""+ totalHeight);
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /*
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
    */

}