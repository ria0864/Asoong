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

        talk_mate.setOnClickListener(new View.OnClickListener() {//여행메이트 클릭
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPage(2,1);
            }
        });
        talk_tip.setOnClickListener(new View.OnClickListener() {//여행꿀팁 클릭
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPage(2,2);
            }
        });
        talk_free.setOnClickListener(new View.OnClickListener() {//자유톡 클릭
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPage(2,3);
            }
        });
        talk_event.setOnClickListener(new View.OnClickListener() {//이벤트 클릭
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPage(2,4);
            }
        });

        return rootView;
    }
}


