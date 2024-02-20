package com.example.babycare.Proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babycare.R;

public class ActividadesFragment extends Fragment {
    public static final String INFO_ACTIVIDAD = "into de una actividad" ;
    RecyclerView rvActividades;
        ActividadAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_actividades, container, false);
        rvActividades = layout.findViewById(R.id.rcvListaActividades);

        rvActividades.setLayoutManager(new LinearLayoutManager(ActividadesFragment.super.getContext()));

        adapter = new ActividadAdapter(Actividad.generador(this));
        rvActividades.setAdapter(adapter);

        adapter.setClickListener(new ActividadAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Actividad unaActividad) {
                Intent i = new Intent(ActividadesFragment.super.getActivity(), ActividadDetalles.class);//no se si esto funciona la verdad eh esta apañado por los pelos y sin sentido
                i.putExtra(INFO_ACTIVIDAD,unaActividad);

                startActivity(i);
            }
        });

        return layout;

    }
}