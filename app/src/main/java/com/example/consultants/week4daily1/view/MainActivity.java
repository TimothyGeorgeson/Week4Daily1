package com.example.consultants.week4daily1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.consultants.week4daily1.R;
import com.example.consultants.week4daily1.client.OkhttpHelper;

public class MainActivity extends AppCompatActivity {

    private OkhttpHelper okhttpHelper;
    RecyclerView rvPersonList;
    private Spinner spGender;
    private Spinner spNationality;
    String gender;
    String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPersonList = findViewById(R.id.rvPersonList);

        okhttpHelper = new OkhttpHelper(getApplicationContext(), rvPersonList);

        //set up spinner for genders
        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> adapterGen = ArrayAdapter.createFromResource(this,
                R.array.genders_array, android.R.layout.simple_spinner_item);
        adapterGen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(adapterGen);

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                gender = (String)adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set up spinner for nationalities
        spNationality = (Spinner) findViewById(R.id.spNationality);
        ArrayAdapter<CharSequence> adapterNat = ArrayAdapter.createFromResource(this,
                R.array.nationalities_array, android.R.layout.simple_spinner_item);
        adapterNat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNationality.setAdapter(adapterNat);

        spNationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                country = (String)adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void searchUsers(View view) {

        okhttpHelper.execute(gender, country);
    }
}
