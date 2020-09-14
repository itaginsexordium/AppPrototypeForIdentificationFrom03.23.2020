package com.kg.myapplication;

import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageButton;

import java.net.URI;

public class UserInformationCase {


    private String number_indefication_card;
    private String date_give_card;
    private String end_of_card;
    private String MKK;
    private String familyi;
    private String name;
    private String secondName;
    private String dateBorn;
    private String bornPlace;
    private String city;
    private String region;
    private String street;
    private String house_and_apartment;


    //photo for identification
   private String imageURIFirst;
   private String imageURISecond;

    public UserInformationCase(){}

    public  UserInformationCase (
                         String number_indefication_card,
                         String date_give_card,
                         String end_of_card,
                         String MKK,
                         String familyi,
                         String name,
                         String secondName,
                         String dateBorn,
                         String bornPlace,
                         String city,
                         String region,
                         String street,
                         String house_and_apartment,
                         String imageURIFirst,
                         String imageURISecond
    ){
        this.number_indefication_card=number_indefication_card;
        this.date_give_card=date_give_card;
        this.end_of_card=end_of_card;
        this.MKK=MKK;
        this.familyi=familyi;
        this.name=name;
        this.secondName=secondName;
        this.dateBorn=dateBorn;
        this.bornPlace=bornPlace;
        this.city=city;
        this.region=region;
        this.street=street;
        this.house_and_apartment=house_and_apartment;
        this.imageURIFirst=imageURIFirst;
        this.imageURISecond=imageURISecond;
    }




    public String getNumber_indefication_card() {
        return number_indefication_card;
    }

    public void setNumber_indefication_card(String number_indefication_card) {
        this.number_indefication_card = number_indefication_card;
    }
    public String getDate_give_card() {
        return date_give_card;
    }

    public void setDate_give_card(String date_give_card) {
        this.date_give_card = date_give_card;
    }

    public String getEnd_of_card() {
        return end_of_card;
    }

    public void setEnd_of_card(String end_of_card) {
        this.end_of_card = end_of_card;
    }

    public String getMKK() {
        return MKK;
    }

    public void setMKK(String MKK) {
        this.MKK = MKK;
    }

    public String getFamilyi() {
        return familyi;
    }

    public void setFamilyi(String familyi) {
        this.familyi = familyi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(String dateBorn) {
        this.dateBorn = dateBorn;
    }

    public String getBornPlace() {
        return bornPlace;
    }

    public void setBornPlace(String bornPlace) {
        this.bornPlace = bornPlace;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse_and_apartment() {
        return house_and_apartment;
    }

    public void setHouse_and_apartment(String house_and_apartment) {
        this.house_and_apartment = house_and_apartment;
    }

    public String getImageURIFirst() {
        return imageURIFirst;
    }

    public void setImageURIFirst(String imageURIFirst) {
        this.imageURIFirst = imageURIFirst;
    }

    public String getImageURISecond() {
        return imageURISecond;
    }

    public void setImageURISecond(String imageURISecond) {
        this.imageURISecond = imageURISecond;
    }
}
