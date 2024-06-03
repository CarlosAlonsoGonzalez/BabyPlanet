package com.example.babycare.Proyecto.Actividad;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babycare.Proyecto.Home.Home;
import com.example.babycare.Proyecto.Perfil.PerfilViewModel;
import com.example.babycare.Proyecto.Perfil.RepoPerfil;
import com.example.babycare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesFragment extends Fragment {
    public static final String INFO_ACTIVIDAD = "into de una actividad" ;
    RecyclerView rvActividades;
    private ActividadAdapter adapter;
    private static ActividadViewModel actividadViewModel;
    PerfilViewModel perfilViewModel;
    int userId;
    int rangoEdadHijo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_actividades, container, false);
        rvActividades = layout.findViewById(R.id.rcvListaActividades);

        //cogemos el id del user
        Home homeActivity = (Home) getActivity();
        if (homeActivity != null) {
            userId = homeActivity.getUserId();
        }

        rvActividades.setLayoutManager(new LinearLayoutManager(ActividadesFragment.super.getContext()));

        adapter = new ActividadAdapter(new ArrayList<>());
        rvActividades.setAdapter(adapter);

        //pillamos por el id le info del hijo
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        perfilViewModel.getPerfil(userId).observe(getViewLifecycleOwner(), perfil -> {
            if (perfil.getHijos() != null && perfil.getHijos().size() > 0) {
                rangoEdadHijo = perfil.getHijos().get(0).getEdad();
            }else{
                //dejamos por defecto uno y asi si no tiene hijo igualmente muestra cosas
                rangoEdadHijo=1;
            }
        });

        // Inicializa y observa los cambios en el ViewModel
        actividadViewModel = new ViewModelProvider(this).get(ActividadViewModel.class);

        actividadViewModel.getActividades(null,rangoEdadHijo).observe(getViewLifecycleOwner(), actividades -> {
            // Actualiza los datos del adaptador cuando cambia la lista de consejos en el ViewModel
            adapter.setActividades(actividades);
            adapter.notifyDataSetChanged();
        });

        adapter.setClickListener(new ActividadAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Actividad unaActividad) {
                Intent i = new Intent(ActividadesFragment.super.getActivity(), ActividadDetalles.class);
                i.putExtra(INFO_ACTIVIDAD,unaActividad);
                startActivity(i);
            }
        });

        return layout;

    }

    public static void cambiarActividadesAdapter(int rango, String areaDesarrollo){

        String area = (areaDesarrollo.matches("^--.*"))? null : areaDesarrollo.replaceAll("area","").trim();

        if(rango!=0 && area==null){
            actividadViewModel.generarActividadesPorRango(rango);
        } else if (rango==0 && area!=null) {
            actividadViewModel.generarActividadesPorAreaDesarrollo(area);
        } else {
           actividadViewModel.generarActividadesPorAreaDesarrlloYRango(area,rango);
        }
    }
}