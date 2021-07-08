package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.androidproject.Classes.Infermier;
import com.example.androidproject.Classes.Medecin;
import com.example.androidproject.Classes.Patient;
import com.example.androidproject.Database.DatabaseHelper;
import com.example.androidproject.Validations.AdminAdapter;
import com.example.androidproject.Validations.MedecinAdapter;

import java.util.ArrayList;
import java.util.List;

public class login_admin extends AppCompatActivity {


        private RecyclerView recyclerView;
        private AdminAdapter adminAdapter;
        private SearchView searchView;
        private ArrayList<Patient> patients;
        DatabaseHelper db = new DatabaseHelper(this);

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_admin);
            DatabaseHelper db = new  DatabaseHelper(this);
            Button btnLogin;
            EditText edtUsername;
            EditText edtPassword;
            btnLogin = (Button) findViewById(R.id.Btn_LoginAdmi);
            edtUsername = (EditText) findViewById(R.id.TxtUsernameAdm);
            edtPassword = (EditText) findViewById(R.id.TxtPasswordAdm);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isExist = db.checkAdmin(edtUsername.getText().toString(), edtPassword.getText().toString());
                    Log.d("Reading: ", "Reading all contacts..");

                    if (isExist) {
                        Context context = getApplicationContext();
                        CharSequence text = "Login succeffuly";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent intent = new Intent(login_admin.this, index_admin.class);
                        intent.putExtra("email", edtUsername.getText().toString());
                        startActivity(intent);
                    } else {

                        edtPassword.setText(null);
                        Toast.makeText(login_admin.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }


}