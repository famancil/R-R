package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by famancil on 20-12-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="rr.db";
    public static final String TABLE_NAME="priories_table";
    public static final String COL_1="ID";
    public static final String COL_2="ACTIVITY";
    public static final String COL_3="GRADE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,ACTIVITY TEXT,GRADE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String activity,String grade)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,activity);
        contentValues.put(COL_3,grade);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
}
