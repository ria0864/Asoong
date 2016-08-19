package org.androidtown.tauction1;

/**
 * Created by Eun on 2016-08-16.
 */
public class AskData {
        private int image_region;
        private String done;
        private String region_detail;
        private String day;
        private String price;
        private String people_num;
        private String ask_commentNo;

        public AskData(int image_region, String done, String region_detail,  String day,String price, String people_num, String ask_commentNo){
            this.image_region = image_region;
            this.done = done;
            this.region_detail = region_detail;
            this.day = day;
            this.price = price;
            this.people_num = people_num;
            this.ask_commentNo = ask_commentNo;
        }
        public int getImage_region(){
            return image_region;
        }
        public String getDone(){
            return done;
        }
        public String getRegion_detail(){
            return region_detail;
        }
        public String getDay(){
            return day;
        }
        public String getPrice(){ return price; }
        public String getPeople_num(){ return people_num; }
        public String getAsk_commentNo(){ return ask_commentNo; }
    }
