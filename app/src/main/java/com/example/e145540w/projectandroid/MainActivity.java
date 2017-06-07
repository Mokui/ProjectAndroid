package com.example.e145540w.projectandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e145540w.projectandroid.Data.Film;
import com.example.e145540w.projectandroid.Singletons.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "6109c14cf4a63d6489336b6dc5cdb1b3";

    private int nbResults = 5;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.search_field);
        Spinner spinner = (Spinner) findViewById(R.id.search_spinner);

        final List<String> values = new LinkedList<>();
        values.add("Nombres de valeurs à afficher");
        values.add("5");
        values.add("10");
        values.add("15");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                if (!item.equals(values.get(0))) {
                    nbResults = Integer.valueOf(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void research(View view) {
        //OnClick du bouton de recherche
        String research = editText.getText().toString();
        // Vérifier l'intégrité des champs,
        // Faire requête pour films,
        // Appeler l'activité ResearchActivity avec les films
        if (!research.isEmpty()) {
            // Ici tembouille avec l'api
            String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=";
            String query = "";
            for (String str : research.split("\\s")) {
                if (query != "") {
                    query += '+';
                }
                query += str;
            }

            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + query, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.v("Réussite", "**** Film : " + response.toString());
                        JSONArray arrayMovies = (JSONArray) response.get("results");

                        ArrayList<Film> movieResults = new ArrayList<>();

                        // Pour chaque obj json dans la réponse
                        for (int i = 0; i < arrayMovies.length(); i++) {
                            JSONObject obj = arrayMovies.getJSONObject(i);
                            Log.v("FILM " + i + 1, obj.toString());

                            Film movie = new Film(obj.getString(Film.JSON_TITLE));

                            movie.setNote(obj.getString(Film.JSON_NOTE));
                            movie.setOverview(obj.getString(Film.JSON_OVERVIEW));
                            movie.setImage("https://image.tmdb.org/t/p/w500" + obj.getString(Film.JSON_IMAGE));
                            movie.setReleaseDate(obj.getString(Film.JSON_RELEASE));

                            JSONArray arrayGenres = obj.getJSONArray(Film.JSON_GENRES);
                            Log.d("GENRE ARRAY", arrayGenres.toString());
                            List<String> genres = new LinkedList<>();
                            for (int j = 0; j < arrayGenres.length(); j++) {
                                int genreID = (int) arrayGenres.get(j);
                                Map<String, String> allGenres = getAllGenres();

                                genres.add(allGenres.get(genreID));

                            }

                            movie.setGenres(genres);

                            movieResults.add(movie);
                        }

                        Intent listActivity = new Intent(MainActivity.this, ResearchActivity.class);
                        listActivity.putExtra("movies", movieResults);

                        startActivityForResult(listActivity, 1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error", error.toString());
                }
            });

            // Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(request);

        } else {
            Toast.makeText(this, "Veuillez saisir une recherche", Toast.LENGTH_LONG).show();
        }
    }


    private Map<String, String> getAllGenres() {

        final Map<String, String> genres = new HashMap<>();


        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=6109c14cf4a63d6489336b6dc5cdb1b3";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject genre = response.getJSONObject(i);
                        genres.put(genre.getString("id"), genre.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        });

        return genres;
    }

}
