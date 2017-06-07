package com.example.e145540w.projectandroid.Data;

import android.net.Uri;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by politchor on 06/06/17.
 */

public class Film implements Serializable {
    public final static String JSON_TITLE = "title";
    public final static String JSON_NOTE = "vote_average";
    public final static String JSON_RELEASE = "release_date";
    public final static String JSON_OVERVIEW = "overview";
    public final static String JSON_GENRES = "genre_ids";
    public final static String JSON_IMAGE = "backdrop_path";
    public final static String JSON_POSTER = "poster_path";



    private String title; // obj.title
    private String releaseDate; // obj.release_date
    private String overview; // obj.overview
    private String note; //obj.vote_average
    private List<String> genres; // obj.genre (itérer dessus)
    private String image; // obj.backdrop_path

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
