package com.example.babycare.Proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.babycare.R;
import com.example.babycare.databinding.HomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Home extends AppCompatActivity {

    HomeBinding binding;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        floatingActionButton = findViewById(R.id.homee);

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemReselectedListener(item ->{

            if(item.getItemId() == R.id.actividades){
                replaceFragment(new ActividadesFragment());
            }else if(item.getItemId() == R.id.consejos){
                replaceFragment(new ConsejosFragment());
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