package com.example.babycare.Proyecto.Actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babycare.R;

import java.io.Serializable;

public class ActividadDetalles extends AppCompatActivity {
    ImageView ivFondoTitulo;
    TextView tvNombre, tvDescripcion, tvMateriales,tvRango;
    Button btAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_detalles);
        getSupportActionBar().hide();

        ivFondoTitulo=findViewById(R.id.ivFondoTituloA);

        tvNombre=findViewById(R.id.tvNombreActividad);
        tvDescripcion=findViewById(R.id.tvDescripcionA);
        tvMateriales=findViewById(R.id.tvMateriales);
        tvRango=findViewById(R.id.tvRangoA);
        btAtras=findViewById(R.id.btAtrasA);


        Intent i = getIntent();

        Serializable actividadRecibida = i.getSerializableExtra(ActividadesFragment.INFO_ACTIVIDAD);
        Actividad nuevaActividad = (Actividad) actividadRecibida;

        ivFondoTitulo.setImageResource(nuevaActividad.getIcono());

        tvNombre.setText(nuevaActividad.getNombre());
        tvDescripcion.setText(nuevaActividad.getDescripcion());
        tvMateriales.setText(nuevaActividad.getMaterialesToString());
        tvRango.setText(nuevaActividad.getRangoString());

        btAtras.setOnClickListener((View v)->{
            finish();
        });
    }
}