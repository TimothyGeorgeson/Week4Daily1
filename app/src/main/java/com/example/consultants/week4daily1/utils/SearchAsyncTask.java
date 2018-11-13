package com.example.consultants.week4daily1.utils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.consultants.week4daily1.model.Person;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SearchAsyncTask extends AsyncTask<String, Integer, ArrayList<Person>> {
    public static final String TAG = SearchAsyncTask.class.getSimpleName() + "_TAG";

    OkHttpClient client;
    Request request;
    Context context;

    public SearchAsyncTask(OkHttpClient client, Request request, Context context)
    {
        this.client = client;
        this.request = request;
        this.context = context;
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
            //performs network call to retrieve data
            String response = client.newCall(request).execute().body().string();

            //parses response data
            return RandomParser.generatePersons(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Person> personList) {
        super.onPostExecute(personList);

        //when AsyncTask is finished, send broadcast to let MainActivity know
        Intent intent = new Intent();
        intent.setAction(Person.PERSON_LIST);
        context.sendBroadcast(intent);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
