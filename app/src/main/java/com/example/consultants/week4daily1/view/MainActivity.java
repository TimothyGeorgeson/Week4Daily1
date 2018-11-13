package com.example.consultants.week4daily1.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.consultants.week4daily1.R;
import com.example.consultants.week4daily1.client.OkhttpHelper;
import com.example.consultants.week4daily1.model.Person;
import com.example.consultants.week4daily1.utils.PaginationScrollListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    RecyclerView rvPersonList;
    RecyclerViewAdapter adapter;
    LinearLayoutManager layoutManager;
    ProgressBar progressBar;

    private MyBroadcastReceiver myBroadcastReceiver;
    private OkhttpHelper okhttpHelper;
    private Spinner spGender;
    private Spinner spNationality;
    String gender;
    String country;

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPersonList = findViewById(R.id.rvPersonList);
        progressBar = findViewById(R.id.pbProgress);

        okhttpHelper = new OkhttpHelper(getApplicationContext());

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
                gender = (String) adapterView.getItemAtPosition(position);
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
                country = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Person.PERSON_LIST);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

    public void searchUsers(View view) {
        progressBar.setVisibility(View.VISIBLE);

        ArrayList<Person> personList = new ArrayList<>();
        okhttpHelper.execute(gender, country, personList);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final ArrayList<Person> personList = intent.getParcelableArrayListExtra(Person.PERSON_LIST);

            adapter = new RecyclerViewAdapter(personList);
            layoutManager = new LinearLayoutManager(context);
            rvPersonList.setAdapter(adapter);
            rvPersonList.setLayoutManager(layoutManager);

            rvPersonList.addOnScrollListener(new PaginationScrollListener(layoutManager) {
                @Override
                protected void loadMoreItems() {
                    isLoading = true;
                    currentPage += 1; //Increment page index to load the next one
                    loadNextPage(personList);
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });

        }
    }

    private void loadNextPage(ArrayList<Person> personList) {

        //when scroll position is at bottom of list
        //this is called repeatedly, and crashes trying to send data so large
        //even with isLoading flags, wasn't able to figure this out yet.
        Log.d(TAG, "loadNextPage: ");
        //okhttpHelper.execute(gender, country, personList);

        isLoading = false;
    }

}
