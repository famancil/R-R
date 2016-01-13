package com.example.famancil.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ActividadHorario {
    private int HorarioId;
    private int ActividadId;

    public ActividadHorario(int HorarioId, int ActividadId)
    {
        this.HorarioId = HorarioId;
        this.ActividadId = ActividadId;
    }

    public boolean InsertarActividadHorario(DataBaseHelper DBUsuario) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cons.ACTIVIDAD_ID_ACTIVIDADHORARIO, this.ActividadId);
        contentValues.put(Cons.HORARIO_ID_ACTIVIDADHORARIO, this.HorarioId);
        long result = db.insert(Cons.ACTIVIDADHORARIO,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public static ArrayList<ActividadHorario> BuscarActividadHorario(DataBaseHelper DBUsuario, int HorarioId) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        Cursor ActHor = db.rawQuery("select * from " + Cons.ACTIVIDADHORARIO + " where " + Cons.HORARIO_ID_ACTIVIDADHORARIO + "='" + String.valueOf(HorarioId) + "'", null);
        ArrayList<ActividadHorario> ActividadHorario  = new ArrayList<>();
        if (ActHor.moveToFirst()) {
            ActividadHorario AH;
            do
            {
                AH = new ActividadHorario(ActHor.getInt(0), ActHor.getInt(1));
                ActividadHorario.add(AH);
            } while (ActHor.moveToNext());
        }
        return ActividadHorario;
    }

    public int getHorarioId() { return this.HorarioId; }

    public int getActividadId() { return this.ActividadId; }
}
