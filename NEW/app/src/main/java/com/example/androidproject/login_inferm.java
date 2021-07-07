package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidproject.Classes.Centre;
import com.example.androidproject.Classes.Infermier;
import com.example.androidproject.Classes.Medecin;
import com.example.androidproject.Classes.Patient;
import com.example.androidproject.Classes.Status;
import com.example.androidproject.Classes.Vaccin;
import com.example.androidproject.Database.DatabaseHelper;
import com.example.androidproject.R;

import java.util.List;


public class login_inferm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_inferm);
        DatabaseHelper db = new  DatabaseHelper(this);


        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        //db.addContact(new Contact("Tommy", "9522222222"));
        //db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List< Patient> personneList = db.getAllPATIENT();

        for ( Patient cn : personneList) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getNom() + " ,Phone: " +
                    cn.getPhone();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        Button btnLogin;
        EditText edtUsername;
        EditText edtPassword;


        btnLogin = (Button) findViewById(R.id.Btn_LoginInfer);
        edtUsername = (EditText) findViewById(R.id.TxtUsernameInfer);
        edtPassword = (EditText) findViewById(R.id.TxtPasswordInfer);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = db.checkMedecin(edtUsername.getText().toString(), edtPassword.getText().toString());
                Log.d("Reading: ", "Reading all contacts..");
                List< Medecin> MedList = db.getAllmedecin();

                for ( Medecin cn : MedList) {
                    String log = "Id: " + cn.getId() + " ,Name: " + cn.getNom() + " ,Prenom: " +
                            cn.getPrenom();
                    // Writing Contacts to log
                }
                if (isExist) {
                    Context context = getApplicationContext();
                    CharSequence text = "Login succeffuly";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent intent = new Intent(login_inferm.this, index_patient.class);
                    intent.putExtra("nom", edtUsername.getText().toString());
                    startActivity(intent);
                } else {

                    edtPassword.setText(null);
                    Toast.makeText(login_inferm.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}