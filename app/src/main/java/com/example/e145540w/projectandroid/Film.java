package com.example.e145540w.projectandroid;

import android.net.Uri;

import java.util.List;

/**
 * Created by politchor on 06/06/17.
 */

public class Film {

    private String title; // obj.title
    private String overview; // obj.overview
    private Double note; //obj.vote_average
    private List<String> genres; // obj.genre (itérer dessus)
    private Uri image; // obj.backdrop_path

    public Film(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
