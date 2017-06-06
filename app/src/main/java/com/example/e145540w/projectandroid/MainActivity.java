package com.example.e145540w.projectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int nbResults = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void research(View view){
        //OnClick du bouton de recherche

        // Vérifier l'intégrité des champs et appeler l'activité ResearchActivity avec les valeurs
    }
}
