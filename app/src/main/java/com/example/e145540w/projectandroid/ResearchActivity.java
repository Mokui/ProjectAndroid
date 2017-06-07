package com.example.e145540w.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film movie = (Film) parent.getItemAtPosition(position);

                Intent descActiivity = new Intent(ResearchActivity.this, DescriptionActivity.class);
                descActiivity.putExtra("movie", movie);

                startActivity(descActiivity);
            }
        });

    }


}
