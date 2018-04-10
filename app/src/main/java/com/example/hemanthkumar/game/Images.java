package com.example.hemanthkumar.game;

/**
 * Created by hemanthkumar on 21/2/18.
 */

public class Images {

    String url;
    boolean display=false;
    int position;

    public String getUrl() {
        return url;
    }

    public void setPosition(int pos){
        this.position = pos;
    }

    public int getPosition(){
        return position;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
