package com.example.babycare.Proyecto.Consejo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

import com.example.babycare.R;

import java.io.Serializable;

public class ConsejoDetalles extends AppCompatActivity {

    TextView tvTipoConsejo, tvNombreConsejo, tvDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejo_detalles);
        tvTipoConsejo = findViewById(R.id.tvTipoConsejo);
        tvNombreConsejo = findViewById(R.id.tvNombreConsejo);
        tvDescripcion = findViewById(R.id.tvTextoDescripcion);

        Intent i = getIntent();
        Serializable consejoRecibido = i.getSerializableExtra(ConsejoFragment.INFO_CONSEJO);
        Consejo nuevoConsejo = (Consejo) consejoRecibido;

        tvTipoConsejo.setText(nuevoConsejo.getTipo());
        tvNombreConsejo.setText((nuevoConsejo.getNombre()));
        tvDescripcion.setText(nuevoConsejo.getDescripcion());
    }
}