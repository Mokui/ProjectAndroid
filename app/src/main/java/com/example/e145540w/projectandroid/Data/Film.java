package com.example.e145540w.projectandroid.Data;

import android.net.Uri;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by politchor on 06/06/17.
 */

public class Film implements Divertissement {

    private String title; // obj.title
    private String releaseDate; // obj.release_date
    private String overview; // obj.overview
    private String note; //obj.vote_average
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
