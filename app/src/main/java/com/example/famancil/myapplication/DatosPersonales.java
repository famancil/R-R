package com.example.famancil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DatosPersonales extends AppCompatActivity {

    private DataBaseHelper DataBaseHelper;
    private Usuario User;
    private static boolean Ingreso = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
        EditText Nombre, Email, Universidad;
        RadioButton Diario, Semanal, Mensual;
        Usuario Usuario = new Usuario();

        DataBaseHelper = DataBaseHelper.getInstance(this);                                   //new DataBaseHelper(this, 1);
        Nombre = (EditText) findViewById(R.id.Nombre);
        Email = (EditText) findViewById(R.id.Email);
        Universidad = (EditText) findViewById(R.id.Universidad);
        Diario = (RadioButton) findViewById(R.id.Diario);
        Semanal = (RadioButton) findViewById(R.id.Semanal);
        Mensual = (RadioButton) findViewById(R.id.Mensual);
        User = Usuario.ConsultarUsuario(DataBaseHelper);

        if(User != null && (User.ExisteUsuario(DataBaseHelper) && !Ingreso))
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
            Usuario Usuario = new Usuario(Nombre.getText().toString(), Email.getText().toString(), Universidad.getText().toString(), Estadisticas);
            if (Usuario.InsertarUsuario(DataBaseHelper)) {
                System.out.println("Se ingresó bien el usuario");
            } else System.out.println("Falló en ingresarse el usuario");
        }
        else
        {
            Usuario Usuario = new Usuario(Nombre.getText().toString(), Email.getText().toString(), Universidad.getText().toString(), Estadisticas);
            Usuario.ActualizarUsuario(DataBaseHelper);
        }
        //User.EliminarUsuario(DataBaseHelper);
    }

    public void ClickButtonHorario(View view)
    {
        Intent intent = new Intent(this, HorarioDiario.class);
        startActivity(intent);
    }

    public void ClickButtonEstadisticas(View view)
    {
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
    }
}
