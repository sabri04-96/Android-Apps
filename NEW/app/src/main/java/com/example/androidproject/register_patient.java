package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidproject.Classes.Patient;
import com.example.androidproject.Database.DatabaseHelper;

public class register_patient extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    Button btn ;
    Patient P;

    EditText cin,nom,prenom,date_naiss,email,phone,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);
        cin = (EditText)findViewById(R.id.reg_cin_pat);
        nom = (EditText)findViewById(R.id.reg_nomRegPat);
        prenom = (EditText)findViewById(R.id.Reg_prenomPat);
        date_naiss = (EditText)findViewById(R.id.Reg_DateNaiss);
        email = (EditText)findViewById(R.id.reg_email);
        phone = (EditText)findViewById(R.id.reg_numTel);
        password = (EditText)findViewById(R.id.reg_password);

        btn = (Button) findViewById(R.id.register_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                P.setCin(cin.getText().toString());
                System.out.println(cin.getText().toString());
                P.setNom(nom.getText().toString());
                P.setPrenom(prenom.getText().toString());
                P.setBirthday(date_naiss.getText().toString());
                P.setEmail(email.getText().toString());
                P.setPhone(phone.getText().toString());
                P.setPassword(password.getText().toString());

                Log.d("test",P.toString());

                db.addPATIENT(P);
                Intent i = new Intent(register_patient.this, login_patient.class);
                startActivity(i);
            }
        });
    }
}