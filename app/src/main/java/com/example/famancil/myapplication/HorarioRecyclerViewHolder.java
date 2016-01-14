package com.example.famancil.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HorarioRecyclerViewHolder extends RecyclerView.ViewHolder {

    private Actividad Actividad;
    private Horario Horario;
    private TextView NumHorario;
    private TextView Act;
    private RatingBar RatingBar;
    private DataBaseHelper DataBaseHelper;
    private int prior;
    private Cursor prioridad;
    private AppCompatActivity Activity;
    private ArrayList<Actividad> Actividades;

    public HorarioRecyclerViewHolder(final View itemView, final Horario Horario, final AppCompatActivity Activity,ArrayList<Actividad> Actividades,DataBaseHelper DataBaseHelper) {
        super(itemView);
        this.Activity=Activity;
        this.Horario = Horario;
        this.DataBaseHelper = DataBaseHelper;
        NumHorario = (TextView) itemView.findViewById(R.id.numhorario);
        Act = (TextView) itemView.findViewById(R.id.activity);
        RatingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        prioridad = null;
        this.Actividades=Actividades;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Activity, "Prioridad Actualizada", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Activity, MainActivity.class);
                i.putExtra("HorarioDia", Horario.getDia());
                i.putExtra("HorarioFecha", Horario.getFecha());
                i.putExtra("ActividadInicio", Actividad.getHorario());
                Activity.startActivity(i);
            }
        });
    }

    public void setActividad(Actividad Actividad, HorarioRecyclerViewHolder holder, int position)
    {
        float Stars = 0;
        this.Actividad = Actividad;
        NumHorario.setText(String.valueOf(Actividad.getHorario()));
        Act.setText(Actividad.getNombre());
        //setStars();
        Cursor res = Prioridad.BuscarPrioridadPorActividad(DataBaseHelper, String.valueOf(Actividad.getId()));
        while (res.moveToNext()) {
            Stars=res.getInt(2);
        }
        RatingBar.setOnRatingBarChangeListener(onRatingChangedListener(holder, position));
        RatingBar.setTag(Actividad.getId());
        RatingBar.setRating(Stars);
    }

  /*  private void setStars()
    {
        Prioridad pri = new Prioridad();
        if(prioridad == null) {
            if (Actividad.getId() != -1) {
                prioridad = pri.BuscarPrioridad(DataBaseHelper, String.valueOf(Actividad.getId()));
                if (prioridad.moveToFirst())
                    prior = prioridad.getInt(2);
                else prior = 3;
            } else prior = -1;
        }
        if (prior != -1)
            RatingBar.setRating(prior);

    }*/

    private RatingBar.OnRatingBarChangeListener onRatingChangedListener(final HorarioRecyclerViewHolder holder, final int position) {
        return new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                float Rating;
                int Id;

                Actividad item = getItem(position);
                item.setRatingStar(v);
                Rating=item.getRatingStar();
                Id= item.getId();
                DataBaseHelper.getInstance(Activity);
                Cursor res = Prioridad.BuscarPrioridadPorActividad(DataBaseHelper, String.valueOf(Id));
                int count=res.getCount();
                //Log.i("Adapter", "star: " + v);

                if (count== 0 && Rating>0) {
                    boolean isInserted = Prioridad.InsertarPrioridad(DataBaseHelper, Rating, String.valueOf(Id));
                    if (isInserted)
                        Toast.makeText(Activity, "Prioridad Aceptada", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Activity, "Prioridad Rechazada", Toast.LENGTH_LONG).show();
                } else {
                    String uno=null;
                    float stars=0;
                    while (res.moveToNext()) {
                        uno=res.getString(0);
                        stars=res.getInt(2);
                    }
                    if(Rating!=stars){
                       boolean isInserted = Prioridad.ActualizarPrioridad(DataBaseHelper, uno, Rating, String.valueOf(Id));
                        if (isInserted)
                            Toast.makeText(Activity, "Prioridad Actualizada", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity, "Rechazada actualizacion de Prioridad", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
    }

    public Actividad getItem(int position) {
        return Actividades.get(position);
    }
}

