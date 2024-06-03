package com.example.babycare.Proyecto.Inicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.babycare.Proyecto.Home.Home;
import com.example.babycare.Proyecto.Perfil.PerfilViewModel;
import com.example.babycare.R;

public class Login extends AppCompatActivity {
    public static final String ID_USUARIO = "id del usuario" ;
    public static final String DATOS_INCORRECTOS="El correo o la contraseña son incorrectos";
    EditText etCorreo, etContraseña;
    Button btInicio, btRegistro;
    PerfilViewModel perfilViewModel;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);
        getSupportActionBar().hide();
        builder = new AlertDialog.Builder(this);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        btInicio = findViewById(R.id.btInicio);
        btRegistro= findViewById(R.id.btRegistro);
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        btInicio.setOnClickListener((v)->{

            String email = etCorreo.getText().toString();
            String password = etContraseña.getText().toString();

            perfilViewModel.login(email, password);
            perfilViewModel.getRespuestaLogin().observe(this, respuestaLogin -> {
                if (respuestaLogin.isDatosCorrectos()) {
                    Intent i = new Intent(this, Home.class);
                    i.putExtra(ID_USUARIO, respuestaLogin.getUserId().intValue());
                    startActivity(i);
                } else {
                    builder.setMessage(DATOS_INCORRECTOS)
                            .setPositiveButton("Entendido", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        });

        btRegistro.setOnClickListener((v)->{
            Intent i = new Intent(this, Registro.class);
            startActivity(i);
        });
    }

}