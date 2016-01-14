package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DataBaseHelper extends SQLiteOpenHelper{
    private static DataBaseHelper sInstance;

    private static final String TAG="DataBaseHelper";
    private static final String DATABASE_NAME = "RRB.db";

    //Tabla Actividad
    /*private static final String TABLE_ACTIVITIES = "activities";
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
            + ")";*/

    private DataBaseHelper(Context context) {
        super(context, Cons.DATABASE_NAME, null,11);
        SQLiteDatabase db = this.getWritableDatabase();
        //context.deleteDatabase(DATABASE_NAME);
    }

    //Constructor Martín, a solo para no generar conflicto.
    /*public DataBaseHelper(Context context, int a) {
        super(context, Cons.DATABASE_NAME, null, 4);
    }*/

    public static synchronized DataBaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_String_CREATE_TABLE_ACTIVITY);
        //db.execSQL(SQL_String_CREATE_TABLE_PRIORITY);

        //Creación Tablas Martín.
        db.execSQL("CREATE TABLE " + Cons.USUARIO + " ( " +
                Cons.USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Cons.NOMBRE_USUARIO + " INTEGER, " +
                Cons.EMAIL + " TEXT, " +
                Cons.UNIVERSIDAD + " TEXT, " +
                Cons.ESTADISTICAS_USUARIO + " INTEGER) ");
        db.execSQL("CREATE TABLE " + Cons.HORARIO + " ( " +
                Cons.HORARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Cons.DIA + " INTEGER, " +
                Cons.FECHA + " DATE);");
        db.execSQL("CREATE TABLE " + Cons.ACTIVIDAD + " (" +
                Cons.ACTIVIDAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Cons.NOMBRE_ACTIVIDAD + " TEXT NOT NULL UNIQUE, " +
                Cons.INICIO + " TEXT NOT NULL, " +
                Cons.TERMINO + " TEXT NOT NULL, " +
                Cons.CUMPLIDO + " NUMERIC, " +
                Cons.INVARIABLE + " NUMERIC); ");
        db.execSQL("CREATE TABLE " + Cons.PRIORIDAD + " ( " +
                Cons.PRIORIDAD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Cons.ACTIVIDAD_ID_PRIORIDAD + " INTEGER NOT NULL UNIQUE, " +
                Cons.GRADO + " FLOAT NOT NULL, " +
                "FOREIGN KEY ("+ Cons.ACTIVIDAD_ID_PRIORIDAD + ") REFERENCES " + Cons.ACTIVIDAD +"(" + Cons.ACTIVIDAD_ID + "));");
        db.execSQL("CREATE TABLE " + Cons.ACTIVIDADHORARIO + " ( " +
                Cons.HORARIO_ID_ACTIVIDADHORARIO + " INTEGER NOT NULL, " +
                Cons.ACTIVIDAD_ID_ACTIVIDADHORARIO + " INTEGER NOT NULL, " +
                "PRIMARY KEY (" +Cons.HORARIO_ID_ACTIVIDADHORARIO+ ", " + Cons.ACTIVIDAD_ID_ACTIVIDADHORARIO+ ") " +
                "FOREIGN KEY ("+ Cons.HORARIO_ID_ACTIVIDADHORARIO + ") REFERENCES " + Cons.HORARIO +"(" + Cons.HORARIO_ID + ") " +
                "FOREIGN KEY ("+ Cons.ACTIVIDAD_ID_ACTIVIDADHORARIO + ") REFERENCES " + Cons.ACTIVIDAD +"(" + Cons.ACTIVIDAD_ID + ")); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES );
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIORITIES);

        //Update Tablas
        db.execSQL("DROP TABLE IF EXISTS " + Cons.USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + Cons.HORARIO);
        db.execSQL("DROP TABLE IF EXISTS " + Cons.ACTIVIDAD);
        db.execSQL("DROP TABLE IF EXISTS " + Cons.PRIORIDAD);
        db.execSQL("DROP TABLE IF EXISTS " + Cons.ACTIVIDADHORARIO);

        // recreate the tables
        onCreate(db);
    }
    /*public boolean insertAct(String name,String ok, String finale)
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
        contentValues.put(Cons.GRADO, grade);
        contentValues.put(Cons.ACTIVIDAD_ID_PRIORIDAD, activity_id);
        long result = db.insert(Cons.PRIORIDAD,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }*/
    /*public Cursor getAllDataAct(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_ACTIVITIES,null);
        return res;
    }
    public Cursor getAllDataPrio(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Cons.PRIORIDAD,null);
        return res;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_ACTIVITIES,"ID = ?",new String[]{id});
    }*/
    public void dropTable(){
        SQLiteDatabase db = sInstance.getWritableDatabase(); //get database
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + Cons.ACTIVIDAD + "'");
        db.execSQL("DELETE FROM " + Cons.ACTIVIDAD); //delete all rows in a table
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + Cons.PRIORIDAD + "'");
        db.execSQL("DELETE FROM " + Cons.PRIORIDAD); //delete all rows in a table
        db.close();
    }

    public void deleteTableActivity()
    {
        SQLiteDatabase db = sInstance.getWritableDatabase();
        db.delete(Cons.ACTIVIDAD, null, null);
        db.delete(Cons.ACTIVIDADHORARIO, null, null);
    }
    /*public Cursor findData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ACTIVITIES + " where " + COLUMN_PRIORITY_ID + "='" + id + "'" , null);
        return res;
    }*/


}
