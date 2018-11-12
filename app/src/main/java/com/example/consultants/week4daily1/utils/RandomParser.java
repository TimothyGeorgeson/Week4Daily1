package com.example.consultants.week4daily1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RandomParser {

    private static String title;
    private static String first;
    private static String last;

    public static String parseName(String responseStr) {

        JSONObject response = null;
        try {
            response = new JSONObject(responseStr);
            JSONArray results = response.getJSONArray("results");
            JSONObject user = (JSONObject) results.get(0);
            JSONObject name = user.getJSONObject("name");
            title = name.getString("title");
            first = name.getString("first");
            last = name.getString("last");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return title + " " + first + " " + last;
    }
}
