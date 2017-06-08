package com.example.e145540w.projectandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.e145540w.projectandroid.Data.Divertissement;
import com.example.e145540w.projectandroid.Data.Film;
import com.example.e145540w.projectandroid.Data.Serie;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class MainActivity extends AppCompatActivity {

    public final static String API_KEY = "6109c14cf4a63d6489336b6dc5cdb1b3";

    Map<String, String> allGenres  = new HashMap<>();
    private int nbResults = 25;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "Bonjour", Toast.LENGTH_LONG).show();

        editText = (EditText) findViewById(R.id.search_field);
        Spinner spinner = (Spinner) findViewById(R.id.search_spinner);

        final List<String> values = new LinkedList<>();
        values.add("Nombres de valeurs à afficher");
        values.add("1");
        values.add("5");
        values.add("10");
        values.add("25");

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
            RadioButton button = (RadioButton) findViewById(R.id.radioMovie);
            final String divSearch;
            if(button.isChecked()){
                divSearch = "movie";
            }
            else {
                divSearch = "tv";
            }

            Log.d("DivSearch : " , divSearch);

            String url = "https://api.themoviedb.org/3/search/"+divSearch+"?api_key=" + API_KEY + "&query=";
            String query = "";
            for (String str : research.split("\\s")) {
                if (query != "") {
                    query += '+';
                }
                query += str;
            }

            Log.d("URL REQUEST", url + query);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url + query, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.v("Réussite", "**** Film : " + response.toString());
                        JSONArray arrayMovies = (JSONArray) response.get("results");

                        ArrayList<Divertissement> results = new ArrayList<>();

                        int size = arrayMovies.length() > nbResults ? nbResults : arrayMovies.length();
                        // Pour chaque obj json dans la réponse
                        for (int i = 0; i < size; i++) {
                            JSONObject obj = arrayMovies.getJSONObject(i);
                            Log.v("DIVERTISSEMENT " + i + 1, obj.toString());

                            Divertissement divertissement = null;
                            if(divSearch.equals("movie")){
                                divertissement = new Film(obj.getString(Divertissement.JSON_TITLE));
                                divertissement.setReleaseDate(obj.getString(Divertissement.JSON_RELEASE));
                            }
                            else  {
                                divertissement = new Serie(obj.getString(Divertissement.JSON_NAME));
                                divertissement.setReleaseDate(obj.getString(Divertissement.JSON_FIRST_AIR_DATE));
                            }


                            divertissement.setNote(obj.getString(Divertissement.JSON_NOTE));
                            divertissement.setOverview(obj.getString(Divertissement.JSON_OVERVIEW));

                            String imageAddress = obj.getString(Divertissement.JSON_IMAGE);
                            if(imageAddress.equals("null")){
                                imageAddress = obj.getString(Divertissement.JSON_POSTER);
                            }
                            divertissement.setImage("https://image.tmdb.org/t/p/w500" + imageAddress);

                            results.add(divertissement);
                        }

                        if(results.size() == 0){
                            Toast.makeText(MainActivity.this, "Aucun résultat pour cette recherche", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Intent listActivity = new Intent(MainActivity.this, ResearchActivity.class);
                            listActivity.putExtra("results", results);

                            startActivityForResult(listActivity, 1);
                        }


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

}
