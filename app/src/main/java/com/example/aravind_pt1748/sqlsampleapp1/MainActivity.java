package com.example.aravind_pt1748.sqlsampleapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteHelperContacts db = new SQLiteHelperContacts(this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.insertContact(new Contacts("Ravi", "9100000000"));
        db.insertContact(new Contacts("Srinivas", "9199999999"));
        db.insertContact(new Contacts("Tommy", "9522222222"));
        db.insertContact(new Contacts("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contacts> contacts = db.selectAllContacts();

        for (Contacts cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}