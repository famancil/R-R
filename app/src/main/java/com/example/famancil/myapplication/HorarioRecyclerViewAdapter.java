package com.example.famancil.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class HorarioRecyclerViewAdapter extends RecyclerView.Adapter<HorarioRecyclerViewHolder> {
    private Horario Horario;
    private ArrayList<Actividad> Actividades;
    private AppCompatActivity Activity;

    public HorarioRecyclerViewAdapter(Horario Horario, ArrayList<Actividad> Actividades, Activity Activity)
    {
        this.Horario = Horario;
        this.Actividades = Actividades;
        this.Activity = (AppCompatActivity) Activity;
    }

    @Override
    public HorarioRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_horario, parent, false);

        return new HorarioRecyclerViewHolder(v, Horario, Activity);
    }

    @Override
    public void onBindViewHolder(HorarioRecyclerViewHolder holder, int position) {
        holder.setActividad(Actividades.get(position));
    }

    @Override
    public int getItemCount() {
        return Actividades.size();
    }
}
