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
        RecyclerView rvActividades;
        ActividadAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_actividades, container, false);
        rvActividades = layout.findViewById(R.id.rcvListaActividades);

        rvActividades.setLayoutManager(new LinearLayoutManager(ActividadesFragment.super.getContext()));

        adapter = new ActividadAdapter(Actividad.generador());
        rvActividades.setAdapter(adapter);

        /*adapter.setClickListener(new ActividadAdapter().ItemClickListener() {
            @Override
            public void onClick(View view, int position, Actividad unaActividad) {

                Intent i = new Intent(APListadoConsejos.this, APDetallesConsejos.class);
                i.putExtra(INFO_CONSEJO,consejillo);

                //i.putExtra(INFO_TITULO, consejillo.getTitulo());
                //i.putExtra(INFO_DESCRIPCION, consejillo.getDescripcion());
                //i.putExtra(INFO_EDAD_MESES, String.valueOf(consejillo.getEdadMeses()));

                startActivity(i);
            }
        });*/

        return layout;

    }
}