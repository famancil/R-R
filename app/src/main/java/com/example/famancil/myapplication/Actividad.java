package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Actividad {

    private int Id;
    private int horario;
    private String Nombre;
    private String Inicio;
    private String Termino;
    private boolean Cumplido;
    private boolean Invariable;

    public Actividad(int horario) {
        Nombre = "";
        this.horario = horario;
    }

    public Actividad(int Id, String Nombre, String Inicio, String Termino, boolean Cumplido, boolean Invariable)
    {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Inicio = Inicio;
        this.Termino = Termino;
        this.Cumplido = Cumplido;
        this.Invariable = Invariable;
    }

    public Actividad(String Nombre, String Inicio, String Termino, boolean Cumplido, boolean Invariable)
    {
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

    public static int BuscarActividadIdUltimo(DataBaseHelper DBActividad) {
        Cursor actividades = BuscarTodosActividades(DBActividad);
        actividades.moveToLast();
        return actividades.getInt(0);
    }

    public static Actividad BuscarActividades(DataBaseHelper DBActividad, int ActividadId){
        SQLiteDatabase db = DBActividad.getReadableDatabase();
        String[] campos = new String[]{Cons.ACTIVIDAD_ID, Cons.NOMBRE_ACTIVIDAD, Cons.INICIO, Cons.TERMINO, Cons.CUMPLIDO, Cons.INVARIABLE};
        String[] args = new String[]{Integer.toString(ActividadId)};

        Cursor Activy = db.query(Cons.ACTIVIDAD, campos, Cons.ACTIVIDAD_ID + "=?", args, null, null, null);

        Actividad Actividad = null;
        if (Activy.moveToFirst()) {
            boolean cumplido;
            boolean invariable;
            do {
                cumplido = Boolean.getBoolean(Activy.getString(4));
                invariable = Boolean.getBoolean(Activy.getString(5));
                Actividad = new Actividad(Activy.getInt(0), Activy.getString(1), Activy.getString(2), Activy.getString(3), cumplido, invariable);
            } while (Activy.moveToNext());
        }
        return Actividad;
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

    public int getHorario() { return this.horario; }

    public int getInicio() { return Integer.parseInt(Inicio); }

    public void setHorario(int horario) { this.horario = horario; }

    public String getNombre() { return this.Nombre; }

}
