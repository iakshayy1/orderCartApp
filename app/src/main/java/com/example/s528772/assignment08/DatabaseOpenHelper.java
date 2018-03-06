package com.example.s528772.assignment08;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by s528772 on 11/9/2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public DatabaseOpenHelper(Context context,Integer integer) {
        super(context, "orderDetailsDB", null, 1);
    }
    private static final String createOrdersDB = "create table orders(" + "_id2 integer primary key autoincrement," +
            "Person_Name text not null," +
            "Cookie_Type text not null," +
            "Boxes_Count integer," +
            "Total_Price integer)";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(createOrdersDB);
        } catch (Exception e){
            Log.d("cookieDB",e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("Drop table if exists movies");
        sqLiteDatabase.execSQL("Drop table if exists orders");

        onCreate(sqLiteDatabase);
    }

}
