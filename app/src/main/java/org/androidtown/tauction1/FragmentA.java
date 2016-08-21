package org.androidtown.tauction1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentA extends Fragment {

    Button btn_filter;
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

        btn_filter=(Button)rootView.findViewById(R.id.btn_filter);

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FilterActivity.class));
            }
        });

        busan.setOnClickListener(new View.OnClickListener() {//부산여행 문의 클릭
            @Override
            public void onClick(View v) {
                //Activity root = getActivity(); //이 클래스가 프레그먼트이기 때문에 액티비티 정보를 얻는다.
                Toast.makeText(getActivity(), "부산 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,1);
            }
        });
        seoul.setOnClickListener(new View.OnClickListener() {//서울여행 문의 클릭
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "서울 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,2);
            }
        });
        incheon.setOnClickListener(new View.OnClickListener() {//인천여행 문의 클릭
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "인천 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,3);
            }
        });
        gangwon.setOnClickListener(new View.OnClickListener() {//강원여행 문의 클릭
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "강원도 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,4);
            }
        });
        jeju.setOnClickListener(new View.OnClickListener() {//제주여행 문의 클릭
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "제주도 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,5);
            }
        });
        jeolla.setOnClickListener(new View.OnClickListener() {//전라여행 문의 클릭
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "전라도 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,6);
            }
        });
        gyeongsang.setOnClickListener(new View.OnClickListener() {//경상여행 문의 클릭
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "경상도 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,7);
            }
        });
        chungcheong.setOnClickListener(new View.OnClickListener() {//충청여행 문의 클릭
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "충청도 클릭", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPage(0,8);
            }
        });

        return rootView;
    }
}