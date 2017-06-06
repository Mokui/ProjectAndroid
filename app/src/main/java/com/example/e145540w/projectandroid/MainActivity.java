package com.example.e145540w.projectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.e145540w.projectandroid.Singletons.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;



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
                if(!item.equals(values.get(0))){
                    nbResults = Integer.valueOf(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void research(View view){
        //OnClick du bouton de recherche
        String research = editText.getText().toString();
        // Vérifier l'intégrité des champs,
        // Faire requête pour films,
        // Appeler l'activité ResearchActivity avec les films
        if(!research.isEmpty()){
            // Ici tembouille avec l'api
            String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&query=";
            String query = "";
            for(String str : research.split("\\s")){
                if(query != ""){
                    query += '+';
                }
                query += str;
            }

            RequestQueue queue = Volley.newRequestQueue(this);

            JsonArrayRequest request = new JsonArrayRequest(url + query, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        JSONObject object = response.getJSONObject(0);
                        Toast.makeText(MainActivity.this, "Réussite !!  *** Film : " + object.getString("original_title"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   Toast.makeText(MainActivity.this, "Error : " + error.toString(), Toast.LENGTH_LONG).show();
                }
            });

            // Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(request);

        }
        else {
            Toast.makeText(this, "Veuillez saisir une recherche", Toast.LENGTH_LONG).show();
        }
    }
}
