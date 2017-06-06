package com.example.e145540w.projectandroid.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.e145540w.projectandroid.Film;
import com.example.e145540w.projectandroid.R;
import com.squareup.picasso.Picasso;

/**
 * Created by politchor on 06/06/17.
 */

public class MyCustomAdapter extends ArrayAdapter {

    public MyCustomAdapter(@NonNull Context context, List<Film> filmsList) {
        super(context,R.layout.row_search_list, filmsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        row = inflater.inflate(R.layout.row_search_list, null);

        Film movie = (Film) getItem(position);

        TextView movieTitle = (TextView) row.findViewById(R.id.movie_title);
        movieTitle.setText(movie.getTitle());

        TextView movieNote = (TextView) row.findViewById(R.id.movie_note);
        movieNote.setText(movie.getNote().toString() + "/10");

        ImageView movieBackdrop = (ImageView) row.findViewById(R.id.movie_backdrop);
        Picasso.with(getContext()).load(movie.getImage()).into(movieBackdrop);
        return row;
    }
}
