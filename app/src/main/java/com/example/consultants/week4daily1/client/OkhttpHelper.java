package com.example.consultants.week4daily1.client;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.consultants.week4daily1.utils.NetworkHelper;
import com.example.consultants.week4daily1.utils.SearchAsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpHelper {

    Context context;
    RecyclerView rvList;
    OkHttpClient client;

    public OkhttpHelper(Context context, RecyclerView rvList) {
        this.context = context;
        this.rvList = rvList;
        client = new OkHttpClient();
    }

    public void execute() {

        final Request request = new Request.Builder()
                .url(NetworkHelper.RANDOM_USER_URL)
                .build();

        SearchAsyncTask asyncTask = new SearchAsyncTask(client, request, context, rvList);
        asyncTask.execute();
    }
}
