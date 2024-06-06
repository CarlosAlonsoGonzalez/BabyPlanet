package com.example.babycare.Proyecto.Home;

import static com.example.babycare.Proyecto.Actividad.ActividadesFragment.INFO_ACTIVIDAD;
import static com.example.babycare.Proyecto.Consejo.ConsejoFragment.INFO_CONSEJO;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babycare.Proyecto.Actividad.Actividad;
import com.example.babycare.Proyecto.Actividad.ActividadDetalles;
import com.example.babycare.Proyecto.Consejo.Consejo;
import com.example.babycare.Proyecto.Consejo.ConsejoDetalles;
import com.example.babycare.Proyecto.Perfil.PerfilViewModel;
import com.example.babycare.R;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static int rangoHijo = 1;
    private static Random random = new Random();
    TextView tvNombreUsuario;
    View itemActividad, itemConsejo;
    TextView tvTextoActividad, tvTextoConsejo;
    ImageView ivIconoActividad, ivIconoConsejo;
    int userId;
    PerfilViewModel perfilViewModel;

    private static final String LAST_UPDATE = "last_update";
    private static final String SAVED_ACTIVIDAD = "saved_actividad";
    private static final String SAVED_CONSEJO = "saved_consejo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        Home homeActivity = (Home) getActivity();
        if (homeActivity != null) {
            userId = homeActivity.getUserId();
        }

        tvNombreUsuario = layout.findViewById(R.id.tvNombreUsuario);
        itemActividad = layout.findViewById(R.id.itemActividad);
        itemConsejo = layout.findViewById(R.id.itemConsejo);

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        ServicioApiHome serviceHome = ServicioApiHome.getInstancia();
        perfilViewModel.getPerfil(userId).observe(getViewLifecycleOwner(), perfil -> {
            tvNombreUsuario.setText(perfil.getNombreUsuario());
        });

        // Obtener las preferencias compartidas
        SharedPreferences preferences = getContext().getSharedPreferences("home_preferences", getContext().MODE_PRIVATE);
        long lastUpdateMillis = preferences.getLong(LAST_UPDATE, 0);
        Date lastUpdateDate = new Date(lastUpdateMillis);
        Date currentDate = Calendar.getInstance().getTime();

        if (shouldUpdate(lastUpdateDate, currentDate)) {
            // Si ha pasado un día, actualizar las actividades y consejos
            updateActividadesYConsejos(serviceHome, preferences);
        } else {
            // Si no ha pasado un día, cargar las actividades y consejos guardados
            loadSavedActividadesYConsejos(preferences);
        }

        return layout;
    }

    private boolean shouldUpdate(Date lastUpdateDate, Date currentDate) {
        Calendar lastUpdateCal = Calendar.getInstance();
        lastUpdateCal.setTime(lastUpdateDate);
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);

        return lastUpdateCal.get(Calendar.DAY_OF_YEAR) != currentCal.get(Calendar.DAY_OF_YEAR) ||
                lastUpdateCal.get(Calendar.YEAR) != currentCal.get(Calendar.YEAR);
    }

    private void updateActividadesYConsejos(ServicioApiHome serviceHome, SharedPreferences preferences) {
        Call<List<Actividad>> llamadaActividades = serviceHome.getRepo().getActividadesPorRango(1);
        llamadaActividades.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                List<Actividad> actividadesPorEdadHijo = response.body();
                if (actividadesPorEdadHijo != null && !actividadesPorEdadHijo.isEmpty()) {
                    int indiceAleatorio = random.nextInt(actividadesPorEdadHijo.size());
                    Actividad actividadRandom = actividadesPorEdadHijo.get(indiceAleatorio);

                    saveActividad(preferences, actividadRandom);
                    displayActividad(actividadRandom);
                }
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                System.out.println("Error en la llamada ActividadRandom Home(rangoHijo: " + rangoHijo + ")\n" + t.getMessage());
            }
        });

        Call<List<Consejo>> llamadaConsejos = serviceHome.getRepo().getConsejosPorRango(1);
        llamadaConsejos.enqueue(new Callback<List<Consejo>>() {
            @Override
            public void onResponse(Call<List<Consejo>> call, Response<List<Consejo>> response) {
                List<Consejo> consejosPorEdadHijo = response.body();
                if (consejosPorEdadHijo != null && !consejosPorEdadHijo.isEmpty()) {
                    int indiceAleatorio = random.nextInt(consejosPorEdadHijo.size());
                    Consejo consejoRandom = consejosPorEdadHijo.get(indiceAleatorio);

                    saveConsejo(preferences, consejoRandom);
                    displayConsejo(consejoRandom);
                }
            }

            @Override
            public void onFailure(Call<List<Consejo>> call, Throwable t) {
                System.out.println("Error en la llamada ConsejoRandom Home(rangoHijo: " + rangoHijo + ")\n" + t.getMessage());
            }
        });

        // Guardar la fecha de la última actualización
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(LAST_UPDATE, Calendar.getInstance().getTimeInMillis());
        editor.apply();
    }

    private void saveActividad(SharedPreferences preferences, Actividad actividad) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_ACTIVIDAD, new Gson().toJson(actividad));
        editor.apply();
    }

    private void saveConsejo(SharedPreferences preferences, Consejo consejo) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_CONSEJO, new Gson().toJson(consejo));
        editor.apply();
    }

    private void loadSavedActividadesYConsejos(SharedPreferences preferences) {
        String actividadJson = preferences.getString(SAVED_ACTIVIDAD, null);
        if (actividadJson != null) {
            Actividad actividad = new Gson().fromJson(actividadJson, Actividad.class);
            displayActividad(actividad);
        }

        String consejoJson = preferences.getString(SAVED_CONSEJO, null);
        if (consejoJson != null) {
            Consejo consejo = new Gson().fromJson(consejoJson, Consejo.class);
            displayConsejo(consejo);
        }
    }

    private void displayActividad(Actividad actividad) {
        tvTextoActividad = itemActividad.findViewById(R.id.tvTextoCorrespondiente);
        ivIconoActividad = itemActividad.findViewById(R.id.ivIcono);

        tvTextoActividad.setText(actividad.getNombre());
        int color = ContextCompat.getColor(getContext(), R.color.Principal);
        ivIconoActividad.setBackgroundColor(color);
        ivIconoActividad.setImageResource(actividad.getIcono());

        itemActividad.setOnClickListener(v -> {
            Intent i = new Intent(HomeFragment.super.getActivity(), ActividadDetalles.class);
            i.putExtra(INFO_ACTIVIDAD, actividad);
            startActivity(i);
        });
    }

    private void displayConsejo(Consejo consejo) {
        tvTextoConsejo = itemConsejo.findViewById(R.id.tvTextoCorrespondiente);
        ivIconoConsejo = itemConsejo.findViewById(R.id.ivIcono);

        tvTextoConsejo.setText(consejo.getNombre());
        int color = ContextCompat.getColor(getContext(), consejo.getColorFondo());
        ivIconoConsejo.setBackgroundColor(color);
        ivIconoConsejo.setImageResource(consejo.getIcono());

        itemConsejo.setOnClickListener(v -> {
            Intent i = new Intent(HomeFragment.super.getActivity(), ConsejoDetalles.class);
            i.putExtra(INFO_CONSEJO, consejo);
            startActivity(i);
        });
    }
}
