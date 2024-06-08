package com.example.babycare.Proyecto.Inicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;

import com.example.babycare.Proyecto.Home.Home;
import com.example.babycare.Proyecto.UsuarioSingleton;
import com.example.babycare.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Login extends AppCompatActivity {
    public static final String ID_USUARIO = "id del usuario" ;
    public static final String DATOS_INCORRECTOS="El correo o la contraseña son incorrectos";
    EditText etCorreo, etContraseña;
    Button btInicio, btRegistro;
    InicioViewModel inicioViewModel;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);
        getSupportActionBar().hide();
        builder = new AlertDialog.Builder(this);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        btInicio = findViewById(R.id.btInicio);
        btRegistro= findViewById(R.id.btRegistro);
        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        if(checkIfFileExists(UsuarioSingleton.NAME_FILE)) {
            Intent i = new Intent(this, Home.class);
            i.putExtra(ID_USUARIO, UsuarioSingleton.getAnfitrion().getId().intValue());
            startActivity(i);
        }
        inicioViewModel.getRespuestaLogin().observe(this, respuestaLogin -> {
            if (respuestaLogin.isDatosCorrectos()) {
                Intent i = new Intent(this, Home.class);
                crearAnfitrionCSV(respuestaLogin.getUserId().intValue());
                i.putExtra(ID_USUARIO, respuestaLogin.getUserId().intValue());
                startActivity(i);
            } else {
                builder.setMessage(DATOS_INCORRECTOS)
                        .setPositiveButton("Entendido", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btInicio.setOnClickListener((v)->{

            String email = etCorreo.getText().toString();
            String password = etContraseña.getText().toString();

            inicioViewModel.login(email, password);

        });

        btRegistro.setOnClickListener((v)->{
            Intent i = new Intent(this, Registro.class);
            startActivity(i);
        });
    }

    private void crearAnfitrionCSV(int idAnfitrion){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), UsuarioSingleton.NAME_FILE);

        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        inicioViewModel.getPerfil(idAnfitrion).observe(this, perfil -> {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos);

                // Escribir los datos del usuario
                //ESTE USUARIO SERIA EL DE LA LLAMADA A LA API AL HACER EL REGISTRO O EL LOGIN
                osw.write(perfil.getId()+"");

                osw.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private boolean checkIfFileExists(String fileName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        return file.exists();
    }

}