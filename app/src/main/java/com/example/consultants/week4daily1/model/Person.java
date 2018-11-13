package com.example.consultants.week4daily1.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    public static final String PERSON_LIST = "personList";

    private Bitmap image;
    private String name;
    private String age;
    private String gender;
    private String country;

    public Person(Bitmap image, String name, String age, String gender, String country) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        image.writeToParcel(dest, 0);
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(gender);
        dest.writeString(country);
    }

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    // "De-parcel object
    public Person(Parcel in) {
        image = Bitmap.CREATOR.createFromParcel(in);
        name = in.readString();
        age = in.readString();
        gender = in.readString();
        country = in.readString();
    }
}


