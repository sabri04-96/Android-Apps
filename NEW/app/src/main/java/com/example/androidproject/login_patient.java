package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproject.Classes.Centre;
import com.example.androidproject.Classes.Infermier;
import com.example.androidproject.Classes.Patient;
import com.example.androidproject.Classes.Status;
import com.example.androidproject.Classes.Vaccin;
import com.example.androidproject.Database.DatabaseHelper;

import java.util.List;

public class login_patient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

                DatabaseHelper db = new DatabaseHelper(this);

                Button btnLogin;
                EditText edtUsername;
                EditText edtPassword;
                TextView clq;


                btnLogin = (Button) findViewById(R.id.Btn_RegisterPT);
                edtUsername = (EditText) findViewById(R.id.TxtPUserP);
                edtPassword = (EditText) findViewById(R.id.TxtPpsdP);

                clq=(TextView)findViewById(R.id.clq);

                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isExist = db.checkPATIENT(edtUsername.getText().toString(), edtPassword.getText().toString());
                        Log.d("Reading: ", "Reading all contacts..");
                        List<Patient> patientList = db.getAllPATIENT();

                        for (Patient cn : patientList) {
                            String log = "Id: " + cn.getId() + " ,Name: " + cn.getNom() + " ,Prenom: " +
                                    cn.getPrenom();
                            // Writing Contacts to log
                            Log.d("Name: ", log);}
                        if (isExist) {
                            Context context = getApplicationContext();
                            CharSequence text = "Login succeffuly";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            Intent intent = new Intent(login_patient.this, index_patient.class);
                            intent.putExtra("nom", edtUsername.getText().toString());
                            startActivity(intent);
                        } else {

                            edtPassword.setText(null);
                            Toast.makeText(login_patient.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

                clq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(login_patient.this,register_patient.class);
                        startActivity(i);
                    }
                });

            }
        }



