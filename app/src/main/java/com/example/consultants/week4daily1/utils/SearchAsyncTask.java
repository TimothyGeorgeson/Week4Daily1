package com.example.consultants.week4daily1.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.consultants.week4daily1.model.Person;
import com.example.consultants.week4daily1.view.RecyclerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SearchAsyncTask extends AsyncTask<String, Integer, ArrayList<Person>> {
    public static final String TAG = SearchAsyncTask.class.getSimpleName() + "_TAG";

    RecyclerView rvPersonList;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    OkHttpClient client;
    Request request;
    Context context;

    public SearchAsyncTask(OkHttpClient client, Request request, Context context, RecyclerView rvPersonList)
    {
        this.client = client;
        this.request = request;
        this.context = context;
        this.rvPersonList = rvPersonList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }

    @Override
    protected ArrayList<Person> doInBackground(String... strings) {

        Log.d(TAG, "doInBackground: ");
        try {
            String response = client.newCall(request).execute().body().string();

            return RandomParser.generatePersons(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Person> personList) {
        super.onPostExecute(personList);

        adapter = new RecyclerViewAdapter(personList);
        layoutManager = new LinearLayoutManager(context);
        rvPersonList.setAdapter(adapter);
        rvPersonList.setLayoutManager(layoutManager);

        for (int i = 0; i < personList.size(); i++) {
            Log.d(TAG, "PostExecute: " + personList.get(i).toString());
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
