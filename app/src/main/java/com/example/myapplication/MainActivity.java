package com.example.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Person;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int SKAMBINIMO_TEISES_KODAS=1;
    EditText name_txt, lname_txt, email_txt, tel_txt,birthday_txt;
    ArrayList<Persona>personList = new ArrayList<Persona>();
    int i = 0;
    int j = 0;
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

        i_id.setText(Integer.toString(i));
    }

    void savePerson(View v) {
        addPersonToList();
        i++;
        showi();
        clearForm();
    }
    void showi() {
        i_id.setText(Integer.toString(i));
    }
    void editPerson(View v) { editPersonRecord(); }

    void showPrevPerson(View v) {

        i_id.setText(Integer.toString(i));
        if (personList.size() > 0) {
//            if (i > 0) {
//                showList(i);
//                i--;
//            } else if(i == 0) {
//                showList(i);
//            }
            while (i < personList.size()) {
                showList(j);
                j++;
            }

        }
    }

    void showNextPerson(View v) {
        if (personList.size() > 0) {
            if (i >= 0 && i < (personList.size() - 1)) {
                showList(i);
                i++;
            } else if(i == (personList.size() - 1)) {
                showList(i);
            }

        }
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

    private void skambink() {
        if(tel_txt.getText().toString().length()>0){
            String telefonas="tel:" + tel_txt.getText().toString().trim();
            Intent skambinimas = new Intent(Intent.ACTION_DIAL);
            skambinimas.setData(Uri.parse(telefonas));
            if(skambinimas.resolveActivity(getPackageManager())!=null){
                startActivity(skambinimas);
            }else{
                //pranešimas apie įvykusią klaidą mėginant iškviesti skambinimo langą
            }
        }
    }

    public void b_callClick(View view){
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
                //galima skambinti
                skambink();
        }else{
            prasytiLeidimoSkambinti();
        }
    }

    private void prasytiLeidimoSkambinti() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
            Manifest.permission.CALL_PHONE)){
            //jei bus NEduotas leidimas parodys paaiskinima, del ko reikia siai programai tokiu teisiu
            new AlertDialog.Builder(MainActivity.this)
                .setTitle("Reikia skambinimo teisės")
                .setMessage("Kad būtų galimybė skambinti tiesiai iš programėlės")
                .setPositiveButton("Leisti", new DialogInterface.OnClickListener() {
                   @Override
                      public void onClick(DialogInterface dialog, int which) {
                          ActivityCompat.requestPermissions(MainActivity.this,
                          new String[]{Manifest.permission.CALL_PHONE}, SKAMBINIMO_TEISES_KODAS);
                          // SKAMBINIMO_TEISES_KODAS bus reikalingas teises gavimo identifikavimui
                      }
                   })
                   .setNegativeButton("Neleisti", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                      }
                   })
                   .create().show();
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,
            new String[]{Manifest.permission.CALL_PHONE}, SKAMBINIMO_TEISES_KODAS);
            // SKAMBINIMO_TEISES_KODAS bus reikalingas teises gavimo identifikavimui
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SKAMBINIMO_TEISES_KODAS)
            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Gautas leidimas", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Negauta teisės skambinti", Toast.LENGTH_SHORT).show();
            }
    }


    void clearForm() {
        name_txt.setText("");
        lname_txt.setText("");
        email_txt.setText("");
        tel_txt.setText("");
        birthday_txt.setText("");
    }

}
