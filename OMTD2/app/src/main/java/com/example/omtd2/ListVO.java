package com.example.omtd2;

import android.graphics.drawable.Drawable;

public class ListVO {
    private int img;
    private String Cost;
    private String context;

    public ListVO(int img, String context, String Cost){
        this.img = img;
        this.Cost = Cost;
        this.context = context;
    }

    public int getImg(){
        return img;
    }

    public void setImg(int img){
        this.img = img;
    }

    public String getCost(){
        return Cost;
    }

    public void setCost(String cost){
        Cost = cost;
    }
    public String getContext(){
        return context;
    }
    public void setContext(String context){
        this.context = context;
    }
}
