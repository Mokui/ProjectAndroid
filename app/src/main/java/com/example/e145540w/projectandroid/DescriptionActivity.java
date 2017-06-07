package com.example.e145540w.projectandroid;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.e145540w.projectandroid.Data.Film;
import com.squareup.picasso.Picasso;

/**
 * Created by politchor on 07/06/17.
 */

public class DescriptionActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_description);

        Bundle extras = getIntent().getExtras();

        Film movie = (Film) extras.get("movie");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(movie.getImage()).into(imageView);

        TextView descTitle = (TextView) findViewById(R.id.descTitle);
        descTitle.setText(movie.getTitle());

        TextView descOverview = (TextView) findViewById(R.id.descOverview);
        descOverview.setText(movie.getOverview());

        RatingBar descStars = (RatingBar) findViewById(R.id.descStars);
        Float rating = Float.valueOf(movie.getNote()) / 2;
        descStars.setRating(rating);

        TextView descRelease = (TextView) findViewById(R.id.descRelease);
        descRelease.setText(movie.getReleaseDate());

        String genres = "Genres : ";

        for(String str : movie.getGenres()){
            if(!genres.equals("Genres : ")){
                genres += ", ";
            }
            genres += str;
        }

        TextView descGenre = (TextView) findViewById(R.id.descGenre);
        descGenre.setText(genres);

    }
}
