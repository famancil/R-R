package com.example.famancil.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HorarioDiario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_diario);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ClickButtonDatosPersonales(View view)
    {
        Intent intent = new Intent(this, DatosPersonales.class);
        startActivity(intent);
    }
}
