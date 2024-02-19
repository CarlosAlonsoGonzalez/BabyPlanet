package com.example.babycare.Proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babycare.R;

public class ConsejosFragment extends Fragment {

    RecyclerView rvConsejos;
    ConsejosAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_consejos, container, false);
        rvConsejos = layout.findViewById(R.id.rcvListaConsejos);

        rvConsejos.setLayoutManager(new LinearLayoutManager(ConsejosFragment.super.getContext()));

        adapter = new ConsejosAdapter(Consejos.generador());
        rvConsejos.setAdapter(adapter);
        // Inflate the layout for this fragment
        return layout;


    }
}