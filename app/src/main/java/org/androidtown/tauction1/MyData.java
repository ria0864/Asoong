package org.androidtown.tauction1;

public class MyData {
    private int image;
    private String rank;
    private String rating_star;
    private String rating_txt;
    private String name;
    private String address;

    public MyData(int image, String rank, String rating_star, String rating_txt, String name, String address){
        this.image = image;
        this.rank = rank;
        this.rating_star = rating_star;
        this.rating_txt = rating_txt;
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
    public String getRating_txt(){
        return rating_txt;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
}