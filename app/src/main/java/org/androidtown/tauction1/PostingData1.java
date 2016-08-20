package org.androidtown.tauction1;

/**
 * Created by Eun on 2016-08-16.
 */
public class PostingData1 {
    private String posting_title;
    private String posting_username;
    private String posting_reg_day;


    public PostingData1(String posting_title, String posting_username, String posting_reg_day){
        this.posting_title = posting_title;
        this.posting_username = posting_username;
        this.posting_reg_day = posting_reg_day;
    }
    public String getPosting_title(){
        return posting_title;
    }
    public String getPosting_username(){ return posting_username; }
    public String getPosting_reg_day(){
        return posting_reg_day;
    }

}
