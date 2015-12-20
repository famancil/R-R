package com.example.famancil.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    Button boton1,boton2,boton3;
    ImageView image;
    EditText edit,act,grade;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text=(TextView)findViewById(R.id.textView);
        boton1=(Button)findViewById(R.id.button);
        boton2=(Button)findViewById(R.id.button2);
        boton3=(Button)findViewById(R.id.button3);
        //image=(ImageView)findViewById(R.id.imageView);
        //edit= (EditText)findViewById(R.id.editText);
        act= (EditText)findViewById(R.id.editText_activity);
        grade = (EditText)findViewById(R.id.editText_grade);
        myDb=new DatabaseHelper(this);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                boolean isInserted = myDb.insertData(act.getText().toString(),
                        grade.getText().toString());
                if (isInserted=true)
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
                break;
            case R.id.button2:
                image.setImageResource((R.mipmap.ic_launcher2));
                break;
            case R.id.button3:
                Intent intent=new Intent(this,Main2Activity.class);
                String dato=edit.getText().toString();
                intent.putExtra("DATO",dato);
                startActivity(intent);
            default:
                break;
        }
    }
}
