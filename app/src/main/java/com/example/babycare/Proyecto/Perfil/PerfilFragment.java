package com.example.babycare.Proyecto.Perfil;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.babycare.Proyecto.Home.Home;
import com.example.babycare.Proyecto.Rango;
import com.example.babycare.R;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    EditText etNombreUsuario, etCorreoUsuario, etContrasenaUsuario, etNombreHijo, etContrasenaAntigua, etContrasenaNueva, etContrasenaNuevaConfirm;
    Spinner spEdadHijo;
    TextView tvCambiarContrasena, tvErrorContrasenaAntigua, tvErrorContrasenaNueva;
    Button btAceptarContrasenaAntigua, btCancelarContrasenaAntigua, btAceptarContrasenaNueva, btCancelarContrasenaNueva, btModificarDatos;
    PerfilViewModel perfilViewModel;
    String contrasenaAntigua;
    int userId;
    int hijoId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_perfil, container, false);

        Home homeActivity = (Home) getActivity();
        if (homeActivity != null) {
            userId = homeActivity.getUserId();
        }

        etNombreUsuario = layout.findViewById(R.id.etNombreUsuario);
        etCorreoUsuario = layout.findViewById(R.id.etCorreoUsuario);
        etContrasenaUsuario = layout.findViewById(R.id.etContrasenaUsuario);
        tvCambiarContrasena = layout.findViewById(R.id.tvCambiarContrasena);
        etNombreHijo = layout.findViewById(R.id.etNombreHijo);
        spEdadHijo = layout.findViewById(R.id.spRangoEdad);//SPINNER
        btModificarDatos = layout.findViewById(R.id.btModificarDatos);

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        perfilViewModel.getPerfil(userId).observe(getViewLifecycleOwner(), perfil -> {
            etNombreUsuario.setText(perfil.getNombreUsuario());
            etCorreoUsuario.setText(perfil.getEmail());
            etContrasenaUsuario.setText(perfil.getPassword());
            contrasenaAntigua = perfil.getPassword();
            if (perfil.getHijos() != null && perfil.getHijos().size() > 0) {
                etNombreHijo.setText(perfil.getHijos().get(0).getNombreHijo());
                spEdadHijo.setSelection(perfil.getHijos().get(0).getEdad());
                hijoId = perfil.getHijos().get(0).getId();
            }

            tvCambiarContrasena.setOnClickListener((v)->{
                AlertDialog.Builder e = new AlertDialog.Builder(getContext());
                LayoutInflater in = getLayoutInflater();
                View vi = in.inflate(R.layout.contrasena, null);
                e.setView(vi);

                btAceptarContrasenaAntigua = (Button) vi.findViewById(R.id.btAceptarContrasenaAntigua);
                btCancelarContrasenaAntigua = (Button) vi.findViewById(R.id.btCancelarContrasenaAntigua);
                etContrasenaAntigua = (EditText) vi.findViewById(R.id.etContrasenaAntiguaInput);
                tvErrorContrasenaAntigua = (TextView) vi.findViewById(R.id.tvErrorContrasenaAntigua);
                tvErrorContrasenaAntigua.setVisibility(View.INVISIBLE);

                AlertDialog ad = e.create();

                btAceptarContrasenaAntigua.setOnClickListener((vis) ->{
                    tvErrorContrasenaAntigua.setVisibility(View.INVISIBLE);
                    if(contrasenaAntigua != null && !contrasenaAntigua.equals(etContrasenaAntigua.getText().toString())){
                        tvErrorContrasenaAntigua.setVisibility(View.VISIBLE);
                        return;
                    }
                    AlertDialog.Builder f = new AlertDialog.Builder(getContext());
                    LayoutInflater li = getLayoutInflater();
                    View vx = li.inflate(R.layout.confirmar_contrasena, null);
                    f.setView(vx);

                    btAceptarContrasenaNueva = (Button) vx.findViewById(R.id.btAceptarContrasenaNueva);
                    btCancelarContrasenaNueva = (Button) vx.findViewById(R.id.btCancelarContrasenaNueva);
                    etContrasenaNueva = (EditText) vx.findViewById(R.id.etContrasenaNuevaInput);
                    etContrasenaNuevaConfirm = (EditText) vx.findViewById(R.id.etContrasenaNuevaInputConf);
                    tvErrorContrasenaNueva = (TextView) vx.findViewById(R.id.tvErrorContrasenaNueva);
                    tvErrorContrasenaNueva.setVisibility(View.INVISIBLE);

                    AlertDialog ad2 = f.create();

                    btAceptarContrasenaNueva.setOnClickListener((v2)->{
                        String contrasenaNueva = etContrasenaNueva.getText().toString();
                        String contrasenaNuevaConfirm = etContrasenaNuevaConfirm.getText().toString();
                        if(!contrasenaNueva.equals(contrasenaNuevaConfirm)){
                            tvErrorContrasenaNueva.setVisibility(View.VISIBLE);
                            return;
                        }
                        Hijo hijo = new Hijo(hijoId,etNombreHijo.getText().toString(), Rango.obtenerDecripcionPorCodigo(spEdadHijo.getSelectedItem().toString()), userId);
                        List<Hijo> hijos = new ArrayList<>();
                        hijos.add(hijo);
                        Usuario usuario = new Usuario(userId, etNombreUsuario.getText().toString(), etCorreoUsuario.getText().toString(), contrasenaNuevaConfirm, hijos);
                        perfilViewModel.modificarPerfil(usuario);
                        etContrasenaUsuario.setText(contrasenaNuevaConfirm);

                        ad.dismiss();
                        ad2.dismiss();
                    });
                    btCancelarContrasenaNueva.setOnClickListener((v3)->{
                        ad.dismiss();
                        ad2.dismiss();
                    });
                    ad2.show();
                });
                btCancelarContrasenaAntigua.setOnClickListener((vist)->{
                    ad.dismiss();
                });
                ad.show();
            });
        });

        btModificarDatos.setOnClickListener((vie) -> {
            String nombreUsuario = etNombreUsuario.getText().toString();
            String email = etCorreoUsuario.getText().toString();
            String password = etContrasenaUsuario.getText().toString();
            String nombreHijo = etNombreHijo.getText().toString();
            String edad = spEdadHijo.getSelectedItem().toString();
            int rango = Rango.obtenerDecripcionPorCodigo(edad);

            if(!TextUtils.isEmpty(nombreUsuario) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) &&!TextUtils.isEmpty(nombreHijo)){
                Hijo hijo = new Hijo(hijoId,nombreHijo, rango, userId);
                List<Hijo> hijos = new ArrayList<>();
                hijos.add(hijo);
                Usuario usuario = new Usuario(userId, nombreUsuario, email, password, hijos);
                perfilViewModel.modificarPerfil(usuario);

                perfilViewModel.getSuccessMessage().observe(getViewLifecycleOwner(), success -> {
                    if (success != null && success) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Éxito")
                                .setMessage("El cambio se ha efectuado correctamente")
                                .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                                .show();

                        perfilViewModel.resetSuccessMessage();
                    }
                });

            }
        });
        return layout;
    }

}