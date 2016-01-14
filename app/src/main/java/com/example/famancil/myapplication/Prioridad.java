package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by famancil on 26-12-15.
 */
public class Prioridad {

    public static boolean InsertarPrioridad(DataBaseHelper DBUsuario,float grado,String activity_id) {
        SQLiteDatabase db=DBUsuario.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cons.ACTIVIDAD_ID_PRIORIDAD, activity_id);
        contentValues.put(Cons.GRADO, grado);
        long result = db.insert(Cons.PRIORIDAD,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public static Cursor BuscarPrioridad(DataBaseHelper DBUsuario,String id) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Cons.PRIORIDAD + " where " + Cons.PRIORIDAD_ID + "='" + id + "'" , null);
        return res;
    }

    public static Cursor BuscarPrioridadPorActividad(DataBaseHelper DBUsuario,String activity_id) {
        SQLiteDatabase db = DBUsuario.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Cons.PRIORIDAD + " where " + Cons.ACTIVIDAD_ID_PRIORIDAD + "='" + activity_id + "'" , null);
        return res;
    }

    public static Cursor BuscarTodasPrioridades(DataBaseHelper DBUsuario) {
        SQLiteDatabase db = DBUsuario.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Cons.PRIORIDAD, null);
        return res;
    }

    public static boolean ActualizarPrioridad(DataBaseHelper DBUsuario,String id,float grado,String activity_id) {
        SQLiteDatabase db=DBUsuario.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cons.ACTIVIDAD_ID_PRIORIDAD, activity_id);
        contentValues.put(Cons.GRADO, grado);
        long result = db.update(Cons.PRIORIDAD,contentValues,"ActividadId="+activity_id,null);
        if(result==-1)
            return false;
        else
            return true;
    }

    public static Integer BorrarPrioridad(DataBaseHelper DBUsuario,String id) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        return db.delete(Cons.PRIORIDAD,"ID = ?",new String[]{id});
    }
}