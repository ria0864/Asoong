package org.androidtown.tauction1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
/* hello, world! */
public class FragmentA extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_a, container, false);
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_a,container,false);

        ImageView busan = (ImageView)rootView.findViewById(R.id.busan_img);
        ImageView seoul = (ImageView)rootView.findViewById(R.id.seoul_img);
        ImageView incheon = (ImageView)rootView.findViewById(R.id.incheon_img);
        ImageView gangwon = (ImageView)rootView.findViewById(R.id.gangwon_img);
        ImageView jeju = (ImageView)rootView.findViewById(R.id.jeju_img);
        ImageView jeolla = (ImageView)rootView.findViewById(R.id.jeolla_img);
        ImageView gyeongsang = (ImageView)rootView.findViewById(R.id.gyeongsang_img);
        ImageView chungcheong = (ImageView)rootView.findViewById(R.id.chungcheong_img);

        busan.setOnClickListener(new View.OnClickListener() {//부산여행 문의 클릭
            @Override
            public void onClick(View v) {
                //Activity root = getActivity(); //이 클래스가 프레그먼트이기 때문에 액티비티 정보를 얻는다.
                //Toast.makeText(root, "부산 클릭", Toast.LENGTH_SHORT).show();
            }
        });
        seoul.setOnClickListener(new View.OnClickListener() {//서울여행 문의 클릭
            @Override
            public void onClick(View v) {


            }
        });
        incheon.setOnClickListener(new View.OnClickListener() {//인천여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });
        gangwon.setOnClickListener(new View.OnClickListener() {//강원여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });
        jeju.setOnClickListener(new View.OnClickListener() {//제주여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });
        jeolla.setOnClickListener(new View.OnClickListener() {//전라여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });
        gyeongsang.setOnClickListener(new View.OnClickListener() {//경상여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });
        chungcheong.setOnClickListener(new View.OnClickListener() {//충청여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }
}