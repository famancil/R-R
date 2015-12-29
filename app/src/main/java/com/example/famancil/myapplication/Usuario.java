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

        return db.insert(Cons.USUARIO, null, NuevoRegistro) != -1;
    }

    public Usuario ConsultarUsuario(DataBaseHelper DBUsuario) {

        int id = 0, estadisticas = 0;
        String nombre = null, email = null, universidad = null;
        SQLiteDatabase db = DBUsuario.getReadableDatabase();
        String[] campos = new String[]{Cons.USUARIO_ID, Cons.NOMBRE_USUARIO, Cons.EMAIL, Cons.UNIVERSIDAD, Cons.ESTADISTICAS_USUARIO};
        String[] args = new String[]{"1"};

        Cursor User = db.query(Cons.USUARIO, campos, Cons.USUARIO_ID +"=?", args, null, null, null);

        Usuario Usuario = null;
        if (User.moveToFirst()) {
            do {
                for (int i = 0; i < 5; i++)
                {
                    if(User.getColumnName(i).equals(Cons.USUARIO_ID)) id = User.getInt(i);
                    if(User.getColumnName(i).equals(Cons.NOMBRE_USUARIO)) nombre = User.getString(i);
                    if(User.getColumnName(i).equals(Cons.EMAIL)) email = User.getString(i);
                    if(User.getColumnName(i).equals(Cons.UNIVERSIDAD)) universidad = User.getString(i);
                    if(User.getColumnName(i).equals(Cons.ESTADISTICAS_USUARIO)) estadisticas = User.getInt(i);
                    Usuario = new Usuario(id, nombre, universidad, email, estadisticas);
                }

            } while (User.moveToNext());
        }

        return Usuario;
    }

    public int ActualizarUsuario(DataBaseHelper DBUsuario)
    {
        SQLiteDatabase db = DBUsuario.getWritableDatabase();
        boolean actualizar = false;

        Usuario User = ConsultarUsuario(DBUsuario);
        ContentValues valores = new ContentValues();
        if(!Nombre.equals(User.getNombre())) { valores.put(Cons.NOMBRE_USUARIO, Nombre); actualizar = true; }
        if(!Email.equals(User.getEmail())) { valores.put(Cons.EMAIL, Email); actualizar = true; }
        if(!Universidad.equals(User.getUniversidad())) { valores.put(Cons.UNIVERSIDAD, Universidad); actualizar = true; }
        if(Estadisticas != User.getEstadisticas()) { valores.put(Cons.ESTADISTICAS_USUARIO, Estadisticas); actualizar = true; }
        if(actualizar) return  db.update(Cons.USUARIO, valores, Cons.USUARIO_ID + "=" + User.getUsuarioId().toString(), null);
        else return 0;
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
        return User != null;
    }

    public int ConsultarUsuarioId(DataBaseHelper DBUsuario)
    {
        int id = 0;
        SQLiteDatabase db = DBUsuario.getReadableDatabase();
        String[] campos = new String[]{Cons.USUARIO_ID};

        Cursor User = db.query(Cons.USUARIO, campos, null, null, null, null, null);
        return User.getInt(0);
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
