package com.example.myapplication;

import android.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name_txt, lname_txt, email_txt, tel_txt,birthday_txt;
    ArrayList<Persona>sarasas = new ArrayList<Persona>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_txt = findViewById(R.id.name_txt);
        lname_txt = findViewById(R.id.lname_txt);
        email_txt = findViewById(R.id.email_txt);
        tel_txt = findViewById(R.id.tel_txt);
        birthday_txt = findViewById(R.id.birthday_txt);

    }
}
