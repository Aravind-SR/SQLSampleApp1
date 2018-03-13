package com.example.aravind_pt1748.sqlsampleapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperContacts extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="CONTACTS-BASE";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="CONTACTS";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PHONE_NUMBER = "PhoneNumber";
    private static final String KEY_ID = "id";

    public SQLiteHelperContacts(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG", "onCreate: called");
        String query = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PHONE_NUMBER + " TEXT "+ ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("TAG", "onUpgrade: called");
        final String upgradeString = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(upgradeString);
        onCreate(db);
    }

    public void insertContact(Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_ID,contact.getId());
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE_NUMBER,contact.getPhoneNumber());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public Contacts selectContacts(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Contacts contact = new Contacts(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        return contact;
    }

    public List<Contacts> selectAllContacts(){
        List<Contacts> contactsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        final String selectionString = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(selectionString,null);
        if(cursor.moveToFirst()){
            do{
                Contacts contact = new Contacts();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactsList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactsList;
    }

    public int updateContacts(Contacts contact, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE_NUMBER,contact.getPhoneNumber());
        int rows = db.update(TABLE_NAME,values,KEY_ID+"=?",new String[]{String.valueOf(id)});
        return rows;
    }

    public int deleteContacts(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public int getContactsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        final String selectionString = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(selectionString,null);
        cursor.close();
        return cursor.getCount();
    }
}
