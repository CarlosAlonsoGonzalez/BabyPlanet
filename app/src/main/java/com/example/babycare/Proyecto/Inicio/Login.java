package com.example.babycare.Proyecto.Inicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
    EditText etCorreo, etContraseña;
    TextView tvErrLogin;
    Button btInicio, btRegistro;
    PerfilViewModel perfilViewModel;
    RespuestaLogin respuestaLogin;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);
        getSupportActionBar().hide();
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        btInicio = findViewById(R.id.btInicio);
        btRegistro= findViewById(R.id.btRegistro);
        tvErrLogin = findViewById(R.id.tvErrLogin);
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        btInicio.setOnClickListener((v)->{

            tvErrLogin.setText("");
             email = etCorreo.getText().toString();
             password = etContraseña.getText().toString();

            perfilViewModel.login(email, password);
            perfilViewModel.getRespuestaLogin().observe(this, respuestaLogin -> {
                if (respuestaLogin.isDatosCorrectos()) {
                    Intent i = new Intent(this, Home.class);
                    i.putExtra(ID_USUARIO, respuestaLogin.getUserId());
                    startActivity(i);
                } else {
                    tvErrLogin.setText("No existe ese usuario");
                }
            });
        });

        btRegistro.setOnClickListener((v)->{
            tvErrLogin.setText("");
            Intent i = new Intent(this, Registro.class);
            startActivity(i);
        });
    }

}