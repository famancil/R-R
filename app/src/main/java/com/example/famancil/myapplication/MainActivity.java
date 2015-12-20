package com.example.famancil.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    SQLiteDatabase db;
    EditText edit_activity,edit_surname,edit_grade;
    Button btn_addData,btn_viewdata,btn_reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB =new DatabaseHelper(this);

        edit_activity=(EditText)findViewById(R.id.editActivity);
        //edit_surname=(EditText)findViewById(R.id.editSurname);
        edit_grade=(EditText)findViewById(R.id.editGrades);
        btn_addData=(Button)findViewById(R.id.btnAdd);
        btn_viewdata= (Button)findViewById(R.id.button_viewall);
        btn_reset=(Button)findViewById(R.id.button_reset);
        addData();
        viewData();
        dropTable();
        //myDB =new DatabaseHelper(this);
    }
    public void viewData(){
        btn_viewdata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getAllData();
                        if (res.getCount() == 0) {
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
                    }
                }
        );
    }
    public void dropTable(){
        btn_reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // db.delete(String tableName, String whereClause, String[] whereArgs);
                        // If whereClause is null, it will delete all rows.
                        SQLiteDatabase db = myDB.getWritableDatabase(); //get database
                        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + myDB.TABLE_NAME + "'");
                        db.execSQL("DELETE FROM " + myDB.TABLE_NAME); //delete all rows in a table
                        db.close();
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
    public void addData(){
        btn_addData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(edit_activity.getText().toString(),
                                edit_grade.getText().toString());
                        if (isInserted=true)
                            Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
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
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

