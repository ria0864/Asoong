package org.androidtown.tauction1;

/**
 * Created by Eun on 2016-08-16.
 */
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Eun on 2016-08-16.
 */
public class SpinnerList {

    private Context context;

    private String[] nation = {"서울", "부산", "제주", "인천/경기"};
    private String[] hotel = {"모텔", "펜션", "민박", "게스트하우스"};

    private ArrayList<String> arrayList = new ArrayList();
    private ArrayList<String> arrayList1 = new ArrayList();


    public ArrayList<String> getArrayList() {

        for (int i = 0; i < getSize(nation); i++) {

            arrayList.add(getNation(i));

        }

        return arrayList;

    }
    public String getNation(int indx) {

        return nation[indx];

    }


    public int getSize(String[] nation) {

        return nation.length;

    } //지역

    public ArrayList<String> getArrayList1() {

        for (int i = 0; i < getSize1(hotel); i++) {

            arrayList.add(getHotel(i));

        }

        return arrayList1;

    }
    public String getHotel(int indx) {

        return hotel[indx];

    }


    public int getSize1(String[] hotel) {

        return hotel.length;

    }


}