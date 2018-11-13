package com.example.consultants.week4daily1.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.consultants.week4daily1.R;
import com.example.consultants.week4daily1.client.OkhttpHelper;

public class MainActivity extends AppCompatActivity {

    private OkhttpHelper okhttpHelper;
    RecyclerView rvPersonList;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okhttpHelper = new OkhttpHelper();

        //set up spinner for genders
        Spinner spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> adapterGen = ArrayAdapter.createFromResource(this,
                R.array.genders_array, android.R.layout.simple_spinner_item);
        adapterGen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(adapterGen);

        //set up spinner for nationalities
        Spinner spNationality = (Spinner) findViewById(R.id.spNationality);
        ArrayAdapter<CharSequence> adapterNat = ArrayAdapter.createFromResource(this,
                R.array.nationalities_array, android.R.layout.simple_spinner_item);
        adapterNat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNationality.setAdapter(adapterNat);
    }

    public void searchUsers(View view) {

        okhttpHelper.execute();
    }
}
