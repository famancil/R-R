package com.example.famancil.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class HorarioDiario extends AppCompatActivity {
    private static DataBaseHelper DataBaseHelper;
    private RecyclerView.Adapter mAdapter;
    private Horario Horario;
    private ArrayList<Actividad> Actividades;
    static int PFecha = 0;
    private String Fecha;
    private Calendar calendar;
    private static Activity Activity;
    private RecyclerView mRecyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_horariodiario, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_diario);
        calendar = Calendar.getInstance();
        setFecha();
        setHorario();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RellenarActividades();
        this.Activity = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.RecView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new HorarioRecyclerViewAdapter(Horario, Actividades, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_eliminar:
                EliminarHorario();
                onResume();
                return true;
            case R.id.action_generar:
                System.out.println("Generar");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void GenerarHorario()
    {

    }

    private void EliminarHorario()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Estás seguro que deseas eliminar todas las actividades");

        alertDialogBuilder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                DataBaseHelper.deleteTableActivity();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void RellenarActividades()
    {
        int i, j;
        boolean encontrado;
        Horario hor;
        DataBaseHelper = DataBaseHelper.getInstance(this);
        hor = Horario.BuscarHorario(DataBaseHelper, Fecha, calendar.get(calendar.DAY_OF_WEEK));
        if(esNullHorario(hor))
        {
            Horario.IngresarHorario(DataBaseHelper);
            hor = Horario.BuscarHorario(DataBaseHelper, Fecha, calendar.get(calendar.DAY_OF_WEEK));
        }
        ActividadHorario ac = null;
        ArrayList<ActividadHorario> ActividadHorario = ac.BuscarActividadHorario(DataBaseHelper, hor.getId());
        Actividades = new ArrayList<>();
        ArrayList<Actividad> actividads = new ArrayList<>();
        for(i = 0; i < ActividadHorario.size(); i++)
        {
            Actividad actividad = null;
            actividad = actividad.BuscarActividades(DataBaseHelper, ActividadHorario.get(i).getActividadId());
            actividads.add(actividad);
        }

        for(i = 0; i < 24; i++)
        {
            encontrado = false;
            for(j = 0; j < actividads.size(); j++)
            {
                if(actividads.get(j).getInicio() == i)
                {
                    actividads.get(j).setHorario(i);
                    Actividades.add(actividads.get(j));
                    encontrado = true;
                    break;
                }
            }
            if(!encontrado)
            {
                Actividad activity = new Actividad(i);
                Actividades.add(activity);
            }
        }
    }

    private boolean esNullHorario(Horario Horario)
    {
        return Horario == null;
    }

    private void setFecha()
    {
        Fecha = Cons.Dia(calendar.get(calendar.DAY_OF_WEEK)) + ", " + calendar.get(calendar.DAY_OF_MONTH) + " de " + Cons.Mes(calendar.get(calendar.MONTH)) + " de " + calendar.get(calendar.YEAR);
        TextView TVFecha = (TextView) findViewById(R.id.Fecha);
        TVFecha.setText(Fecha);
    }

    private void setHorario()
    {
        Horario = new Horario(calendar.get(calendar.DAY_OF_WEEK), Fecha);
    }

    public static Activity getActivity() { return Activity; }

    public void BackButton(View view)
    {
        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH) - 1);
        setFecha();
        setHorario();
        onResume();
    }

    public void ForwardButton(View view)
    {
        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH) + 1);
        setFecha();
        setHorario();
        onResume();
    }

    public void ClickButtonEstadisticasHorarioDiario(View view)
    {
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
    }

    public void ClickButtonPrioridadHorarioDiario(View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void ClickButtonDatosPersonales(View view)
    {
        Intent intent = new Intent(this, DatosPersonales.class);
        startActivity(intent);
    }
}
