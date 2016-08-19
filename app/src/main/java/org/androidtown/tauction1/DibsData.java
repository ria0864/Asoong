package org.androidtown.tauction1;

/**
 * Created by Eun on 2016-08-16.
 */
public class DibsData {
        private String dibs_userName;
        private String dibs_reg_day;
        private String dibs_title;
        private String dibs_content;
        private String dibs_commentNo;


        public DibsData(String dibs_userName, String dibs_reg_day, String dibs_title,String dibs_content ,String dibs_commentNo){
            this.dibs_userName = dibs_userName;
            this.dibs_reg_day = dibs_reg_day;
            this.dibs_title = dibs_title;
            this.dibs_content = dibs_content;
            this.dibs_commentNo = dibs_commentNo;
        }
        public String getDibs_userName(){
            return dibs_userName;
        }
        public String getDibs_reg_day(){ return dibs_reg_day; }
        public String getDibs_title(){ return dibs_title; }
        public String getDibs_content(){ return dibs_content; }
        public String getDibs_commentNo(){ return dibs_commentNo; }

    }
