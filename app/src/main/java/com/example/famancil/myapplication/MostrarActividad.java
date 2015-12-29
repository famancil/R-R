package com.example.famancil.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by famancil on 29-12-15.
 */
public class MostrarActividad extends AppCompatActivity {
    //Button btnAgregarMiembro;
    ListView lista;
    DataBaseHelper dataBaseHelper=DataBaseHelper.getInstance(this);
    TextView Id, Nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_actividad);

        //btnAgregarMiembro = (Button) findViewById(R.id.btnAgregarMiembro);
        lista = (ListView) findViewById(R.id.listViewMiembros);
        listar();


        Cursor cursor = Actividad.BuscarTodosActividades(dataBaseHelper);

        String[] from = new String[] {
                Cons.ACTIVIDAD_ID,
                Cons.NOMBRE_ACTIVIDAD
        };
        int[] to = new int[] {
                R.id.miembro_id,
                R.id.miembro_nombre
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MostrarActividad.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);
    }


    public void listar(){
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Id = (TextView) view.findViewById(R.id.miembro_id);
                Nombre = (TextView) view.findViewById(R.id.miembro_nombre);

                String aux_Id = Id.getText().toString();
                String aux_Nombre = Nombre.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), Main2Activity.class);
                modify_intent.putExtra("miembroId", aux_Id);
                modify_intent.putExtra("miembroNombre", aux_Nombre);
                startActivity(modify_intent);

            }
        });

    }
}
