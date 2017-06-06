package com.example.e145540w.projectandroid.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

/**
 * Created by politchor on 06/06/17.
 */

public class MyCustomAdapter extends ArrayAdapter {

    public MyCustomAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
