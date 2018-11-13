package com.example.consultants.week4daily1.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.consultants.week4daily1.controller.MainController;
import com.example.consultants.week4daily1.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class RandomParser {

    public static ArrayList<Person> generatePersons(String responseStr) {

        JSONObject response = null;
        JSONObject user = null;
        JSONObject picture = null;
        JSONObject name = null;
        JSONObject dob = null;

        String title = "";
        String first = "";
        String last = "";
        String personName = "";
        String age = "";
        String gender = "";
        String country = "";

        try {
            response = new JSONObject(responseStr);
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                user = (JSONObject) results.get(i);

                //get picture URL and convert to bitmap
                picture = user.getJSONObject("picture");
                String urlPicture = picture.getString("medium");
                Bitmap bmImage = null;
                try {
                    InputStream in = new java.net.URL(urlPicture).openStream();
                    bmImage = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

                //get name, and convert to camel case
                name = user.getJSONObject("name");
                title = name.getString("title");
                first = name.getString("first");
                last = name.getString("last");
                personName = DisplayUtil.convertToCamelCase(title + " " + first + " " + last);

                //get age
                dob = user.getJSONObject("dob");
                age = dob.getString("age");

                //get gender
                gender = DisplayUtil.convertToCamelCase(user.getString("gender"));

                //get country
                country = DisplayUtil.codeToCountry(user.getString("nat"));

                //add person to personList in controller
                MainController.getInstance().addPerson(bmImage, personName, age, gender, country);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return MainController.getInstance().getPersonList();
    }
}
