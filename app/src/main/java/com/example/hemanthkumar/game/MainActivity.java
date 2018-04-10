package com.example.hemanthkumar.game;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.hemanthkumar.game.R.id.iv_grid_view;
import static com.example.hemanthkumar.game.R.id.iv_imageView;
import static com.example.hemanthkumar.game.R.id.line1;

public class MainActivity extends AppCompatActivity {


    GridView gridView;
    MyAdapter adapter;
    Api api;
    Context context;
    TextView textview;
    ImageView imageView;

    int current_position = 0;
    int mistake = 0;
    int counter;
    int increase = 0;
    int score=0;


    //Declaring new ArrayList
    ArrayList<Images> url_list;

    String url;
    List<Integer> solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        gridView = findViewById(R.id.iv_grid_view);
        textview = findViewById(R.id.textview);
        imageView = findViewById(R.id.iv_imageView);
        //this logic for
        solution = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            solution.add(i);
        }
        Collections.shuffle(solution);

        Toast.makeText(context, "solution is " + solution, Toast.LENGTH_SHORT).show();

        Timer timer = new Timer();
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                textview.setText(String.valueOf(counter));
                counter++;
            }

            public void onFinish() {
                disappear();
            }
        }.start();


        //Initializing ArrayList
        url_list = new ArrayList<>();
        //creating object for Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //linking interface with class
        api = retrofit.create(Api.class);
        //getting method of interface
        Call<Example> call = api.getImages();
        //calling enqueue method for getting server response
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example data = response.body();
                for (int i = 0; i < 9; i++) {
                    url = data.getItems().get(i).getMedia().getM();
                    Images image = new Images();
                    image.setUrl(url);
                    image.setPosition(i);
                    image.setDisplay(false);
                    url_list.add(image);
                }

                //sending all urls to Adapter
                adapter = new MyAdapter(url_list, context);
                //setting the Adapter
                gridView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }
        });

    }

    //Here iam calling disappear function for Hiding Data
    public void disappear() {
        adapter.Hide();
        nextImage();

    }

    public void comparing(int pos) {

        if (pos == current_position) {
            score+=10-(2*mistake);
            Toast.makeText(context, "score details"+score, Toast.LENGTH_SHORT).show();
            mistake = 0;
            url_list.get(pos).setDisplay(true);
            adapter.setImage(url_list);
            nextImage();

        } else {
            mistake++;
            Toast.makeText(context, mistake + " try agian..wrong", Toast.LENGTH_SHORT).show();
            if (mistake == 3) {
                url_list.get(current_position).setDisplay(true);
                adapter.setImage(url_list);
                current_position++;
                nextImage();
                mistake = 0;
            }

        }


    }

    private void scoreUpdate() {

    }

    //fetching next image
    public void nextImage() {
        if (increase < 9) {
            current_position = solution.get(increase);
            url = url_list.get(current_position).getUrl();
            Picasso.with(context).load(url).into(imageView);
            increase++;
        }

    }
}
