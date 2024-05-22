package com.example.babycare.Proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.babycare.Proyecto.Actividad.ActividadesFragment;
import com.example.babycare.Proyecto.Consejo.ConsejoFragment;
import com.example.babycare.Proyecto.Perfil.PerfilFragment;
import com.example.babycare.R;
import com.example.babycare.databinding.HomeBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Home extends AppCompatActivity {

    HomeBinding binding;
    FloatingActionButton floatingActionButton;
    MaterialToolbar mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        floatingActionButton = findViewById(R.id.homee);
        mt = findViewById(R.id.normal_toolbar);


        mt.setOnMenuItemClickListener((v)->{
            //Laura aqui va lo que harias con el filtro cogiendo el id del filtro
            //ej de chat gpt
            /*
            int id = item.getItemId();
            if (id == R.id.some_menu_item) {
                Intent intent = new Intent(CurrentActivity.this, TargetActivity.class);
                startActivity(intent);
                return true;
            }*/
            return false;
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

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}