package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Actividad {

    private Integer ActividadId;
    private String Nombre;
    private String Inicio;
    private String Termino;
    private boolean Cumplido;
    private boolean Invariable;

    public Actividad(String Nombre,String Inicio, String Termino, Boolean Cumplido, Boolean Invariable)
    {
        this.Nombre = Nombre;
        this.Inicio = Inicio;
        this.Termino = Termino;
        this.Cumplido = Cumplido;
        this.Invariable = Invariable;
    }

    public Actividad(Integer ActividadId, String Nombre,String Inicio, String Termino, Boolean Cumplido, Boolean Invariable)
    {
        this.ActividadId = ActividadId;
        this.Nombre = Nombre;
        this.Inicio = Inicio;
        this.Termino = Termino;
        this.Cumplido = Cumplido;
        this.Invariable = Invariable;
    }

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

    public boolean InsertarActividad(DataBaseHelper DBActividad)
    {
        SQLiteDatabase db = DBActividad.getWritableDatabase();
        ContentValues NuevoRegistro = new ContentValues();
        NuevoRegistro.put(Cons.NOMBRE_ACTIVIDAD, Nombre);
        NuevoRegistro.put(Cons.INICIO, Inicio);
        NuevoRegistro.put(Cons.TERMINO, Termino);
        NuevoRegistro.put(Cons.CUMPLIDO, Cumplido);
        NuevoRegistro.put(Cons.INVARIABLE, Invariable);
        return db.insert(Cons.ACTIVIDAD, null, NuevoRegistro) != -1;
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
