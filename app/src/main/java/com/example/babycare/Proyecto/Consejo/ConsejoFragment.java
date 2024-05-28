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

import com.example.babycare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsejoFragment extends Fragment {
    public static final String INFO_CONSEJO = "info de un consejo" ;
    RecyclerView rvConsejos;
    static ConsejoAdapter adapter;
    private ConsejoViewModel consejoViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_consejos, container, false);
        rvConsejos = layout.findViewById(R.id.rcvListaConsejos);

        rvConsejos.setLayoutManager(new GridLayoutManager(this.getContext(), 3));

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

    public static void cambiarConsejosAdapter(int rango, String tipo){
        ServicioApiConsejos ser = ServicioApiConsejos.getInstancia();
        Call<List<Consejo>> llamadaFiltro = null;

        if(rango!=0 && tipo==null){
            llamadaFiltro = ser.getRepo().getConsejosPorRango(rango);
        } else if (rango==0 && tipo!=null) {
            llamadaFiltro = ser.getRepo().getConsejosPorTipo(tipo);
        } else {
            llamadaFiltro = ser.getRepo().getConsejoPorRangoYTipo(rango, tipo);
        }

        llamadaFiltro.enqueue(new Callback<List<Consejo>>() {
            @Override
            public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Consejo> listadoFiltrado = new ArrayList<>(response.body());
                    //ArrayList<Consejo> consejosProcesadas = Consejo.generador(listaActividades);
                    adapter.setConsejos(listadoFiltrado);
                }
            }

            @Override
            public void onFailure(Call<List<Consejo>> call, Throwable t) {
                System.out.println("Error en la llamada de filtro Consejo(rango: "+rango+", tipo:"+tipo+")\n"+ t.getMessage());
            }
        });
    }
}