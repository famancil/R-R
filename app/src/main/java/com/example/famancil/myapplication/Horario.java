package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.Date;
import java.util.ArrayList;

public class Horario
{
    private int HorarioId;
    private int Dia;
    private String Fecha;
    private int InicioHorario;

    public Horario(int Dia, String Fecha)
    {
        this.Dia = Dia;
        this.Fecha = Fecha;
    }

    public Horario(int HorarioId, int Dia, String Fecha)
    {
        this.HorarioId = HorarioId;
        this.Dia = Dia;
        this.Fecha = Fecha;
    }


    public Horario RevisarHorario(DataBaseHelper DBHorario, int InicioHorario)
    {
        this.InicioHorario = InicioHorario;
        Horario hor;
        hor = BuscarHorario(DBHorario, Fecha, Dia);
        if(hor == null)
        {
            IngresarHorario(DBHorario);
            hor = BuscarHorario(DBHorario, Fecha, Dia);
        }
        hor.setInicioHorario(InicioHorario);
        return hor;
    }

    public boolean IngresarHorario(DataBaseHelper DBHorario)
    {
        SQLiteDatabase db = DBHorario.getWritableDatabase();

        ContentValues NuevoRegistro = new ContentValues();
        NuevoRegistro.put(Cons.DIA, Dia);
        NuevoRegistro.put(Cons.FECHA, Fecha);
        return db.insert(Cons.HORARIO, null, NuevoRegistro) != -1;
    }

    public ArrayList<Horario> ConsultarHorario(DataBaseHelper DBHorario, String fecha, int i) {

        int HorarioId, Dia;
        String Fecha;
        SQLiteDatabase db = DBHorario.getReadableDatabase();
        String[] campos = new String[]{Cons.HORARIO_ID, Cons.DIA, Cons.FECHA};

        Cursor Hr = db.query(Cons.HORARIO, campos, null, null, null, null, null);
        ArrayList<Horario> Horarios = new ArrayList<>();
        if (Hr.moveToFirst()) {
            do
            {
                Horario Horario = new Horario(Hr.getInt(0), Hr.getInt(1), Hr.getString(2));
                Horarios.add(Horario);
            } while (Hr.moveToNext());
        }

        return Horarios;
    }

    public static Horario BuscarHorario(DataBaseHelper DBUsuario, String Fecha, int Dia) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        Horario Horario = null;
        Cursor res = db.rawQuery("select " + Cons.HORARIO_ID + ", " + Cons.FECHA + ", " + Cons.DIA  + " from " + Cons.HORARIO + " where " + Cons.FECHA + "='" + Fecha + "' AND " + Cons.DIA + "= '" + String.valueOf(Dia) + "'", null);
        if (res.moveToFirst()) {
            do {
                Horario = new Horario(res.getInt(0), res.getInt(2), res.getString(1));
            } while(res.moveToNext());
        }
        return Horario;
    }

    public int getDia() { return this.Dia; }

    public String getFecha() { return this.Fecha; }

    public int getId() { return this.HorarioId; }

    public String getInicioHorario() { return String.valueOf(this.InicioHorario); }

    public String getTerminoHorario() { return String.valueOf(this.InicioHorario + 1); }

    public void setInicioHorario(int InicioHorario) { this.InicioHorario = InicioHorario; }
}
