package com.example.babycare.Proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.babycare.R;

public class Login extends AppCompatActivity {

    Button btEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);
        getSupportActionBar().hide();
        btEntrar = findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener((v)->{
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        });
    }
}