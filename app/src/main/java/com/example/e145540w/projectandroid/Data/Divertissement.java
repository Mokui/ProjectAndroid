package com.example.e145540w.projectandroid.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by politchor on 08/06/17.
 */

public interface Divertissement extends Serializable {

    public final static String JSON_TITLE = "title";
    public final static String JSON_NAME = "name";
    public final static String JSON_NOTE = "vote_average";
    public final static String JSON_RELEASE = "release_date";
    public final static String JSON_FIRST_AIR_DATE = "first_air_date";
    public final static String JSON_OVERVIEW = "overview";
    public final static String JSON_IMAGE = "backdrop_path";
    public final static String JSON_POSTER = "poster_path";

        public String getTitle();

        public void setTitle(String title);

        public String getOverview();

        public void setOverview(String overview);

        public String getNote();

        public void setNote(String note);

        public String getImage();

        public void setImage(String image);

        public String getReleaseDate();

        public void setReleaseDate(String releaseDate);
}
