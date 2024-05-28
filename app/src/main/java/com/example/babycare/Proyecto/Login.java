package com.example.babycare.Proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.babycare.Proyecto.Home.Home;
import com.example.babycare.R;

public class Login extends AppCompatActivity {

    EditText etCorreo, etContraseña;
    Button btInicio, btRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);
        getSupportActionBar().hide();
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        btInicio = findViewById(R.id.btInicio);
        btRegistro= findViewById(R.id.btRegistro);

        btInicio.setOnClickListener((v)->{
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        });

        btRegistro.setOnClickListener((v)->{
            Intent i = new Intent(this, Registro.class);
            startActivity(i);
        });
    }
}