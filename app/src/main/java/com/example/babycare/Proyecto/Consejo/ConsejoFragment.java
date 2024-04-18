package com.example.babycare.Proyecto.Consejo;

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

public class ConsejoFragment extends Fragment {
    public static final String INFO_CONSEJO = "info de un consejo" ;
    RecyclerView rvConsejos;
    ConsejoAdapter adapter;
    private ConsejoViewModel consejoViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_consejos, container, false);
        rvConsejos = layout.findViewById(R.id.rcvListaConsejos);

        rvConsejos.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new ConsejoAdapter(new ArrayList<>()); // Adapter sin datos inicialmente
        rvConsejos.setAdapter(adapter);

        adapter.setClickListener(new ConsejoAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Consejo consejo) {
                Intent i = new Intent(ConsejoFragment.super.getActivity(), ConsejoDetalles.class);
                i.putExtra(INFO_CONSEJO,consejo);
                startActivity(i);
            }
        });

        // Inicializa y observa los cambios en el ViewModel
        consejoViewModel = new ViewModelProvider(this).get(ConsejoViewModel.class);
        consejoViewModel.getConsejos().observe(getViewLifecycleOwner(), consejos -> {
            // Actualiza los datos del adaptador cuando cambia la lista de consejos en el ViewModel
            adapter.setConsejos(consejos);
        });



        return layout;
    }
}