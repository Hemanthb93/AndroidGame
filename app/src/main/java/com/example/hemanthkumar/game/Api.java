package com.example.hemanthkumar.game;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hemanthkumar on 15/2/18.
 */

public interface Api {
    String Base_Url = "https://api.flickr.com/services/feeds/";

    @GET("photos_public.gne?format=json&nojsoncallback=1")
    Call<Example> getImages();

}
