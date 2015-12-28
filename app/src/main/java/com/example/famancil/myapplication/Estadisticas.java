package com.example.famancil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


/**
 * Created by Seba on 28-12-2015.
 */
public class Estadisticas extends AppCompatActivity {

}
    private DataBaseHelper DataBaseHelper;
    private Usuario User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);
    }
    public void GenerarEstadistica(View view) {

    }

    public void ClickButtonDatosPersonales(View view)
    {
        Intent intent = new Intent(this, DatosPersonales.class);
        startActivity(intent);
    }

    public void ClickButtonHorario(View view)
    {
        Intent intent = new Intent(this, HorarioDiario.class);
        startActivity(intent);
    }

    public void ClickButtonPrioridad(View view)
    {
        Intent intent = new Intent(this, Prioridad.class);
        startActivity(intent);
    }
}
