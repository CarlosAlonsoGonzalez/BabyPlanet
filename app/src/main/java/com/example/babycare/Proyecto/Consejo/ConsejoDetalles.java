package com.example.babycare.Proyecto.Consejo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.example.babycare.R;

import java.io.Serializable;

public class ConsejoDetalles extends AppCompatActivity {

    ImageView ivFondoTitulo;
    TextView tvTipoConsejo, tvNombreConsejo, tvDescripcion, tvRango;
    Button btAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejo_detalles);
        getSupportActionBar().hide();

        ivFondoTitulo=findViewById(R.id.ivFondoTituloC);

        tvTipoConsejo = findViewById(R.id.tvTipoConsejo);
        tvNombreConsejo = findViewById(R.id.tvNombreConsejo);
        tvDescripcion = findViewById(R.id.tvDescripcionC);
        tvRango = findViewById(R.id.tvRangoC);

        btAtras=findViewById(R.id.btAtrasC);

        Intent i = getIntent();
        Serializable consejoRecibido = i.getSerializableExtra(ConsejoFragment.INFO_CONSEJO);
        Consejo nuevoConsejo = (Consejo) consejoRecibido;

        ivFondoTitulo.setImageResource(nuevoConsejo.getIcono());
        int colorIconoConsejo = ContextCompat.getColor(this, nuevoConsejo.getColorFondo());
        ivFondoTitulo.setBackgroundColor(colorIconoConsejo);

        tvTipoConsejo.setText(nuevoConsejo.getTipo());
        tvTipoConsejo.setBackgroundColor(colorIconoConsejo);
        tvNombreConsejo.setText(("\""+nuevoConsejo.getNombre())+"\"");
        tvDescripcion.setText(nuevoConsejo.getDescripcion());
        tvRango.setText(nuevoConsejo.getRangoString());

        btAtras.setOnClickListener((View v)->{
            finish();
        });


    }
}