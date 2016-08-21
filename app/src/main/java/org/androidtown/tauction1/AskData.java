package org.androidtown.tauction1;

import java.util.Date;

/**
 * Created by jaechulE on 2016-08-21.
 */

public class AskData {
    private int image_region;
    private int ask_no;
    private int done;
    private int reg_no;
    private Date ask_startday;
    private Date ask_endday;
    private int ask_budget;
    private int ask_num;
    private int mem_no;
    private String mem_id;
    private int ask_commentNo; //댓글 수.......서버에서 ask_no에 해당하는 comment table count해서

    public AskData(int image_region, int ask_no, int reg_no, int done, Date ask_startday, Date ask_endday, int ask_budget, int ask_num, int mem_no, String mem_id, int ask_commentNo) {
        this.image_region = image_region;
        this.ask_no = ask_no;
        this.reg_no = reg_no;
        this.done = done; //경매완료(1이면 경매완료, 2이면 경매진행중)
        this.ask_startday = ask_startday;
        this.ask_endday = ask_endday;
        this.ask_budget = ask_budget;
        this.ask_num = ask_num;
        this.mem_no = mem_no;
        this.mem_id = mem_id;
        this.ask_commentNo = ask_commentNo;
    }

    public int getImage_region() {
        return image_region;
    }

    public int getAsk_no() {
        return ask_no;
    }

    public int getDone() {
        return done;
    }

    public int getReg_no() {
        return reg_no;
    }

    public Date getAsk_startday() {
        return ask_startday;
    }

    public Date getAsk_endday() {
        return ask_endday;
    }

    public int getAsk_budget() {
        return ask_budget;
    }

    public int getAsk_num() {
        return ask_num;
    }

    public int getMem_no() {
        return mem_no;
    }

    public String getMem_id() {
        return mem_id;
    }

    public int getAsk_commentNo() {
        return ask_commentNo;
    }
}
