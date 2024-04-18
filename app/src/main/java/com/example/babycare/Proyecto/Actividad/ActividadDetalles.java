package com.example.babycare.Proyecto.Actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.babycare.R;

import java.io.Serializable;

public class ActividadDetalles extends AppCompatActivity {
    TextView tvNombre, tvDescripcion, tvMateriales,tvRango;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalles);
        tvNombre=findViewById(R.id.tvNombreActividad);
        tvDescripcion=findViewById(R.id.tvDescripcionA);
        tvMateriales=findViewById(R.id.tvMateriales);
        tvRango=findViewById(R.id.tvRangoA);


        Intent i = getIntent();

        Serializable actividadRecibida = i.getSerializableExtra(ActividadesFragment.INFO_ACTIVIDAD);
        Actividad nuevaActividad = (Actividad) actividadRecibida;

        tvNombre.setText(nuevaActividad.getNombre());
        tvDescripcion.setText(nuevaActividad.getDescripcion());
        //tvMateriales.setText(nuevaActividad.getMaterialesToString());
        tvRango.setText(nuevaActividad.getRangoString());
    }
}