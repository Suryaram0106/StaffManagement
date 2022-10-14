package com.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_Staffs = "StaffDetails";

    public DBHelper(Context context ) {
        super(context,"UserData",null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table StaffDetails(emailID TEXT primary key,name TEXT,password PASSWORD,dob TEXT,number NUMBER,address TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists StaffDetails");
    }
    public Boolean insetStaffData(String emailID,String name,String password, String dob,String number, String address){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("emailID",emailID);
        contentValues.put("name",name);
        contentValues.put("password",password);
        contentValues.put("dob",dob);
        contentValues.put("number",number);
        contentValues.put("address",address);
        long result= DB.insert("StaffDetails",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }



    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetStaffNames(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from StaffDetails ",null);
        //String sur="sur";
        //db.execSQL("DELETE FROM StaffDetails" + " WHERE " + "name" + "= '" + sur + "'");
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex("name")));
            userList.add(user);
        }
        return  userList;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from StaffDetails ",null);
        return cursor;
    }
}