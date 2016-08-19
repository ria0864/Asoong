package org.androidtown.tauction1;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by 14Z950 on 2016-07-28.
 */
public class FavoritesActivity extends AppCompatActivity {

    ImageButton btn_back;

    ListView list;
    FavoritesAdapter adapter;
    ArrayList<FavoritesData> arrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        btn_back=(ImageButton)findViewById(R.id.goFragment4);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //리스트에 보여줄 데이터를 세팅한다.
        setData();

        //어댑터 생성
        adapter = new FavoritesAdapter(this, arrData);

        //리스트뷰에 어댑터 연결
        list = (ListView)findViewById(R.id.list_favorites);

        list.setAdapter(adapter);

    }
    private void setData(){//레이아웃 크기에 맞게 글자수 제한할 것
        arrData = new ArrayList<FavoritesData>();
        //여기에서 리스트뷰 아이템 추가 코드 int image, String rank, String like_num, String name, String address
        arrData.add(new FavoritesData(R.drawable.toscana,"259","토스카나 호텔","제주특별자치도 서귀포시 용흥로 66번길 158-7"));
        arrData.add(new FavoritesData(R.drawable.maru_pension,"190","별빛마루 펜션","경기도 가평군 북면 적목리"));
        arrData.add(new FavoritesData(R.drawable.sheraton_hotel, "177","쉐라톤 그랜드 워커힐","서울특별시 광진구 워커힐로 177 쉐라톤워커힐호텔"));
        arrData.add(new FavoritesData(R.drawable.toscana,"259","토스카나 호텔","제주특별자치도 서귀포시 용흥로 66번길 158-7"));
        arrData.add(new FavoritesData(R.drawable.maru_pension,"190","별빛마루 펜션","경기도 가평군 북면 적목리"));
        arrData.add(new FavoritesData(R.drawable.sheraton_hotel, "177","쉐라톤 그랜드 워커힐","서울특별시 광진구 워커힐로 177 쉐라톤워커힐호텔"));
        arrData.add(new FavoritesData(R.drawable.toscana,"259","토스카나 호텔","제주특별자치도 서귀포시 용흥로 66번길 158-7"));
        arrData.add(new FavoritesData(R.drawable.maru_pension,"190","별빛마루 펜션","경기도 가평군 북면 적목리"));
        arrData.add(new FavoritesData(R.drawable.sheraton_hotel, "177","쉐라톤 그랜드 워커힐","서울특별시 광진구 워커힐로 177 쉐라톤워커힐호텔"));
    }
}