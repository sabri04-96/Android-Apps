package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.androidproject.Classes.Patient;
import com.example.androidproject.Database.DatabaseHelper;
import com.example.androidproject.Validations.MedecinAdapter;

import java.util.ArrayList;
import java.util.List;

public class IndexMedecin extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MedecinAdapter medecinAdapter;
    private SearchView searchView;
    private ArrayList<Patient> patients;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search View");
        recyclerView = (RecyclerView) findViewById(R.id.RV_Patient);
        Log.d("Reading: ", "Reading all contacts..");
        List<Patient> patientList = db.getAllPatientByStatus(2);

        medecinAdapter = new MedecinAdapter(this,patientList);
        for (Patient cn : patientList) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getNom() + " ,Prenom: " +
                    cn.getPrenom();
            // Writing Contacts to log
            Log.d("Name: ", log);}
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(medecinAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }


    }





