package com.example.androidproject.Validations;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidproject.Classes.Centre;
import com.example.androidproject.Classes.Medecin;
import com.example.androidproject.Database.DatabaseHelper;
import com.example.androidproject.R;
import com.example.androidproject.index_admin;
import com.example.androidproject.login_admin;

import java.util.ArrayList;
import java.util.List;

public class FirstUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_update);
        DatabaseHelper db = new  DatabaseHelper(this);
        Spinner s1,s2;
        Button btn;

        btn = (Button) findViewById(R.id.button_affect);
        s1 = (Spinner) findViewById(R.id.spinner_medecin);
        s2 = (Spinner) findViewById(R.id.spinner_infermier);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }});
    }
}