package com.example.hemanthkumar.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hemanthkumar on 15/2/18.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Images> images;


    ImageButton imageButton;

    Boolean hide = false;

    //constructor
    public MyAdapter(ArrayList<Images> images, Context context) {

        this.images = images;
        this.context = context;

    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.gridview, parent, false);
        imageButton = (ImageButton) convertView.findViewById(R.id.iv_imageView);

        Picasso.with(context).load(images.get(position).getUrl()).into(imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) context).comparing(position);

            }
        });

        if (hide == true) {
            imageButton.setImageResource(0);

        } else {
            if (images.get(position).isDisplay()) {
                Picasso.with(context).load(images.get(position).getUrl()).into(imageButton);
            } else {
                imageButton.setImageResource(0);
            }
        }
        return convertView;
    }

    //getting list of urls from MainActivity
    public void setImage(ArrayList<Images> list) {
        //here iam Equating images with list of urls
        hide = false;
        images = list;
        notifyDataSetChanged();
    }


    public void Hide() {
        hide = true;
        notifyDataSetChanged();
    }

}

