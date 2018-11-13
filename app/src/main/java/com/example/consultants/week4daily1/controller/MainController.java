package com.example.consultants.week4daily1.controller;

import android.graphics.Bitmap;

import com.example.consultants.week4daily1.model.Person;

import java.util.ArrayList;

public class MainController {

    public static MainController instance;
    private ArrayList<Person> personList;

    private MainController()
    {
        this.personList = new ArrayList<Person>();
    }

    public static MainController getInstance()
    {
        if(instance == null)
        {
            instance = new MainController();
        }
        return instance;
    }

    public void addPerson(Bitmap image, String name, String age, String gender, String country)
    {
        Person person = new Person(image, name, age, gender, country);
        personList.add(person);
    }

    public void clearList()
    {
        personList.clear();
    }

    public ArrayList<Person> getPersonList()
    {
        return this.personList;
    }
}
