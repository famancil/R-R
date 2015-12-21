package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by famancil on 20-12-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static DatabaseHelper sInstance;

    private static final String TAG="DatabaseHelper";
    private static final String DATABASE_NAME = "RRB.db";

    //Tabla Actividad
    private static final String TABLE_ACTIVITIES = "activities";
    private static final String COLUMN_ACTIVITY_ID = "_id";
    private static final String COLUMN_ACTIVITY_NAME = "name";
    private static final String COLUMN_ACTIVITY_OK = "ok";
    private static final String COLUMN_ACTIVITY_FINAL = "final";


    //Tabla Prioridad
    private static final String TABLE_PRIORITIES = "priorities";
    private static final String COLUMN_PRIORITY_ID = COLUMN_ACTIVITY_ID;
    private static final String COLUMN_PRIORITY_GRADE = "grade";
    private static final String COLUMN_PRIORITY_ACTIVITY_ID = "activity_id";

    String SQL_String_CREATE_TABLE_ACTIVITY= "CREATE TABLE " + TABLE_ACTIVITIES + "(" +
            COLUMN_ACTIVITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_ACTIVITY_NAME + " TEXT NOT NULL," +
            COLUMN_ACTIVITY_OK + " INTEGER NOT NULL," +
            COLUMN_ACTIVITY_FINAL + " INTEGER NOT NULL" + ")";

    String SQL_String_CREATE_TABLE_PRIORITY = "CREATE TABLE " + TABLE_PRIORITIES + "(" +
            COLUMN_PRIORITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_PRIORITY_GRADE + " TEXT NOT NULL," +
            COLUMN_PRIORITY_ACTIVITY_ID + " INTEGER NOT NULL"
            + ")";

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
        SQLiteDatabase db = this.getWritableDatabase();
        //context.deleteDatabase(DATABASE_NAME);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_String_CREATE_TABLE_ACTIVITY);
        db.execSQL(SQL_String_CREATE_TABLE_PRIORITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIORITIES);

        // recreate the tables
        onCreate(db);
    }
    public boolean insertAct(String name,String ok, String finale)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACTIVITY_NAME, name);
        contentValues.put(COLUMN_ACTIVITY_OK, ok);
        contentValues.put(COLUMN_ACTIVITY_FINAL, finale);
        long result = db.insert(TABLE_ACTIVITIES,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertPri(String grade,String activity_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRIORITY_GRADE, grade);
        contentValues.put(COLUMN_PRIORITY_ACTIVITY_ID, activity_id);
        long result = db.insert(TABLE_PRIORITIES,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllDataAct(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_ACTIVITIES,null);
        return res;
    }
    public Cursor getAllDataPrio(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_PRIORITIES,null);
        return res;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_ACTIVITIES,"ID = ?",new String[]{id});
    }
    public void dropTable(){
        SQLiteDatabase db = sInstance.getWritableDatabase(); //get database
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + sInstance.TABLE_ACTIVITIES + "'");
        db.execSQL("DELETE FROM " + sInstance.TABLE_ACTIVITIES); //delete all rows in a table
        db.close();
    }
    public Cursor findData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ACTIVITIES + " where " + COLUMN_PRIORITY_ID + "='" + id + "'" , null);
        return res;
    }


}
