package com.example.famancil.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.support.v7.widget.Toolbar;


public class Estadisticas extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    Button  button_ver;
    String Id;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);
        button_ver = (Button) findViewById(R.id.button_statistics);

        Intent intent= getIntent();
        dataBaseHelper=DataBaseHelper.getInstance(this);
        Cursor uno = Prioridad.BuscarPrioridad(dataBaseHelper, Id);

        viewData();
    }
    public void viewData() {
        button_ver.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor activity = Actividad.BuscarTodosActividades(dataBaseHelper);
                        if (activity.getCount() == 0) {
                            showMessage("Aviso", "No hay actividades");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        int nActividades = activity.getCount();
                        while (activity.moveToNext()) {
                            buffer.append("Actividad :" + activity.getString(1) + "\n");
                            buffer.append("Cumplido :" + activity.getString(4) + "\n");
                            buffer.append("\n");
                            if (activity.getString(4) != null) counter += 1;
                        }
                        if (counter == 0){
                            buffer.append("No se han completado actividades");
                        }
                        else{
                            buffer.append("Se han cumplido actividades");
                        }
                        showMessage("Estadisticas", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
