package com.example.marcia.myapplication.view.com.example.marcia.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.marcia.myapplication.R;

public class TiposActivity extends AppCompatActivity {

    private Spinner spTipos;
    private String tipos[]={"          ","ArrayList","SQlite","Serviços"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos);

        spTipos = (Spinner)findViewById(R.id.spTipos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_dropdown_item,tipos);
        spTipos.setAdapter(adapter);
        spTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(spTipos.getSelectedItem().toString().equals("ArrayList")){
                    LoginActivity.tipo = 2;
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                if(spTipos.getSelectedItem().toString().equals("SQlite"))
                {
                    LoginActivity.tipo = 1;
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                if(spTipos.getSelectedItem().toString().equals("Serviços"))
                {
                    LoginActivity.tipo = 3;
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }
}
