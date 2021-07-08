package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidproject.Classes.Patient;
import com.example.androidproject.Database.DatabaseHelper;
import com.example.androidproject.Validations.UpdatePatient;

public class index_patient extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    private Button button_reserv,btn;
    Patient P;
    EditText cin,nom,prenom,date_naiss,email,phone,password,NB_VCC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_patient);

        NB_VCC = (EditText) findViewById(R.id.txxtNB_vcc);
        cin = (EditText) findViewById(R.id.txxt_cin);
        nom = (EditText) findViewById(R.id.txxtNom);
        prenom = (EditText) findViewById(R.id.txxtprenom);
        date_naiss = (EditText) findViewById(R.id.txxtdate);
        email = (EditText) findViewById(R.id.txxtemail);
        phone = (EditText) findViewById(R.id.txxtNumTel);


        Intent intent = getIntent();
        String dataTransmited = intent.getStringExtra("cin");
        Log.d("test", dataTransmited);
        Context context = getApplicationContext();
        Patient pa = db.getPatientByEmail(dataTransmited);

        NB_VCC.setHint((pa.getNbr_vaccin()));
        cin.setHint(pa.getCin());
        nom.setHint(pa.getNom());
        prenom.setHint(pa.getPrenom());
        date_naiss.setHint(pa.getBirthday());
        email.setHint(pa.getEmail());
        phone.setHint(pa.getPhone());



        button_reserv = (Button) findViewById(R.id.button_reserv);
        button_reserv.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent intent = new Intent(index_patient.this, index_reservation.class);
                                                 startActivity(intent);
                                             }
                                         }

        );
        btn = (Button) findViewById(R.id.button_edit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(index_patient.this, UpdatePatient.class);
                startActivity(intent);
            }


        });



    }
}