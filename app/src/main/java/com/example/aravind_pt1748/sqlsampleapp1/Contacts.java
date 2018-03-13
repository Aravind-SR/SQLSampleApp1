package com.example.aravind_pt1748.sqlsampleapp1;

import android.content.Context;

public class Contacts {

    private int _id;
    private String _name;
    private String _phoneNumber;

    public Contacts(){

    }

    public Contacts(int id, String name, String phoneNumber){
        this._id=id;
        this._name=name;
        this._phoneNumber=phoneNumber;
    }

    public Contacts(String name, String phoneNumber){
        super();
        this._name=name;
        this._phoneNumber=phoneNumber;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this._phoneNumber = phoneNumber;
    }
}
