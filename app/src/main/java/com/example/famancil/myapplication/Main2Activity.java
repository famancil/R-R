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

    TextView texto,text_rating;
    Button boton_add,button_ver;
    DataBaseHelper dataBaseHelper;
    String Id,Name;
    EditText edit_idAct,edit_nameAct;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        texto=(TextView)findViewById(R.id.textView3);
        boton_add=(Button)findViewById(R.id.button_addprio);
        button_ver=(Button)findViewById(R.id.button_viewall);
        edit_idAct=(EditText)findViewById(R.id.editIdAct);
        edit_nameAct=(EditText)findViewById(R.id.editNameAct);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Intent intent= getIntent();
        Id = intent.getStringExtra("miembroId");
        Name = intent.getStringExtra("miembroNombre");
        edit_idAct.setText(Id);
        edit_nameAct.setText(Name);

        dataBaseHelper=DataBaseHelper.getInstance(this);
        Cursor uno = Prioridad.BuscarPrioridad(dataBaseHelper, Id);
        if (uno.moveToNext()){
            ratingBar.setRating(Float.valueOf(uno.getString(2)));
        }
        
        addPriority();
        viewData();
        addListenerOnRatingBar();

    }

    public void addPriority() {
        boton_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = Prioridad.InsertarPrioridad(dataBaseHelper,String.valueOf(ratingBar.getRating()),
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
                        Cursor res = Prioridad.BuscarTodasPrioridades(dataBaseHelper);
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

    public void ClickButtonDatosHorario(View view)
    {
        Intent intent = new Intent(this, HorarioDiario.class);
        startActivity(intent);
    }

    public void ClickButtonEstadisticasActivity2(View view)
    {
        Intent intent = new Intent(this, Estadisticas.class);
        startActivity(intent);
    }
}
