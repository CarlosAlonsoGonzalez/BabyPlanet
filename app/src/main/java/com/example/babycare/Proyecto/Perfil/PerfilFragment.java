package com.example.babycare.Proyecto.Perfil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.babycare.R;

public class PerfilFragment extends Fragment {

    EditText etNombreUsuario, etApellidoUsuario, etCorreoUsuario, etContrasenaUsuario, etNombreHijo, etEdadHijo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_perfil, container, false);

        etNombreUsuario = layout.findViewById(R.id.etNombreUsuario);
        etApellidoUsuario = layout.findViewById(R.id.etApellidoUsuario);
        etCorreoUsuario = layout.findViewById(R.id.etCorreoUsuario);
        etContrasenaUsuario = layout.findViewById(R.id.etContrasenaUsuario);
        etNombreHijo = layout.findViewById(R.id.etNombreHijo);
        etEdadHijo = layout.findViewById(R.id.etEdadHijo);

        return layout;
    }
}