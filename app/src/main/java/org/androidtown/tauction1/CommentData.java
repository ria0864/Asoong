package org.androidtown.tauction1;

/**
 * Created by Eun on 2016-08-16.
 */
public class CommentData {
        private String comment_accomName;
        private String comment_regDay;
        private String comment_price;
        private String comment_content;


        public CommentData(String comment_accomName, String comment_regDay, String comment_price, String comment_content){
            this.comment_accomName = comment_accomName;
            this.comment_regDay = comment_regDay;
            this.comment_price = comment_price;
            this.comment_content = comment_content;
        }
        public String getComment_accomName(){
            return comment_accomName;
        }
        public String getComment_regDay(){
            return comment_regDay;
        }
        public String getComment_price(){ return comment_price; }
        public String getComment_content(){ return comment_content; }
    }
