package com.example.consultants.week4daily1.client;

import android.content.Context;

import com.example.consultants.week4daily1.utils.DisplayUtil;
import com.example.consultants.week4daily1.utils.NetworkHelper;
import com.example.consultants.week4daily1.utils.SearchAsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpHelper {
    public static final String TAG = OkhttpHelper.class.getSimpleName() + "_TAG";

    OkHttpClient client;
    Context context;

    public OkhttpHelper(Context context) {
        client = new OkHttpClient();
        this.context = context;
    }

    public void execute(String gender, String country) {

        String URL = NetworkHelper.RANDOM_USER_URL + "?results=20&gender=" + gender.toLowerCase() +
                "&nat=" + DisplayUtil.countryToCode(country);

        final Request request = new Request.Builder()
                .url(URL)
                .build();

        SearchAsyncTask asyncTask = new SearchAsyncTask(client, request, context);
        asyncTask.execute();
    }
}
