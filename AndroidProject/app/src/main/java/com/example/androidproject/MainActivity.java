package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.classes.Personne;
import com.example.androidproject.database.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db = new DatabaseHelper(this);
        Personne p = new Personne(1, "sabri", "sabri.fetoui@gmail.com", "22125142", "04/02/1996", "Test123", "user");
        Personne p1 = new Personne(2, "sabri", "sabri.fetoui@ooredoo.tn", "22125142", "04/02/1996", "Test123", "user");
        db.addpersonne(p);

/*
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addpersonne(new Personne(1,"sabri","sabri.fetoui@gmail.com","22125142","04/02/1996","Test123","user"));
        db.addpersonne(new Personne(2,"test","sabri.fetoui@ooredoo.tn","22125142","04/02/1996","Test123","user336"));
        //db.addContact(new Contact("Tommy", "9522222222"));
        //db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Personne> personneList = db.getAllpersonne();

        for (Personne cn : personneList) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhone();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}*/
   Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;


           db.addpersonne(p1);
            btnLogin = (Button) findViewById(R.id.Btn_Login);
            edtUsername = (EditText) findViewById(R.id.TxtUsername);
            edtPassword = (EditText) findViewById(R.id.TxtPassword);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isExist = db.checkpersonne(edtUsername.getText().toString(), edtPassword.getText().toString());
                    Log.d("Reading: ", "Reading all contacts..");
                    List<Personne> personneList = db.getAllpersonne();

                    for (Personne cn : personneList) {
                        String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " +
                                cn.getPhone();
                        // Writing Contacts to log
                        Log.d("Name: ", log);}
                    if (isExist) {
                        Context context = getApplicationContext();
                        CharSequence text = "Login succeffuly";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent intent = new Intent(MainActivity.this, Welcome.class);
                        intent.putExtra("username", edtUsername.getText().toString());
                        startActivity(intent);
                    } else {

                        edtPassword.setText(null);
                        Toast.makeText(MainActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }}
