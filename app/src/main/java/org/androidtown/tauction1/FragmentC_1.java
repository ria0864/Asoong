package org.androidtown.tauction1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



import java.util.ArrayList;

/**
 * Created by SSU on 2016-08-11.
 */
public class FragmentC_1 extends Fragment{
    ImageButton goFragment;

    ListView list;
    PostingAdapter adapter;
    ArrayList<PostingData1> arrData;
    Button textaddbtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_c_1, container, false);

        goFragment=(ImageButton)rootView.findViewById(R.id.goFragmentC);
        goFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPage(2,0);
            }
        });

        Spinner yearSpinner = (Spinner)rootView.findViewById(R.id.spinner_post);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinnerPost, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        textaddbtn = (Button)rootView.findViewById(R.id.btn_addPosting);

       textaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PostingTextAdd.class));
            }
        });











        //리스트에 보여줄 데이터를 세팅한다.
        setData();

        //어댑터 생성
        adapter = new PostingAdapter(rootView.getContext(), arrData);

        //리스트뷰에 어댑터 연결
        list = (ListView)rootView.findViewById(R.id.list_posting);
        list.setAdapter(adapter);

        //mScrollView=(ScrollView)findViewById(R.id.ScrollView);
        //setScroll(list);

        return rootView;
    }



    private void setData(){
        arrData = new ArrayList<PostingData1>();
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));
        arrData.add(new PostingData1( "여행꿀팁 알려드려용 ~~","sjjo0319", "2016.8.7"));

    }


}

