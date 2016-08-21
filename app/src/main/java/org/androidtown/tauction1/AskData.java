package org.androidtown.tauction1;

import java.util.Date;

/**
 * Created by Eun on 2016-08-16.
 */
public class AskData { //A_1에서 쓰이는 DATA들인데........서버에서 받아와야함.

    final static public int TYPE_All = 1;
    final static public int TYPE_BUSAN = 2;
    final static public int TYPE_SEOUL = 3;
    final static public int TYPE_INCHEON = 4;
    final static public int TYPE_GANGWON = 5;
    final static public int TYPE_JEJU = 6;
    final static public int TYPE_JEOLLA = 7;
    final static public int TYPE_GYEONGSANG = 8;
    final static public int TYPE_CHUNGCHEONG = 9;

    final static public  String [] TYPE_STRING = {"all","busan","seoul","incheon","gangwon","jeju","jeolla","gyeongsang","chungcheong"};
    public  String [] REG_STRING = {"전체","부산","서울","i인천","강원도","제주도","전라도","경상도","충청도"};

    private int image_region;

    private int ask_no;
    private Date ask_date;
    private String ask_title;
    private String ask_contents;
    private int done;
    private int reg_no;
    private int ask_num;
    private String ask_type;
    private String ask_gender;
    private String ask_trip;
    private int ask_budget;
    private String ask_convin;
    private Date ask_startday;
    private Date ask_endday;
    private String ask_pay;
    private String mem_id;
    private int isLike;
    private int ask_commentNo; //댓글 수.......서버에서 ask_no에 해당하는 comment table count해서

    //이 함수는 AskContentActivity에서 사용됨
    public AskData(int ask_no, Date ask_date, String ask_title, String ask_contents, int done, int reg_no, int ask_num, String ask_type, String ask_gender, String ask_trip, int ask_budget, String ask_convin, Date ask_startday, Date ask_endday, String ask_pay, String mem_id,int isLike, int ask_commentNo) {
        this.ask_no = ask_no;
        this.ask_date = ask_date;
        this.ask_title = ask_title;
        this.ask_contents = ask_contents;
        this.done = done;
        this.reg_no = reg_no;
        this.ask_num = ask_num;
        this.ask_type = ask_type;
        this.ask_gender = ask_gender;
        this.ask_trip = ask_trip;
        this.ask_budget = ask_budget;
        this.ask_convin = ask_convin;
        this.ask_startday = ask_startday;
        this.ask_endday = ask_endday;
        this.ask_pay = ask_pay;
        this.mem_id = mem_id;
        this.isLike = isLike;
        this.ask_commentNo = ask_commentNo;
    }

    static public String getRegNoToName(int type){
        return TYPE_STRING[type-1];
    }
    public String getNoToString(int type){
        return REG_STRING[type-1];
    }

    public int getImage_region() {
        return image_region;
    }
    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public void setImage_region(int image_region) {
        this.image_region = image_region;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getAsk_no() {
        return ask_no;
    }

    public void setAsk_no(int ask_no) {
        this.ask_no = ask_no;
    }

    public Date getAsk_date() {
        return ask_date;
    }

    public void setAsk_date(Date ask_date) {
        this.ask_date = ask_date;
    }

    public String getAsk_title() {
        return ask_title;
    }

    public void setAsk_title(String ask_title) {
        this.ask_title = ask_title;
    }

    public String getAsk_contents() {
        return ask_contents;
    }

    public void setAsk_contents(String ask_contents) {
        this.ask_contents = ask_contents;
    }

    public int getReg_no() {
        return reg_no;
    }

    public void setReg_no(int reg_no) {
        this.reg_no = reg_no;
    }

    public int getAsk_num() {
        return ask_num;
    }

    public void setAsk_num(int ask_num) {
        this.ask_num = ask_num;
    }

    public String getAsk_type() {
        return ask_type;
    }

    public void setAsk_type(String ask_type) {
        this.ask_type = ask_type;
    }

    public String getAsk_gender() {
        return ask_gender;
    }

    public void setAsk_gender(String ask_gender) {
        this.ask_gender = ask_gender;
    }

    public String getAsk_trip() {
        return ask_trip;
    }

    public void setAsk_trip(String ask_trip) {
        this.ask_trip = ask_trip;
    }

    public int getAsk_budget() {
        return ask_budget;
    }

    public void setAsk_budget(int ask_budget) {
        this.ask_budget = ask_budget;
    }

    public String getAsk_convin() {
        return ask_convin;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public void setAsk_convin(String ask_convin) {
        this.ask_convin = ask_convin;
    }

    public Date getAsk_startday() {
        return ask_startday;
    }

    public void setAsk_startday(Date ask_startday) {
        this.ask_startday = ask_startday;
    }

    public Date getAsk_endday() {
        return ask_endday;
    }

    public void setAsk_endday(Date ask_endday) {
        this.ask_endday = ask_endday;
    }

    public String getAsk_pay() {
        return ask_pay;
    }

    public void setAsk_pay(String ask_pay) {
        this.ask_pay = ask_pay;
    }

    public int getAsk_commentNo() {
        return ask_commentNo;
    }

    public void setAsk_commentNo(int ask_commentNo) {
        this.ask_commentNo = ask_commentNo;
    }


}

