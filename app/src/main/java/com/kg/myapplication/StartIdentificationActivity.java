package com.kg.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartIdentificationActivity extends AppCompatActivity {
    private EditText adPhone, adMail;
    private TextView alert;
    private Button nextButton;

    private String mail = null;
    private String phone = null;
    private Intent goNext;
    private Spinner category;

    private int payfun;

    private FirebaseDatabase database;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_indification);




        adMail = findViewById(R.id.mail);
        adPhone = findViewById(R.id.phone);
        alert = findViewById(R.id.alertText);
        nextButton = (Button) findViewById(R.id.buttonNext);
        category =(Spinner)findViewById(R.id.category);



        database= FirebaseDatabase.getInstance();



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (category.getSelectedItem().toString()){
                    case "основной":{

                       payfun=1;
                        goNext=new Intent(StartIdentificationActivity.this,PActivity.class);
                        break;
                    }
                    case "профессиональный":{
                        payfun=2;
                        goNext = new Intent(StartIdentificationActivity.this, ChekInformation.class);
                    break;
                    }
                }



                if (adMail.getText().toString().equals("") || adPhone.getText().toString().equals("")){
                    alert.setText("заполните данные");

                }else if (adMail.getText().toString().equals("")) {

                    alert.setText("Введите mail");

                }else if (adPhone.getText().toString().equals("")){
                    alert.setText("Введите номер");

                }else {
                    UsersInfo usersInfo = new UsersInfo(adMail.getText().toString(), adPhone.getText().toString());

                    phone = adPhone.getText().toString();

                    myRef = database.getReference("users").child(phone);

                    myRef.setValue(usersInfo);
                    goNext.putExtra("EXTRA_USERNAME",phone);
                    goNext.putExtra("PAYFUN",payfun);
                    startActivity(goNext);
                }

            }
        });


    }


}