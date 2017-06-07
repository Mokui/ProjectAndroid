package com.example.e145540w.projectandroid.Callbacks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.e145540w.projectandroid.MainActivity;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by politchor on 07/06/17.
 */

public class GenreCallback extends AsyncTask<Map<String, String>, Void, Void> {
    private Context context;
    public GenreCallback(Context context) {
        this.context = context;
    }



    @Override
    protected Void doInBackground(Map<String, String>... params) {

        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key="+MainActivity.API_KEY;

        RequestQueue queue = Volley.newRequestQueue(context);
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(), future, future);

        queue.add(request);

        try {
            JSONObject response = future.get(30, TimeUnit.SECONDS); // this will block
            Log.d("JSONOBJECT", response.toString());
        } catch (InterruptedException e) {
            // exception handling
            e.printStackTrace();
        } catch (ExecutionException e) {
            // exception handling
            e.printStackTrace();
        } catch (TimeoutException e) {
            // exception handling
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
