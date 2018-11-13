package com.example.consultants.week4daily1.utils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
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
    ArrayList<Person> personList;

    public SearchAsyncTask(OkHttpClient client, Request request, Context context, ArrayList<Person> personList)
    {
        this.client = client;
        this.request = request;
        this.context = context;
        this.personList = personList;
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

            return RandomParser.generatePersons(response, personList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Person> personList) {
        super.onPostExecute(personList);

        Intent intent = new Intent();
        intent.setAction(Person.PERSON_LIST);
        intent.putParcelableArrayListExtra(Person.PERSON_LIST, personList);
        context.sendBroadcast(intent);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
