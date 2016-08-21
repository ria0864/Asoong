package org.androidtown.tauction1;

/**
 * Created by SSU on 2016-08-20.
 */
public class TalkPostListData {
    private int posting_no;
    private String posting_type;
    private String posting_date;
    private String posting_title;
    private String mem_id;

    public TalkPostListData(int posting_no, String posting_type, String posting_date, String posting_title, String mem_id){
        this.posting_no = posting_no;
        this.posting_type = posting_type;
        this.posting_date = posting_date;
        this.posting_title = posting_title;
        this.mem_id = mem_id;
    }

    public int getPosting_no() {
        return posting_no;
    }

    public String getPosting_type() {
        return posting_type;
    }

    public String getPosting_date() {
        return posting_date;
    }

    public String getPosting_title() {
        return posting_title;
    }

    public String getMem_id() {
        return mem_id;
    }
}
