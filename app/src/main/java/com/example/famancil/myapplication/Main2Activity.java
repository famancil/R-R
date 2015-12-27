package com.example.famancil.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity{
    CheckBox checkR,checkA,checkAm;
    TextView texto,text_rating;
    Button boton_add,button_ver,btn_find;
    DataBaseHelper myDB;
    EditText edit_idAct,edit_nameAct,edit_grade;
    private RatingBar ratingBar;
    Actividad actividad;
    Prioridad prioridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        texto=(TextView)findViewById(R.id.textView3);
        //checkR=(CheckBox)findViewById(R.id.checkBox);
        //checkA=(CheckBox)findViewById(R.id.checkBox2);
        //checkAm=(CheckBox)findViewById(R.id.checkBox3);
        boton_add=(Button)findViewById(R.id.button_addprio);
        button_ver=(Button)findViewById(R.id.button_viewall);
        btn_find=(Button)findViewById(R.id.button_find);
        edit_idAct=(EditText)findViewById(R.id.editIdAct);
        edit_nameAct=(EditText)findViewById(R.id.editNameAct);
        //edit_grade=(EditText)findViewById(R.id.editGrade);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
        myDB=DataBaseHelper.getInstance(this);
        if(bundle!=null){
            String cadena=(String)bundle.get("DATO");
            texto.setText(cadena);
        }
        addPriority();
        viewData();
        FindData();
        addListenerOnRatingBar();
        //boton.setOnClickListener(this);
        //button_ver.setOnClickListener(this);
        /*/FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    public void addPriority() {
        boton_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = prioridad.InsertarPrioridad(myDB,String.valueOf(ratingBar.getRating()),
                                edit_idAct.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(Main2Activity.this, "Prioridad Aceptada", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main2Activity.this, "Prioridad Rechazada", Toast.LENGTH_LONG).show();
                        edit_idAct.setText("");
                        edit_nameAct.setText("");
                        ratingBar.setRating(0);
                    }
                }
        );
    }

    public void viewData() {
        button_ver.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = prioridad.BuscarTodasPrioridades(myDB);
                        if (res.getCount() == 0) {
                            showMessage("Aviso", "No hay prioridades");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Grado :" + res.getString(2) + "\n");
                            buffer.append("actividad_id :" + res.getString(1) + "\n");
                        }
                        showMessage("Prioridad", buffer.toString());
                    }
                }
        );
    }
    public void FindData() {
        btn_find.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = actividad.BuscarActividad(myDB, edit_idAct.getText().toString());
                        edit_idAct.setText("");
                        edit_nameAct.setText("");
                        if (res.getCount() == 0) {
                            showMessage("Aviso", "No hay actividades");
                            return;
                        }
                        while (res.moveToNext()) {
                            edit_idAct.setText(res.getString(0));
                            edit_nameAct.setText(res.getString(1));
                        }
                        /*StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Ok :" + res.getString(2) + "\n");
                            buffer.append("Final :" + res.getString(3) + "\n");
                        }
                        showMessage("Data", buffer.toString());*/
                    }
                }
        );
    }

    public void addListenerOnRatingBar() {

        text_rating=(TextView)findViewById(R.id.textView_rating);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                text_rating.setText(String.valueOf(rating));

            }
        });
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
}
