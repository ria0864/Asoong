package org.androidtown.tauction1;

/**
 * Created by SSU on 2016-08-20.
 */
public class TalkPostingData {
    private int posting_no;
    private String posting_type;
    private String posting_date;
    private String posting_title;
    private String posting_content;
    private int mem_no;
    private String mem_id;

    final static public int TYPE_MATE = 1;
    final static public int TYPE_TIP = 2;
    final static public int TYPE_FREE = 3;
    final static public int TYPE_EVENT = 4;
    final static public  String [] TYPE_STRING = {"mate","tip","free","event"};

    public TalkPostingData(int posting_no, String posting_type, String posting_date, String posting_title, String posting_content, int mem_no, String mem_id){
        this.posting_no = posting_no;
        this.posting_type = posting_type;
        this.posting_date = posting_date;
        this.posting_title = posting_title;
        this.posting_content = posting_content;
        this.mem_no = mem_no;
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

    public String getPosting_content() {
        return posting_content;
    }

    public int getMem_no() {
        return mem_no;
    }

    public String getMem_id() {
        return mem_id;
    }

    static public String getTypeToString(int type) {
        return TYPE_STRING[type-1];
    }
}
