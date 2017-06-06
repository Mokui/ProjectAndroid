package com.example.e145540w.projectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by politchor on 06/06/17.
 */

public class ResearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ListView listView = (ListView) findViewById(R.id.movieList);

        //Ici récupérer les lignes avec la requête
    }
}
