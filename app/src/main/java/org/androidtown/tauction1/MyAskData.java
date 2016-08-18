package org.androidtown.tauction1;

/**
 * Created by Eun on 2016-08-16.
 */
public class MyAskData {
        private String my_ask_reg_day;
        private String my_ask_title;
        private String my_ask_content;
        private String my_ask_commentNo;


        public MyAskData(String my_ask_reg_day, String my_ask_title, String my_ask_content, String my_ask_commentNo){
            this.my_ask_reg_day = my_ask_reg_day;
            this.my_ask_title = my_ask_title;
            this.my_ask_content = my_ask_content;
            this.my_ask_commentNo = my_ask_commentNo;
        }
        public String getMy_ask_reg_day(){
            return my_ask_reg_day;
        }
        public String getMy_ask_title(){ return my_ask_title; }
        public String getMy_ask_content(){
        return my_ask_content;
    }
        public String getMy_ask_commentNo(){ return my_ask_commentNo; }

    }
