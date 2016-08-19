package org.androidtown.tauction1;

public class FavoritesData {
    private int favorites_image;
    private String favorites_like_num;
    private String favorites_name;
    private String favorites_address;

    public FavoritesData(int favorites_image, String favorites_like_num, String favorites_name, String favorites_address){
        this.favorites_image = favorites_image;
        this.favorites_like_num = favorites_like_num;
        this.favorites_name = favorites_name;
        this.favorites_address = favorites_address;
    }
    public int getFavorites_image(){
        return favorites_image;
    }
    public String getFavorites_like_num(){
        return favorites_like_num;
    }
    public String getFavorites_name(){
        return favorites_name;
    }
    public String getFavorites_address(){
        return favorites_address;
    }
}