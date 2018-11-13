package com.example.consultants.week4daily1.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

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
            Log.d(TAG, "execute: " + RandomParser.parseName(response));
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
