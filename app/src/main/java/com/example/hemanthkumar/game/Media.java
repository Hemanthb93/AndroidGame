package com.example.hemanthkumar.game;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hemanthkumar on 15/2/18.
 */

public class Media {

    @SerializedName("m")
    @Expose
    private String m;

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

}

