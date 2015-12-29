package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Actividad {

    public static boolean InsertarActividad(DataBaseHelper DBUsuario,String nombre,String inicio, String termino, Boolean cumplido, Boolean invariable) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cons.NOMBRE_ACTIVIDAD, nombre);
        contentValues.put(Cons.INICIO,inicio);
        contentValues.put(Cons.TERMINO,termino);
        contentValues.put(Cons.CUMPLIDO,cumplido);
        contentValues.put(Cons.INVARIABLE,invariable);
        long result = db.insert(Cons.ACTIVIDAD,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public static Cursor BuscarActividad(DataBaseHelper DBUsuario,String id) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Cons.ACTIVIDAD + " where " + Cons.ACTIVIDAD_ID + "='" + id + "'" , null);
        return res;
    }

    public static Cursor BuscarTodosActividades(DataBaseHelper DBActividad) {
        SQLiteDatabase db = DBActividad.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Cons.ACTIVIDAD, null);
        return res;
    }

    public static Integer BorrarActividad(DataBaseHelper DBUsuario,String id) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        return db.delete(Cons.ACTIVIDAD,"ID = ?",new String[]{id});
    }



}
