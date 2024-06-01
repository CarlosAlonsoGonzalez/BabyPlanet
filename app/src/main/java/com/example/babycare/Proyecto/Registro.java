package com.example.babycare.Proyecto;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.babycare.Proyecto.Perfil.Hijo;
import com.example.babycare.Proyecto.Perfil.PerfilViewModel;
import com.example.babycare.Proyecto.Perfil.RepoPerfil;
import com.example.babycare.Proyecto.Perfil.ServicioApiPerfil;
import com.example.babycare.Proyecto.Perfil.Usuario;
import com.example.babycare.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    public static final String ERROR_DATOS = "Los campos de correo, usuario y contraseña son obligatorios.";
    public static final String CORREO_INCORRECTO = "Correo no válido.";
    public static final String CONTRASEÑAS_NO_COINCIDEN = "La contraseña no coincide";
    EditText etNombreUsuario, etCorreoUsuario, etContrasena, etConfirmarContrasena,
            etNombreHijo, etEdadHijo;
    TextView tvErrCampos, tvErrContraseña, tvCorreo;
    Button btRegistro, btVolver;
    String nombreUsuario;
    RepoPerfil repoPerfil;
    PerfilViewModel perfilViewModel;
    String email;
    String password;
    String nombreHijo;
    String regex = "^[\\w.-]+@([\\w-]+\\.)+com$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;
    Usuario usuario;
    List<Hijo> hijos = new ArrayList<>();
    int edadHijo;
    boolean errores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro);
        getSupportActionBar().hide();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etNombreUsuario = findViewById(R.id.etNombreUsuarioReg);
        etCorreoUsuario = findViewById(R.id.etCorreoUsuarioReg);
        etContrasena = findViewById(R.id.etContrasenaRegistro);
        etConfirmarContrasena = findViewById(R.id.etConfirmarContrasena);
        etNombreHijo = findViewById(R.id.etNombreHijoReg);
        //  etEdadHijo = findViewById(R.id.etEdadHijo);
        btRegistro = findViewById(R.id.btRegistro);
        //  btVolver = findViewById(R.id.btVolver);
        tvCorreo = findViewById(R.id.tvErrCorreo);
        tvErrCampos = findViewById(R.id.tvErrFaltanCampos);
        tvErrContraseña = findViewById(R.id.tvErrCoincidencia);


        repoPerfil = ServicioApiPerfil.getRepo();
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);


        btRegistro.setOnClickListener(v -> {
            errores = false;
            tvErrCampos.setText("");
            tvCorreo.setText("");
            tvErrContraseña.setText("");
            nombreUsuario = etNombreUsuario.getText().toString();
            email = etCorreoUsuario.getText().toString();
            password = etContrasena.getText().toString();
            nombreHijo = etNombreHijo.getText().toString();
            regex = "^[\\w.-]+@([\\w-]+\\.)+com$";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(email);
            edadHijo = 2;

            if (nombreUsuario.isEmpty() || email.isEmpty() || password.isEmpty()) {
                tvErrCampos.setText(ERROR_DATOS);
                errores = true;
            }
            if (!password.equals(etConfirmarContrasena.getText().toString()) || !errores && (etConfirmarContrasena.getText().toString()).isEmpty()) {
                tvErrContraseña.setText(CONTRASEÑAS_NO_COINCIDEN);
                errores = true;
            }
            if (!matcher.matches() && !email.isEmpty()) {
                tvCorreo.setText(CORREO_INCORRECTO);
                errores = true;
            }
            if (!errores) {
                if (!nombreHijo.isEmpty() || edadHijo != 0) {
                    Hijo hijo = new Hijo(nombreHijo, edadHijo);
                    hijos.add(hijo);
                }
                usuario = new Usuario(nombreUsuario, email, password, hijos);
                tvErrCampos.setText(":)");

                perfilViewModel.crearUsuario(usuario);
                perfilViewModel.getSuccessMessage().observe(this, success -> {
                    if (success != null && success) {
                        new AlertDialog.Builder(this)
                                .setTitle("Éxito")
                                .setMessage("El usuario se ha registrado correctamente")
                                .setPositiveButton("Aceptar", (dialog, which) -> {
                                    dialog.dismiss();
                                    finish();
                                })
                                .show();
                        perfilViewModel.resetSuccessMessage();
                    }
                });


            }

        });
    }
}