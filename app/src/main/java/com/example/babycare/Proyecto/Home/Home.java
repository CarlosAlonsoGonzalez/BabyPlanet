package com.example.babycare.Proyecto.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.babycare.Proyecto.Actividad.ActividadesFragment;
import com.example.babycare.Proyecto.Consejo.ConsejoFragment;
import com.example.babycare.Proyecto.Perfil.PerfilFragment;
import com.example.babycare.Proyecto.Rango;
import com.example.babycare.R;
import com.example.babycare.databinding.HomeBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;


public class Home extends AppCompatActivity {

    //Heeramientas filtro
    //Etiqueta hijos, 6 radio button edad + radio button tipo (varia segun consejo o actividad)

    HomeBinding binding;
    FloatingActionButton floatingActionButton;
    MaterialToolbar mt;
    Button btAplicarFiltros, btCancelar;
    RadioGroup rgRangoEdad;
    RadioButton rb0006, rb0612, rb1218, rb1824, rb2430, rb3036;
    Spinner spCategoria;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        floatingActionButton = findViewById(R.id.homee);
        mt = findViewById(R.id.normal_toolbar);

        mt.setOnMenuItemClickListener((v)->{
            AlertDialog.Builder e = new AlertDialog.Builder(this);
            LayoutInflater in = getLayoutInflater();
            View vi = in.inflate(R.layout.filtro, null);
            e.setView(vi);
            rgRangoEdad = vi.findViewById(R.id.rgEdadRango);
            rb0006 = vi.findViewById(R.id.rb0006);
            rb0612 = vi.findViewById(R.id.rb0612);
            rb1218 = vi.findViewById(R.id.rb1218);
            rb1824 = vi.findViewById(R.id.rb1824);
            rb2430 = vi.findViewById(R.id.rb2430);
            rb3036 = vi.findViewById(R.id.rb3036);
            spCategoria = vi.findViewById(R.id.spCategoria);
            btAplicarFiltros = vi.findViewById(R.id.btAplicarFiltros);
            btCancelar = vi.findViewById(R.id.btCancelar);

            if (currentFragment instanceof ActividadesFragment) {
                setupSpinnerActividad();
            } else if(currentFragment instanceof ConsejoFragment){
                setupSpinnerConsejo();
            }

            vi.findViewById(R.id.rb0006);
            AlertDialog ad = e.create();

            btAplicarFiltros.setOnClickListener((m) ->{
                int rango = 0;

                if(rgRangoEdad.getCheckedRadioButtonId() == rb0006.getId()){
                     rango= Rango.RANGO_0_6.getCodigo();
                } else if (rgRangoEdad.getCheckedRadioButtonId() == rb0612.getId()) {
                    rango= Rango.RANGO_6_12.getCodigo();
                }else if (rgRangoEdad.getCheckedRadioButtonId() == rb1218.getId()) {
                    rango= Rango.RANGO_12_18.getCodigo();
                }else if (rgRangoEdad.getCheckedRadioButtonId() == rb1824.getId()) {
                    rango= Rango.RANGO_18_24.getCodigo();
                }else if (rgRangoEdad.getCheckedRadioButtonId() == rb2430.getId()) {
                    rango= Rango.RANGO_24_30.getCodigo();
                }else if (rgRangoEdad.getCheckedRadioButtonId() == rb3036.getId()) {
                    rango= Rango.RANGO_30_36.getCodigo();
                }

                if (currentFragment instanceof ActividadesFragment) {
                    String area = spCategoria.getSelectedItem().toString();
                    ActividadesFragment.cambiarActividadesAdapter(rango,area);
                } else if(currentFragment instanceof ConsejoFragment){
                    String tipo = spCategoria.getSelectedItem().toString();
                    ConsejoFragment.cambiarConsejosAdapter(rango,tipo);
                }
            });

            btCancelar.setOnClickListener((mi) ->{
                ad.dismiss();
            });

            ad.show();
            return true;
        });

        mt.setNavigationOnClickListener((v)->{
            replaceFragment(new PerfilFragment());
        });

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.actividades) {
                    replaceFragment(new ActividadesFragment());
                    return true;
                } else if (item.getItemId() == R.id.consejos) {
                    replaceFragment(new ConsejoFragment());
                    return true;
                }
                return false;
            }
        });


        floatingActionButton.setOnClickListener((v)->{
            replaceFragment(new HomeFragment());
        });

    }


    private void setupSpinnerActividad() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Actividades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(adapter);
    }
    private void setupSpinnerConsejo() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Consejos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(adapter);
    }

    private void replaceFragment(Fragment fragment){
        currentFragment = fragment;
        MenuItem filterItem = mt.getMenu().findItem(R.id.itemFiltro);
        if (currentFragment instanceof ActividadesFragment || currentFragment instanceof ConsejoFragment) {
            filterItem.setVisible(true);
        } else {
            filterItem.setVisible(false);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}