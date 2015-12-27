package com.example.famancil.myapplication;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Usuario {
    private Integer UsuarioId;
    private String Nombre;
    private String Universidad;
    private String Email;
    private Integer Estadisticas;

    public Usuario(int UsuarioId, String Nombre, String Universidad, String Email, int Estadisticas) {
        this.UsuarioId = UsuarioId;
        this.Nombre = Nombre;
        this.Universidad = Universidad;
        this.Email = Email;
        this.Estadisticas = Estadisticas;
    }

    public Usuario(String Nombre, String Universidad, String Email, int Estadisticas) {
        this.Nombre = Nombre;
        this.Universidad = Universidad;
        this.Email = Email;
        this.Estadisticas = Estadisticas;
    }

    public Usuario() {}

    public boolean InsertarUsuario(DataBaseHelper DBUsuario) {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();

        ContentValues NuevoRegistro = new ContentValues();
        NuevoRegistro.put(Cons.NOMBRE_USUARIO, Nombre);
        NuevoRegistro.put(Cons.EMAIL, Email);
        NuevoRegistro.put(Cons.UNIVERSIDAD, Universidad);
        NuevoRegistro.put(Cons.ESTADISTICAS_USUARIO, Estadisticas);

        if (db.insert(Cons.USUARIO, null, NuevoRegistro) != -1) return true;
        else return false;
    }

    public Usuario ConsultarUsuario(DataBaseHelper DBUsuario) {
        SQLiteDatabase db = DBUsuario.getReadableDatabase();
        String[] campos = new String[]{Cons.USUARIO_ID, Cons.NOMBRE_USUARIO, Cons.UNIVERSIDAD, Cons.EMAIL, Cons.ESTADISTICAS_USUARIO};

        Cursor User = db.query(Cons.USUARIO, campos, null, null, null, null, null);

        Usuario Usuario = null;
        if (User.moveToFirst()) {
            do {
                Usuario = new Usuario(User.getInt(0), User.getString(1), User.getString(2), User.getString(3), User.getInt(4));
            } while (User.moveToNext());
        }
        return Usuario;
    }

    public void ActualizarUsuario(DataBaseHelper DBUsuario)
    {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();

        Usuario User = ConsultarUsuario(DBUsuario);

        ContentValues valores = new ContentValues();
        if(!Nombre.equals(User.getNombre())) valores.put(Cons.NOMBRE_USUARIO, Nombre);
        if(!Email.equals(User.getEmail())) valores.put(Cons.EMAIL, Email);
        if(!Universidad.equals(User.getUniversidad())) valores.put(Cons.UNIVERSIDAD, Universidad);
        if(Estadisticas != User.getEstadisticas()) valores.put(Cons.ESTADISTICAS_USUARIO, Estadisticas);
        db.update(Cons.USUARIO, valores, Cons.USUARIO_ID + "=" + User.getUsuarioId().toString(), null);
    }

    public void EliminarUsuario(DataBaseHelper DBUsuario)
    {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        Usuario User = ConsultarUsuario(DBUsuario);
        db.delete(Cons.USUARIO, Cons.USUARIO_ID + "=" + User.getUsuarioId().toString(), null);
    }

    public boolean ExisteUsuario(DataBaseHelper DBUsuario)
    {
        SQLiteDatabase db = DBUsuario.getReadableDatabase();
        Usuario User = ConsultarUsuario(DBUsuario);
        if(User == null) return false;
        else return true;
    }

    public Integer getUsuarioId() {  return this.UsuarioId; }
    public String getNombre() { return this.Nombre; }
    public String getUniversidad() { return this.Universidad; }
    public String getEmail() { return this.Email; }
    public Integer getEstadisticas() { return this.Estadisticas; }

    public void setUsuarioId(int UsuarioId) { this.UsuarioId = UsuarioId; }
    public void setNombre(String Nombre) { this.Nombre = Nombre; }
    public void setUniversidad(String Universidad) { this.Universidad = Universidad; }
    public void setEmail(String Email) { this.Email = Email; }
    public void setEstadisticas(int Estadisticas) { this.Estadisticas = Estadisticas; }
}
