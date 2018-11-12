package com.example.consultants.week4daily1.client;

import com.example.consultants.week4daily1.utils.NetworkHelper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpHelper {

    OkHttpClient client;

    public OkhttpHelper() {
        client = new OkHttpClient();
    }

    public void execute() {

        final Request request = new Request.Builder()
                .url(NetworkHelper.RANDOM_USER_URL)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = client.newCall(request).execute().body().string();
                    //Log.d(TAG, "execute: " + RandomParser.parseName(response));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
