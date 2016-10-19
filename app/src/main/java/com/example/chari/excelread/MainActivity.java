package com.example.chari.excelread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SqlLiteDbHelper dbHelper;
    Contact contacts ;
    Spinner SpinCity;
    String SpinSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new SqlLiteDbHelper(this);
        SpinCity=(Spinner)findViewById(R.id.SpinCity);

        dbHelper.openDataBase();
        dbHelper.getdata();
        contacts = new Contact();
        SpinAdapt();
    }


    public void SpinAdapt()
    {
        ArrayList<String> getdata = new ArrayList<String>();
        //getdata.add(dbHelper.getdata());


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getdata());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinCity.setAdapter(adapter1);




    }


    public void Getdata(View view)
    {

        SpinSelected=SpinCity.getSelectedItem().toString();
        Log.e("Spinner get",SpinSelected);
        Log.e("on click","log");

        dbHelper.getCityId(SpinSelected);


        // dbHelper.getdata();

        // contacts = dbHelper.Get_ContactDetails();
        TextView tv1 = (TextView)findViewById(R.id.textView1);

        //tv1.setText("Name: "+contacts.getName()+"\n Mobile No: "+contacts.getMobileNo());



    }
}
