package com.example.luismunoz.recyclerviewwithdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView bigImage = (ImageView) findViewById(R.id.bigImage);

        String url = getIntent().getStringExtra("image");
        Log.v("URL", url);

        Glide.with(this).load(url).into(bigImage);
    }
}
