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

import com.example.babycare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesFragment extends Fragment {
    public static final String INFO_ACTIVIDAD = "into de una actividad" ;
    RecyclerView rvActividades;
    static ActividadAdapter adapter;
    private ActividadViewModel actividadViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_actividades, container, false);
        rvActividades = layout.findViewById(R.id.rcvListaActividades);

        rvActividades.setLayoutManager(new LinearLayoutManager(ActividadesFragment.super.getContext()));

        adapter = new ActividadAdapter(new ArrayList<>());
        rvActividades.setAdapter(adapter);

        // Inicializa y observa los cambios en el ViewModel
        actividadViewModel = new ViewModelProvider(this).get(ActividadViewModel.class);

        actividadViewModel.getActividades().observe(getViewLifecycleOwner(), actividades -> {
            // Actualiza los datos del adaptador cuando cambia la lista de consejos en el ViewModel
            adapter.setActividades(actividades);
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
        ServicioApiActividades ser = ServicioApiActividades.getInstancia();
        Call<List<Actividad>> llamadaFiltro = null;

        if(rango!=0 && areaDesarrollo==null){
            llamadaFiltro = ser.getRepo().getActividadesPorRango(rango);
        } else if (rango==0 && areaDesarrollo!=null) {
            String areaDesarrolloBd = areaDesarrollo.replaceAll("area","").trim();
            llamadaFiltro = ser.getRepo().getActividadesPorAreaDesarrollo(areaDesarrolloBd);
        } else {
            String areaDesarrolloBd = areaDesarrollo.replaceAll("area","").trim();
            llamadaFiltro = ser.getRepo().getActividadesPorRangoYAreaDesarrollo(rango, areaDesarrolloBd);
        }

        llamadaFiltro.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Actividad> listadoFiltrado = new ArrayList<>(response.body());
                    //ArrayList<Actividad> actividadesProcesadas = Actividad.generador(listaActividades);
                    adapter.setActividades(listadoFiltrado);
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                System.out.println("Error en la llamada de filtro Actividad(rango: "+rango+", areaDesarrollo:"+areaDesarrollo+")\n"+ t.getMessage());
            }
        });

    }
}