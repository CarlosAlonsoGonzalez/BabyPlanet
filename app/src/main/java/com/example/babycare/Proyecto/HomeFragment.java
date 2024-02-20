package com.example.babycare.Proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        int numeroAleatorio = random.nextInt(22) + 1;

        ServicioApiHome serHome = ServicioApiHome.getInstancia();
        Call<Consejo> llamada = serHome.getRepo().getConsejo(numeroAleatorio);

        llamada.enqueue(new Callback<Consejo>() {
            @Override
            public void onResponse(Call<Consejo> call, Response<Consejo> response) {
                //vas desglosando las clases hasta llegar a la base
                Consejo c = response.body();

                tvConsejoHome.setText(c.getNombre());
            }

            @Override
            public void onFailure(Call<Consejo> call, Throwable t) {
                String nuncaToast = "Debug";
            }
        });
        return layout;
    }
}