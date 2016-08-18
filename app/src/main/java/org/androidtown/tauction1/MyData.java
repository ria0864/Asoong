package org.androidtown.tauction1;

public class MyData {
    private int image;
    private String rank;
    private String like_num;
    private String name;
    private String address;

    public MyData(int image, String rank, String like_num, String name, String address){
        this.image = image;
        this.rank = rank;
        this.like_num = like_num;
        this.name = name;
        this.address = address;
    }
    public int getImage(){
        return image;
    }
    public String getRank(){ return rank; }
    public String getLike_num(){
        return like_num;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
}