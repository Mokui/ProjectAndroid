package com.example.e145540w.projectandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.e145540w.projectandroid.Adapters.MyCustomAdapter;
import com.example.e145540w.projectandroid.Data.Film;

import java.util.List;

/**
 * Created by politchor on 06/06/17.
 */

public class ResearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        Bundle extras = getIntent().getExtras();

        List<Film> films = (List<Film>) extras.get("movies");

        ListView listView = (ListView) findViewById(R.id.movie_list);

        ArrayAdapter customAdapter = new MyCustomAdapter(this, films);

        listView.setAdapter(customAdapter);

        //Ici récupérer les lignes avec la requête
    }
}
