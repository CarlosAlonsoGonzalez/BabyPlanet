package com.example.babycare.Proyecto.Consejo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babycare.Proyecto.Home.Home;
import com.example.babycare.Proyecto.Perfil.PerfilViewModel;
import com.example.babycare.R;

import java.util.ArrayList;


public class ConsejoFragment extends Fragment {
    public static final String INFO_CONSEJO = "info de un consejo" ;
    RecyclerView rvConsejos;
    private ConsejoAdapter adapter;
    private static ConsejoViewModel consejoViewModel;
    PerfilViewModel perfilViewModel;
    int userId;
    static int rangoEdadHijo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_consejos, container, false);
        rvConsejos = layout.findViewById(R.id.rcvListaConsejos);
        rvConsejos.setLayoutManager(new GridLayoutManager(this.getContext(), 3));

        adapter = new ConsejoAdapter(new ArrayList<>()); // Adapter sin datos inicialmente
        rvConsejos.setAdapter(adapter);

        //cogemos el id del user
        Home homeActivity = (Home) getActivity();
        if (homeActivity != null) {
            userId = homeActivity.getUserId();
        }

        // Inicializa y observa los cambios en el ViewModel
        consejoViewModel = new ViewModelProvider(this).get(ConsejoViewModel.class);

        //pillamos por el id le info del hijo
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        perfilViewModel.getPerfil(userId).observe(getViewLifecycleOwner(), perfil -> {
            if (perfil.getHijos() != null && perfil.getHijos().size() > 0) {
                rangoEdadHijo = perfil.getHijos().get(0).getEdad();
            }else{
                //dejamos por defecto uno y asi si no tiene hijo igualmente muestra cosas
                rangoEdadHijo=1;
            }
            consejoViewModel.getConsejos(null, rangoEdadHijo).observe(getViewLifecycleOwner(), consejos -> {
                // Actualiza los datos del adaptador cuando cambia la lista de consejos en el ViewModel
                adapter.setConsejos(consejos);
                adapter.notifyDataSetChanged();
            });
        });

        adapter.setClickListener(new ConsejoAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Consejo consejo) {
                Intent i = new Intent(ConsejoFragment.super.getActivity(), ConsejoDetalles.class);
                i.putExtra(INFO_CONSEJO,consejo);
                startActivity(i);
            }
        });

        return layout;
    }

    public static void cambiarConsejosAdapter(int rango, String tipo){

        if(tipo.matches("^--.*")) tipo = null;

        if(rango!=0 && tipo==null){
            consejoViewModel.generarConsejosPorRango(rango);
        } else if (rango==0 && tipo!=null) {
            consejoViewModel.generarConsejosPorTipo(tipo);
        } else {
            consejoViewModel.generarConsejosPorTipoYRango(tipo, rango);
        }





    }
}