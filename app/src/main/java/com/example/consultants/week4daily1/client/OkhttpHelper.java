package com.example.consultants.week4daily1.client;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.consultants.week4daily1.utils.DisplayUtil;
import com.example.consultants.week4daily1.utils.NetworkHelper;
import com.example.consultants.week4daily1.utils.SearchAsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpHelper {
    public static final String TAG = OkhttpHelper.class.getSimpleName() + "_TAG";

    Context context;
    RecyclerView rvList;
    OkHttpClient client;

    public OkhttpHelper(Context context, RecyclerView rvList) {
        this.context = context;
        this.rvList = rvList;
        client = new OkHttpClient();
    }

    public void execute(String gender, String country) {

        String URL = NetworkHelper.RANDOM_USER_URL + "?results=20&gender=" + gender.toLowerCase() +
                "&nat=" + DisplayUtil.countryToCode(country);

        final Request request = new Request.Builder()
                .url(URL)
                .build();

        SearchAsyncTask asyncTask = new SearchAsyncTask(client, request, context, rvList);
        asyncTask.execute();
    }
}
