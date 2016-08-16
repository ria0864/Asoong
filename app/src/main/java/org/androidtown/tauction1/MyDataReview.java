package org.androidtown.tauction1;

public class MyDataReview {
    private String review_user_name;
    private String review_reg_day;
    private String review_txt;

    public MyDataReview(String review_user_name, String review_reg_day, String review_txt){
        this.review_user_name = review_user_name;
        this.review_reg_day = review_reg_day;
        this.review_txt = review_txt;
    }
    public String getReview_user_name(){
        return review_user_name;
    }
    public String getReview_reg_day(){
        return review_reg_day;
    }
    public String getReview_txt(){
        return review_txt;
    }
}