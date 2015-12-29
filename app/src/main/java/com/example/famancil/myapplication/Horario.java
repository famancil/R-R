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
    private String Descripcion;
    private int UsuarioId;

    public Horario(int Dia, String Fecha, String Descripcion, int UsuarioId)
    {
        this.Dia = Dia;
        this.Fecha = Fecha;
        this.Descripcion = Descripcion;
        this.UsuarioId = UsuarioId;
    }

    public Horario(int HorarioId, int Dia, String Fecha, String Descripcion, int UsuarioId)
    {
        this.HorarioId = HorarioId;
        this.Dia = Dia;
        this.Fecha = Fecha;
        this.Descripcion = Descripcion;
        this.UsuarioId = UsuarioId;
    }

    public boolean IngresarHorario(DataBaseHelper DBHorario)
    {
        SQLiteDatabase db = DBHorario.getWritableDatabase();

        ContentValues NuevoRegistro = new ContentValues();
        NuevoRegistro.put(Cons.HORARIO_ID, HorarioId);
        NuevoRegistro.put(Cons.DIA, Dia);
        NuevoRegistro.put(Cons.FECHA, Fecha);
        NuevoRegistro.put(Cons.DESCRIPCION, Descripcion);
        NuevoRegistro.put(Cons.USUARIO_ID_HORARIO, UsuarioId);
        return db.insert(Cons.HORARIO, null, NuevoRegistro) != -1;
    }

    public ArrayList<Horario> ConsultarHorario(DataBaseHelper DBHorario) {

        int HorarioId, Dia, UsuarioId;
        String Descripcion, Fecha;
        SQLiteDatabase db = DBHorario.getReadableDatabase();
        String[] campos = new String[]{Cons.HORARIO_ID, Cons.DIA, Cons.FECHA, Cons.DESCRIPCION, Cons.USUARIO_ID_HORARIO};

        Cursor Hr = db.query(Cons.HORARIO, campos, null, null, null, null, null);
        ArrayList<Horario> Horarios = new ArrayList<>();
        if (Hr.moveToFirst()) {
            do
            {
                Horario Horario = new Horario(Hr.getInt(0), Hr.getInt(1), Hr.getString(2), Hr.getString(3), Hr.getInt(4));
                Horarios.add(Horario);
            } while (Hr.moveToNext());
        }

        return Horarios;
    }

}
