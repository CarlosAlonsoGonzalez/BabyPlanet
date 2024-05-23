package com.example.babycare.Proyecto;

import static com.example.babycare.Proyecto.Actividad.ActividadesFragment.INFO_ACTIVIDAD;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
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

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static int rangoHijo =1;
    private static Random random = new Random();
    TextView tvNombreUsuario;
    View itemActividad, itemConsejo;
    TextView tvTextoActividad, getTvTextoConsejo;
    ImageView ivIconoActividad, ivIconoConsejo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        tvNombreUsuario=layout.findViewById(R.id.tvNombreUsuario);
        itemActividad = layout.findViewById(R.id.itemActividad);
        itemConsejo = layout.findViewById(R.id.itemConsejo);

        //TODO este id seria el de el usuario al inciar sesion (a las malas con un singleton con toda la info del usurio pillada desde el login)
        int id=0;
        ServicioApiHome serviceHome = ServicioApiHome.getInstancia();
        //Call<Usuario> llamadaInfoUsuario = serviceHome.getRepo().getUsuarioPorId(id);

        //Call<List<Consejo>> llamadaConsejos = serviceHome.getRepo().getConsejosPorEdad(rangoHijo);
        Call<List<Consejo>> llamadaConsejos = serviceHome.getRepo().getConsejos();
        //Call<List<Actividad>> llamadaActividades = serviceHome.getRepo().getActividadesPorEdad(rangoHijo);
        Call<List<Actividad>> llamadaActividades = serviceHome.getRepo().getActividades();

        /*llamadaInfoUsuario.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario user = response.body();
                rangoHijo = user.getHijos()[0].getEdad();
                tvNombreUsuario.setText(user.getNombreUsuario());
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                String nuncaToast = "Debug";
            }
        });*/

        llamadaActividades.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                List<Actividad> actividadesPorEdadHijo = response.body();

                int indiceAleatorio = random.nextInt(actividadesPorEdadHijo.size());

                Actividad actividadRandom = actividadesPorEdadHijo.get(indiceAleatorio);

                tvTextoActividad = itemActividad.findViewById(R.id.tvTextoCorrespondiente);
                ivIconoActividad = itemActividad.findViewById(R.id.ivIcono);

                tvTextoActividad.setText(actividadRandom.getNombre());
                int color = ContextCompat.getColor(getContext(), R.color.Principal);
                ivIconoActividad.setBackgroundColor(color);
                ivIconoActividad.setImageResource(actividadRandom.getIcono());

                itemActividad.setOnClickListener((v)->{
                    Intent i = new Intent(HomeFragment.super.getActivity(), ActividadDetalles.class);
                    i.putExtra(INFO_ACTIVIDAD, actividadRandom);
                    startActivity(i);
                });
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                String nuncaToast = "Debug";
            }
        });
        llamadaConsejos.enqueue(new Callback<List<Consejo>>() {
            @Override
            public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                List<Consejo> consejosPorEdadHijo = response.body();

                //Hacer que no coja otro si es el mismo dia.
                int indiceAleatorio = random.nextInt(consejosPorEdadHijo.size());

                Consejo consejoRandom = consejosPorEdadHijo.get(indiceAleatorio);

                getTvTextoConsejo = itemConsejo.findViewById(R.id.tvTextoCorrespondiente);
                ivIconoConsejo = itemConsejo.findViewById(R.id.ivIcono);

                getTvTextoConsejo.setText(consejoRandom.getTipo());
                int color = ContextCompat.getColor(getContext(), consejoRandom.getColorFondo());
                ivIconoConsejo.setBackgroundColor(color);
                ivIconoConsejo.setImageResource(consejoRandom.getIcono());

                itemConsejo.setOnClickListener((v)->{
                    Intent i = new Intent(HomeFragment.super.getActivity(), ConsejoDetalles.class);
                    i.putExtra(INFO_ACTIVIDAD, consejoRandom);
                    startActivity(i);
                });
            }

            @Override
            public void onFailure(Call<List<Consejo>> call, Throwable t) {
                String nuncaToast = "Debug";
            }
        });

        return layout;
    }
}