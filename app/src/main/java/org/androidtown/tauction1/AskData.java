package org.androidtown.tauction1;

/**
 * Created by Eun on 2016-08-16.
 */
public class AskData {
        private int image;
        private String rank;
        private String rating_star;
        private String name;
        private String address;

        public AskData(int image, String rank, String rating_star,  String name, String address){
            this.image = image;
            this.rank = rank;
            this.rating_star = rating_star;
            this.name = name;
            this.address = address;
        }
        public int getImage(){
            return image;
        }
        public String getRank(){
            return rank;
        }
        public String getRating_star(){
            return rating_star;
        }
        public String getName(){
            return name;
        }
        public String getAddress(){
            return address;
        }
    }
