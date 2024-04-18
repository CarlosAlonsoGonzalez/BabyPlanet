package com.example.babycare.Proyecto;

import static com.example.babycare.Proyecto.Actividad.ActividadesFragment.INFO_ACTIVIDAD;
import static com.example.babycare.Proyecto.Consejo.ConsejoFragment.INFO_CONSEJO;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babycare.Proyecto.Actividad.Actividad;
import com.example.babycare.Proyecto.Actividad.ActividadDetalles;
import com.example.babycare.Proyecto.Consejo.Consejo;
import com.example.babycare.Proyecto.Consejo.ConsejoDetalles;
import com.example.babycare.R;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    TextView tvActividadHome, tvConsejoHome;
    ImageView ivActividadHome, ivConsejoHome;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        tvActividadHome = layout.findViewById(R.id.tvActividadHome);
        tvConsejoHome = layout.findViewById(R.id.tvConsejoHome);
        ivActividadHome = layout.findViewById(R.id.ivActividadHome);
        ivConsejoHome = layout.findViewById(R.id.ivConsejoHome);

        Random random = new Random();
        int numeroAleatorioCon = random.nextInt(22) + 1;
        int numeroAleatorioAct = random.nextInt(7) + 18;

        ServicioApiHome serHome = ServicioApiHome.getInstancia();
        Call<Consejo> llamada = serHome.getRepo().getConsejo(numeroAleatorioCon);
        Call<Actividad> llamadaAc = serHome.getRepo().getActividad(numeroAleatorioAct);

        llamadaAc.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                //vas desglosando las clases hasta llegar a la base
                Actividad a = response.body();

                tvActividadHome.setText(a.getNombre());
                ivActividadHome.setImageResource(a.getIcono());

                tvActividadHome.setOnClickListener((v)->{
                    Intent i = new Intent(HomeFragment.super.getActivity(), ActividadDetalles.class);
                    i.putExtra(INFO_ACTIVIDAD, a);
                    startActivity(i);
                });

                ivActividadHome.setOnClickListener((v)->{
                    Intent i = new Intent(HomeFragment.super.getActivity(), ActividadDetalles.class);
                    i.putExtra(INFO_ACTIVIDAD, a);
                    startActivity(i);
                });
            }

            @Override
            public void onFailure(Call<Actividad> call, Throwable t) {
                String nuncaToast = "Debug";
            }
        });
        llamada.enqueue(new Callback<Consejo>() {
            @Override
            public void onResponse(Call<Consejo> call, Response<Consejo> response) {
                //vas desglosando las clases hasta llegar a la base
                Consejo c = response.body();

                tvConsejoHome.setText(c.getNombre());
                ivConsejoHome.setImageResource(c.getIcono());

                tvConsejoHome.setOnClickListener((v)->{
                    Intent i = new Intent(HomeFragment.super.getActivity(), ConsejoDetalles.class);
                    i.putExtra(INFO_CONSEJO, c);
                    startActivity(i);
                });

                ivConsejoHome.setOnClickListener((v)->{
                    Intent i = new Intent(HomeFragment.super.getActivity(), ConsejoDetalles.class);
                    i.putExtra(INFO_CONSEJO, c);
                    startActivity(i);
                });
            }

            @Override
            public void onFailure(Call<Consejo> call, Throwable t) {
                String nuncaToast = "Debug";
            }
        });

        return layout;
    }
}