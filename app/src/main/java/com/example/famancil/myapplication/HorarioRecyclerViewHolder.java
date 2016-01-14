package com.example.famancil.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class HorarioRecyclerViewHolder extends RecyclerView.ViewHolder {

    private Actividad Actividad;
    private Horario Horario;
    private TextView NumHorario;
    private TextView Act;
    private RatingBar RatingBar;
    private DataBaseHelper DataBaseHelper;
    private int prior;
    private Cursor prioridad;


    public HorarioRecyclerViewHolder(final View itemView, final Horario Horario, final AppCompatActivity Activity) {
        super(itemView);
        this.Horario = Horario;
        DataBaseHelper = DataBaseHelper.getInstance(Activity);
        NumHorario = (TextView) itemView.findViewById(R.id.numhorario);
        Act = (TextView) itemView.findViewById(R.id.activity);
        RatingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        prioridad = null;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity, MainActivity.class);
                i.putExtra("HorarioDia", Horario.getDia());
                i.putExtra("HorarioFecha", Horario.getFecha());
                i.putExtra("ActividadInicio", Actividad.getHorario());
                Activity.startActivity(i);
            }
        });
    }

    public void setActividad(Actividad Actividad)
    {
        this.Actividad = Actividad;
        NumHorario.setText(String.valueOf(Actividad.getHorario()));
        Act.setText(Actividad.getNombre());
        setStars();
    }

    private void setStars()
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
    }
}
