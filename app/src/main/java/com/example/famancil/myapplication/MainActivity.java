package com.example.famancil.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    EditText edit_name, edit_id, edit_inicio, edit_termino;
    Button btn_addData, btn_viewdata, btn_reset, btn_delete, btn_newact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = DataBaseHelper.getInstance(this);
        edit_id = (EditText) findViewById(R.id.editId);
        edit_name = (EditText) findViewById(R.id.editNombre);
        edit_inicio = (EditText) findViewById(R.id.editInicio);
        edit_termino = (EditText) findViewById(R.id.editTermino);
        btn_addData = (Button) findViewById(R.id.btnAdd);
        btn_viewdata = (Button) findViewById(R.id.button_viewall);
        btn_reset = (Button) findViewById(R.id.button_reset);
        btn_delete = (Button) findViewById(R.id.button_delete);
        btn_newact = (Button) findViewById(R.id.button_act);
        addData();
        viewData();
        dropTable();
        DeleteData();
        VerPrioridad();
    }

    public void DeleteData() {
        btn_delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = Actividad.BorrarActividad(dataBaseHelper, edit_id.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Actividad Borrada", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Actividad no Borrada", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewData() {
        btn_viewdata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = Actividad.BuscarTodosActividades(dataBaseHelper);
                        if (res.getCount() == 0) {
                            showMessage("Aviso", "No hay actividades");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Nombre :" + res.getString(1) + "\n");
                            buffer.append("Inicio :" + res.getString(2) + "\n");
                            buffer.append("Termino :" + res.getString(3) + "\n");
                            buffer.append("Cumplido :" + res.getString(4) + "\n");
                            buffer.append("Invariable :" + res.getString(5) + "\n");
                        }
                        showMessage("Actividad", buffer.toString());
                    }
                }
        );
    }

    public void dropTable() {
        btn_reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // db.delete(String tableName, String whereClause, String[] whereArgs);
                        // If whereClause is null, it will delete all rows.
                        dataBaseHelper.dropTable();
                        //db.delete(DatabaseHelper.TAB, null, null);
                    }
                        /*if (res.getCount() == 0) {
                            showMessage("Error", "Nada Encontrado");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Activity :" + res.getString(1) + "\n");
                            buffer.append("Grade :" + res.getString(2) + "\n");
                        }
                        showMessage("Data", buffer.toString());
                    }*/
                }
        );
    }

    public void addData() {
        btn_addData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = Actividad.InsertarActividad(dataBaseHelper, edit_name.getText().toString(),
                                edit_inicio.getText().toString(),
                                edit_termino.getText().toString(), false, false);
                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Actividad Ingresada", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Actividad Rechazada", Toast.LENGTH_LONG).show();
                        edit_name.setText("");
                        edit_inicio.setText("");
                        edit_termino.setText("");
                    }
                }
        );
    }

    public void VerPrioridad() {
        btn_newact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MostrarActividad.class);
                        startActivity(intent);

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title, String Message) {
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

    public void ClickButtonEstadisticas(View view)
    {
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
    }

}

