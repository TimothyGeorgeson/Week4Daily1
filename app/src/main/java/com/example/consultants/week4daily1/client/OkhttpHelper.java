package com.example.consultants.week4daily1.client;

import android.util.Log;

import com.example.consultants.week4daily1.utils.NetworkHelper;
import com.example.consultants.week4daily1.utils.RandomParser;
import com.example.consultants.week4daily1.utils.SearchAsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpHelper {

    public static final String TAG = OkhttpHelper.class.getSimpleName() + "_TAG";
    OkHttpClient client;

    public OkhttpHelper() {
        client = new OkHttpClient();
    }

    public void execute() {

        final Request request = new Request.Builder()
                .url(NetworkHelper.RANDOM_USER_URL)
                .build();

        SearchAsyncTask asyncTask = new SearchAsyncTask(client, request);
        asyncTask.execute();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String response = client.newCall(request).execute().body().string();
//                    Log.d(TAG, "execute: " + RandomParser.parseName(response));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }
}
