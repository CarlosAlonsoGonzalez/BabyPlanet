package com.example.babycare.Proyecto.Consejo;

import androidx.appcompat.app.AppCompatActivity;

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
    TextView tvTipoConsejo, tvNombreConsejo, tvDescripcion;
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
        btAtras=findViewById(R.id.btAtrasC);

        Intent i = getIntent();
        Serializable consejoRecibido = i.getSerializableExtra(ConsejoFragment.INFO_CONSEJO);
        Consejo nuevoConsejo = (Consejo) consejoRecibido;

        //TODO cambiar esto para que sea o simplemente una imagen de fondo o ver como hacemos para que el color realmente cambie
        ivFondoTitulo.setImageResource(nuevoConsejo.getIcono());
        ivFondoTitulo.setBackgroundColor(nuevoConsejo.getColorFondo());

        tvTipoConsejo.setText(nuevoConsejo.getTipo());
        tvNombreConsejo.setText(("\""+nuevoConsejo.getNombre())+"\"");
        tvDescripcion.setText(nuevoConsejo.getDescripcion());

        btAtras.setOnClickListener((View v)->{
            finish();
        });


    }
}