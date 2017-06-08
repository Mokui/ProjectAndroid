package com.example.e145540w.projectandroid.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by politchor on 08/06/17.
 */

public class Serie implements Divertissement {

    private String name; // obj.name
    private String releaseDate; // obj.release_date
    private String overview; // obj.overview
    private String note; //obj.vote_average
    private String image; // obj.backdrop_path

    public Serie(String name) {
        this.name = name;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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
}
