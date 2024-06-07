package com.example.babycare.Proyecto.Inicio;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.babycare.Proyecto.Perfil.Hijo;
import com.example.babycare.Proyecto.Perfil.Usuario;
import com.example.babycare.R;

import java.util.ArrayList;

import java.util.List;


public class Registro extends AppCompatActivity {
    public static final String REGEX_CORREO = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String ERROR_DATOS = "Los campos nombre usuario, correo electronico y contraseña son obligatorios.";
    public static final String CORREO_INCORRECTO = "Correo no válido.";
    public static final String CONTRASEÑAS_NO_COINCIDEN = "La contraseña no coincide";
    public static final String ERR_DATOS_HIJO = "Los datos de hijo/a son invalidos";
    EditText etNombreUsuario, etCorreoUsuario, etContrasena, etConfirmarContrasena, etNombreHijo;
    CheckBox chbOptionals;
    Spinner spEdadHijo;
    Button btRegistro, btVolver;
    InicioViewModel inicioViewModel;
    Usuario usuario;
    List<Hijo> hijos = new ArrayList<>();
    boolean hayErrores=true;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        getSupportActionBar().hide();

        etNombreUsuario = findViewById(R.id.etNombreUsuarioReg);
        etCorreoUsuario = findViewById(R.id.etCorreoUsuarioReg);
        etContrasena = findViewById(R.id.etContrasenaRegistro);
        etConfirmarContrasena = findViewById(R.id.etConfirmarContrasena);
        chbOptionals=findViewById(R.id.chbDatosHijo);
        etNombreHijo = findViewById(R.id.etNombreHijoReg);
        spEdadHijo = findViewById(R.id.spRangoEdadReg);
        btRegistro = findViewById(R.id.btRegistro);
        btVolver = findViewById(R.id.btVolver);

        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        builder = new AlertDialog.Builder(this);

        chbOptionals.setOnCheckedChangeListener((buttonView, isChecked) -> {
            etNombreHijo.setEnabled(isChecked);
            spEdadHijo.setEnabled(isChecked);
        });

        btRegistro.setOnClickListener(v -> {
            String nombreUsuario = etNombreUsuario.getText().toString();
            String email = etCorreoUsuario.getText().toString();
            String password = etContrasena.getText().toString();
            String confPassword = etConfirmarContrasena.getText().toString();
            String nombreHijo = etNombreHijo.getText().toString();
            int rango = spEdadHijo.getSelectedItemPosition();

            if (chbOptionals.isChecked()) {
                if (validarCamposObligatorios(nombreUsuario, email, password, confPassword) && validarCamposOpcionales(nombreHijo, rango)) {
                    Hijo hijo = new Hijo(nombreHijo, rango);
                    hijos.add(hijo);
                    usuario = new Usuario(nombreUsuario, email, password, hijos);
                    hayErrores = false;
                } else hayErrores = true;
            } else {
                if (validarCamposObligatorios(nombreUsuario, email, password, confPassword)) {
                    usuario = new Usuario(nombreUsuario, email, password, hijos);
                    hayErrores = false;
                } else hayErrores = true;
            }

            if (!hayErrores) {
                inicioViewModel.crearUsuario(usuario);
                inicioViewModel.getSuccessMessage().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean success) {
                        if (success != null && success) {
                            new AlertDialog.Builder(Registro.this)
                                    .setTitle("Éxito")
                                    .setMessage("El usuario se ha registrado correctamente")
                                    .setPositiveButton("Aceptar", (dialog, which) -> {
                                        dialog.dismiss();
                                        finish();
                                    })
                                    .show();
                            inicioViewModel.resetSuccessMessage();
                        } else {
                            new AlertDialog.Builder(Registro.this)
                                    .setTitle("Error")
                                    .setMessage("Ese correo ya existe")
                                    .setPositiveButton("Aceptar", (dialog, which) -> {
                                        dialog.dismiss();
                                    })
                                    .show();
                        }
                    }
                });
            }
        });

        btVolver.setOnClickListener((v) -> finish());
    }

    private boolean validarCamposObligatorios(String nombreUsuario, String correo, String contrasena, String repContrasena) {
        if (nombreUsuario.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || repContrasena.isEmpty()) {
            builder.setMessage(ERROR_DATOS)
                    .setPositiveButton("Entendido", null);
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }

        if (!contrasena.equals(repContrasena) || repContrasena.isEmpty()) {
            builder.setMessage(CONTRASEÑAS_NO_COINCIDEN)
                    .setPositiveButton("Entendido", null);
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }

        if (!correo.matches(REGEX_CORREO)) {
            builder.setMessage(CORREO_INCORRECTO)
                    .setPositiveButton("Entendido", null);
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        return true;
    }

    private boolean validarCamposOpcionales(String nombreHijo, int rango) {
        if (nombreHijo.isEmpty() || rango == 0) {
            builder.setMessage(ERR_DATOS_HIJO)
                    .setPositiveButton("Entendido", null);
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        return true;
    }
}