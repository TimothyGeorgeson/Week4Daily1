package com.example.consultants.week4daily1.client;

import com.example.consultants.week4daily1.utils.NetworkHelper;
import com.example.consultants.week4daily1.utils.SearchAsyncTask;

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

        SearchAsyncTask asyncTask = new SearchAsyncTask(client, request);
        asyncTask.execute();
    }
}
