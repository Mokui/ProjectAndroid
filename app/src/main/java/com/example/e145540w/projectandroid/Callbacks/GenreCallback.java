package com.example.e145540w.projectandroid.Callbacks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.e145540w.projectandroid.MainActivity;
import com.example.e145540w.projectandroid.Singletons.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by politchor on 07/06/17.
 */

public class GenreCallback extends AsyncTask<Map<String, String>, Void, JsonObjectRequest> {
    private Context context;
    private Map<String, String> allGenres  = new HashMap<>();
    private JsonObjectRequest request;

    public GenreCallback(Context context) {
        this.context = context;
    }

    @Override
    protected JsonObjectRequest doInBackground(Map<String, String>... params) {

        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key="+MainActivity.API_KEY;

        request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray responseArray = response.getJSONArray("genres");
                    for (int i = 0; i < responseArray.length(); i++) {
                        JSONObject genre = responseArray.getJSONObject(i);
                        allGenres.put(genre.getString("id"), genre.getString("name"));
                        Log.d("GENRES ADD", "Ajouté : " + allGenres.get(genre.getString("id")));
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

        return request;
    }

    @Override
    protected void onPostExecute(JsonObjectRequest v) {
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
