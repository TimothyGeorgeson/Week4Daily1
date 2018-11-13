package com.example.consultants.week4daily1.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.consultants.week4daily1.model.Person;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SearchAsyncTask extends AsyncTask {
    public static final String TAG = SearchAsyncTask.class.getSimpleName() + "_TAG";

    OkHttpClient client;
    Request request;

    public SearchAsyncTask(OkHttpClient client, Request request)
    {
        this.client = client;
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        Log.d(TAG, "doInBackground: ");
        try {
            String response = client.newCall(request).execute().body().string();

            ArrayList<Person> personList = RandomParser.generatePersons(response);

            for (int i = 0; i < personList.size(); i++) {
                Log.d(TAG, "execute: " + personList.get(i).toString());
            }

            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}
