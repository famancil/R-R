package com.example.famancil.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;

public class DatosPersonales extends AppCompatActivity {

    private static DataBaseHelper DataBaseHelper;
    private Usuario User;
    private static boolean Ingreso = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
        EditText Nombre, Email, Universidad;
        RadioButton Diario, Semanal, Mensual;
        Usuario Usuario = new Usuario();
        //this.getApplicationContext().deleteDatabase("RRB.db");

        DataBaseHelper = DataBaseHelper.getInstance(this);              //new DataBaseHelper(this, 1);
        Nombre = (EditText) findViewById(R.id.Nombre);
        Email = (EditText) findViewById(R.id.Email);
        Universidad = (EditText) findViewById(R.id.Universidad);
        Diario = (RadioButton) findViewById(R.id.Diario);
        Semanal = (RadioButton) findViewById(R.id.Semanal);
        Mensual = (RadioButton) findViewById(R.id.Mensual);
        User = Usuario.ConsultarUsuario(DataBaseHelper);

        if(User != null && !Ingreso)
        {
            Intent intent = new Intent(this, HorarioDiario.class);
            Ingreso = true;
            startActivity(intent);
            finish();
        }
        else
        {
            if (User != null) {
                Nombre.setText(User.getNombre());
                Email.setText(User.getEmail());
                Universidad.setText(User.getUniversidad());

                if (User.getEstadisticas() == 0) Diario.setChecked(true);
                if (User.getEstadisticas() == 1) Semanal.setChecked(true);
                if (User.getEstadisticas() == 2) Mensual.setChecked(true);
            }
        }
    }

    public void ClickButtonIngresar(View view) {
        EditText Nombre, Email, Universidad;
        RadioGroup RadioGroup;
        int Estadisticas = -1;
        DataBaseHelper = DataBaseHelper.getInstance(this);
        Nombre = (EditText) findViewById(R.id.Nombre);
        Email = (EditText) findViewById(R.id.Email);
        Universidad = (EditText) findViewById(R.id.Universidad);
        RadioGroup = (RadioGroup) findViewById(R.id.RadioGroup);

        int SelectId = RadioGroup.getCheckedRadioButtonId();
        if (SelectId == R.id.Diario) Estadisticas = 0;
        if (SelectId == R.id.Semanal) Estadisticas = 1;
        if (SelectId == R.id.Mensual) Estadisticas = 2;

        if (User == null)
        {
            Usuario Usuario = new Usuario(Nombre.getText().toString(), Universidad.getText().toString(), Email.getText().toString(), Estadisticas);
            if (Usuario.InsertarUsuario(DataBaseHelper)) {
                Snackbar.make(Nombre, "Se ha ingresado bien el usuario", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(this, HorarioDiario.class);
                Ingreso = true;
                startActivity(intent);
            } else Snackbar.make(Nombre, "Falló en ingresar al usuario", Snackbar.LENGTH_LONG).show();
        }
        else {
            Usuario Usuario = new Usuario(Nombre.getText().toString(), Universidad.getText().toString(), Email.getText().toString(), Estadisticas);
            if (Usuario.ActualizarUsuario(DataBaseHelper) == 0)
                Snackbar.make(Nombre, "No hay cambios", Snackbar.LENGTH_LONG).show();
            else Snackbar.make(Nombre, "Se ha actualizado el usuario", Snackbar.LENGTH_LONG).show();
        }
        //User.EliminarUsuario(DataBaseHelper);
    }

    public void ClickButtonHorario(View view)
    {
        Intent intent = new Intent(this, HorarioDiario.class);
        startActivity(intent);
    }

    public void ClickButtonEstadisticasDatosPersonales(View view)
    {
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
    }
}
