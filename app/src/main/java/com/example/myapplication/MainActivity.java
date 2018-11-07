package com.example.myapplication;

import android.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name_txt, lname_txt, email_txt, tel_txt,birthday_txt;
    ArrayList<Persona>personList = new ArrayList<Persona>();
    int i = 0;
    TextView i_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_txt = findViewById(R.id.name_txt);
        lname_txt = findViewById(R.id.lname_txt);
        email_txt = findViewById(R.id.email_txt);
        tel_txt = findViewById(R.id.tel_txt);
        birthday_txt = findViewById(R.id.birthday_txt);
        i_id = findViewById(R.id.i_id);

    }

    void savePerson(View v) {
        addPersonToList();
        clearForm();
    }
    void editPerson(View v) { editPersonRecord(); }

    void showPrevPerson(View v) {
        i_id.setText(Integer.toString(i));
        if (personList.size() > 0) {
            if (i > 0) {
                showList(i);
                i--;
            } else if(i == 0) {
                showList(i);
            }
        }
    }

    void showNextPerson(View v) {
        if (personList.size() > 0) {
            if (i < personList.size()) {
                showList(i);
                i++;
            }

        }
        i_id.setText(Integer.toString(i));
    }

    void addPersonToList() {
        String Pname = name_txt.getText().toString().trim();
        String Plname = lname_txt.getText().toString().trim();
        String Pemail = email_txt.getText().toString().trim();
        String Ptel = tel_txt.getText().toString().trim();
        String Pbirthday = birthday_txt.getText().toString().trim();

        personList.add(new Persona(Pname, Plname, Pemail, Ptel, Pbirthday));
    }

    void editPersonRecord() {
        Persona redaguojamas = personList.get(i);
        redaguojamas.setName(name_txt.getText().toString().trim());
        redaguojamas.setLname(lname_txt.getText().toString().trim());
        redaguojamas.setEmail(email_txt.getText().toString().trim());
        redaguojamas.setTel(tel_txt.getText().toString().trim());
        redaguojamas.setBirthday(birthday_txt.getText().toString().trim());
        personList.set(i, redaguojamas);
    }

    void showList(int i) {
        Persona asmuoRodymui= personList.get(i);
        name_txt.setText(asmuoRodymui.getName());
        lname_txt.setText(asmuoRodymui.getLname());
        email_txt.setText(asmuoRodymui.getEmail());
        tel_txt.setText(asmuoRodymui.getTel());
        birthday_txt.setText(asmuoRodymui.getBirthday());
    }

    void clearForm() {
        name_txt.setText("");
        lname_txt.setText("");
        email_txt.setText("");
        tel_txt.setText("");
        birthday_txt.setText("");
    }

}
