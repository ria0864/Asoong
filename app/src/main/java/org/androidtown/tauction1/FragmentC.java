package org.androidtown.tauction1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentC extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_c, container, false);
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_c,container,false);

        ImageView talk_mate = (ImageView)rootView.findViewById(R.id.mate_img);
        ImageView talk_tip = (ImageView)rootView.findViewById(R.id.tip_img);
        ImageView talk_free = (ImageView)rootView.findViewById(R.id.free_img);
        ImageView talk_event = (ImageView)rootView.findViewById(R.id.event_img);

        talk_mate.setOnClickListener(new View.OnClickListener() {//부산여행 문의 클릭
            @Override
            public void onClick(View v) {
                //Activity root = getActivity(); //이 클래스가 프레그먼트이기 때문에 액티비티 정보를 얻는다.
                //Toast.makeText(root, "부산 클릭", Toast.LENGTH_SHORT).show();
            }
        });
        talk_tip.setOnClickListener(new View.OnClickListener() {//서울여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });
        talk_free.setOnClickListener(new View.OnClickListener() {//인천여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });
        talk_event.setOnClickListener(new View.OnClickListener() {//강원여행 문의 클릭
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }
}


