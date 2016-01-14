package com.example.famancil.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import java.util.ArrayList;


public class HorarioRecyclerViewAdapter extends RecyclerView.Adapter<HorarioRecyclerViewHolder> {
    private Horario Horario;
    private ArrayList<Actividad> Actividades;
    private AppCompatActivity Activity;
    private DataBaseHelper dataBaseHelper;

    public HorarioRecyclerViewAdapter(Horario Horario, ArrayList<Actividad> Actividades, Activity Activity,DataBaseHelper dataBaseHelper)
    {
        this.Horario = Horario;
        this.Actividades = Actividades;
        this.Activity = (AppCompatActivity) Activity;
        this.dataBaseHelper=dataBaseHelper;
    }

    @Override
    public HorarioRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_horario, parent, false);
        return new HorarioRecyclerViewHolder(v, Horario, Activity,Actividades,dataBaseHelper);
    }

    @Override
    public void onBindViewHolder(HorarioRecyclerViewHolder holder, int position) {
        holder.setActividad(Actividades.get(position),holder,position);
    }

    @Override
    public int getItemCount() {
        return Actividades.size();
    }

}
