package com.example.myappfirebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etInsert,etFilter;
    Button btnInsert, btnSortData,btnRefresh,btnShowDataFilter,btnHideDataFilter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    MyModelData myModelData;
    public static String PathFolder = "user";
    public static String currentUser = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInsert = findViewById(R.id.etInsert);
        etFilter = findViewById(R.id.etFilter);

        btnInsert = findViewById(R.id.btnInsert);
        btnSortData = findViewById(R.id.btnSortData);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnShowDataFilter = findViewById(R.id.btnShowDataFilter);
        btnHideDataFilter = findViewById(R.id.btnHideDataFilter);


        // Write a message to the database
        myRef = FirebaseDatabase.getInstance().getReference(PathFolder);

        MainFragment mainFragment = new MainFragment();
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().add(R.id.contentContainer, mainFragment).commit();

        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etInsert.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please insert data ",Toast.LENGTH_LONG).show();
                }else {
                    String key = myRef.push().getKey();
                    myModelData = new MyModelData(key, etInsert.getText().toString());
                    myRef.child(key).setValue(myModelData);

                    Toast.makeText(getApplicationContext(), "insert success..." + myModelData.getName(), Toast.LENGTH_LONG).show();
                    etInsert.setText("");
                }


            }
        });

        btnSortData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SortDataFragment sortDataFragment = new SortDataFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, sortDataFragment)
                        .commit();


            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragment mainFragment = new MainFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainer, mainFragment)
                        .commit();

            }
        });

        btnShowDataFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //currentUser = etFilter.getText().toString();

            }
        });

        btnHideDataFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

}
