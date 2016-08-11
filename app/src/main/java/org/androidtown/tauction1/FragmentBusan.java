package org.androidtown.tauction1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by SSU on 2016-08-11.
 */
public class FragmentBusan extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_busan, container, false);
        ImageView busan = (ImageView) rootView.findViewById(R.id.busan_img);
        return rootView;
    }
}
