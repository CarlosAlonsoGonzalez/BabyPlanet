package com.example.babycare.Proyecto.Perfil;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.babycare.Proyecto.Rango;
import com.example.babycare.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment {

    EditText etNombreUsuario, etCorreoUsuario, etContrasenaUsuario, etNombreHijo, etContrasenaAntigua, etContrasenaNueva, etContrasenaNuevaConfirm;
    Spinner spEdadHijo;//SPINNER
    TextView tvCambiarContrasena, tvErrorContrasenaAntigua, tvErrorContrasenaNueva;
    Button btAceptarContrasenaAntigua, btCancelarContrasenaAntigua, btAceptarContrasenaNueva, btCancelarContrasenaNueva, btModificarDatos;
    PerfilViewModel perfilViewModel;
    String contrasenaAntigua;
    RepoPerfil repoPerfil;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_perfil, container, false);

        etNombreUsuario = layout.findViewById(R.id.etNombreUsuario);
        etCorreoUsuario = layout.findViewById(R.id.etCorreoUsuario);
        etContrasenaUsuario = layout.findViewById(R.id.etContrasenaUsuario);
        tvCambiarContrasena = layout.findViewById(R.id.tvCambiarContrasena);
        etNombreHijo = layout.findViewById(R.id.etNombreHijo);
        spEdadHijo = layout.findViewById(R.id.spRangoEdad);//SPINNER
        btModificarDatos = layout.findViewById(R.id.btModificarDatos);
        repoPerfil = ServicioApiPerfil.getRepo();

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        int id = 1;
        perfilViewModel.getPerfil(id).observe(getViewLifecycleOwner(), perfil -> {
            etNombreUsuario.setText(perfil.getNombreUsuario());
            etCorreoUsuario.setText(perfil.getEmail());
            etContrasenaUsuario.setText(perfil.getPassword());
            contrasenaAntigua = perfil.getPassword();
            if (perfil.getHijos() != null && perfil.getHijos().length > 0) {
                etNombreHijo.setText(perfil.getHijos()[0].getNombreHijo());
                //TODO MIRA ESTO CARLITOS QUE ES LO QUE PUSE DEL SPINNER
                //etEdadHijo.setText(String.valueOf(perfil.getHijos()[0].getEdad()));
                spEdadHijo.setSelection(perfil.getHijos()[0].getEdad());
            }
        });

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
                if(contrasenaAntigua != null && !contrasenaAntigua.equals(etContrasenaAntigua.getText())){
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
                    //Map<String, Object> actualizaciones = new HashMap<>();
                    //actualizaciones.put("password", etContrasenaNuevaConfirm.getText().toString());

                    //perfilViewModel.actualizarPerfil(actualizaciones);

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

        btModificarDatos.setOnClickListener((vie) -> {
            String nombreUsuario = etNombreUsuario.getText().toString();
            String email = etCorreoUsuario.getText().toString();
            String password = etContrasenaUsuario.getText().toString();
            //actualizaciones.put("edad", etEdadHijo.getText().toString()); SPINNER
            //actualizaciones.put("edad", spEdadHijo.getSelectedItemPosition());



            if(!TextUtils.isEmpty(nombreUsuario) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) ){
                Usuario usuario = new Usuario( id, nombreUsuario, email, password);
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