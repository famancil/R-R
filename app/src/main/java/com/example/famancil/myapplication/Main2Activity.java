package com.example.famancil.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    CheckBox checkR,checkA,checkAm;
    TextView texto;
    Button boton;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        texto=(TextView)findViewById(R.id.textView3);
        checkR=(CheckBox)findViewById(R.id.checkBox);
        checkA=(CheckBox)findViewById(R.id.checkBox2);
        checkAm=(CheckBox)findViewById(R.id.checkBox3);
        boton=(Button)findViewById(R.id.button4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
        myDb=new DatabaseHelper(this);
        if(bundle!=null){
            String cadena=(String)bundle.get("DATO");
            texto.setText(cadena);
        }
        boton.setOnClickListener(this);
        /*/FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        StringBuffer result= new StringBuffer();
        result.append(" 1 Estrella: ").append((checkR.isChecked()));
        result.append(" 2 Estrellas: ").append((checkA.isChecked()));
        result.append(" 3 Estrellas: ").append((checkAm.isChecked()));

        Toast.makeText(Main2Activity.this,result.toString(),Toast.LENGTH_SHORT).show();

    }
}
