package com.example.babycare.Proyecto;

import android.os.Environment;

import com.example.babycare.Proyecto.Perfil.Hijo;
import com.example.babycare.Proyecto.Perfil.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSingleton {
    public static final String NAME_FILE = "usuarioAnfitrion.csv" ;
    private long id;
    private static UsuarioSingleton anfitrion = null;

    public static UsuarioSingleton getAnfitrion() {
        if (anfitrion == null){
            if(checkIfFileExists()) anfitrion = leerCSVAnfitrion();
        }
        return anfitrion;
    }

    public Long getId() {
        return id;
    }

    private UsuarioSingleton(long id) {
        this.id = id;
    }


    private static UsuarioSingleton leerCSVAnfitrion() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NAME_FILE);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            if ((line = br.readLine()) != null) {
                // Dividir la línea en partes utilizando la coma como delimitador
                String[] parts = line.split(",");

                // Obtener los datos del usuario
                long id = Long.parseLong(parts[0]);

                // Crear y retornar el objeto Usuario
                return new UsuarioSingleton(id);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static boolean checkIfFileExists() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NAME_FILE);
        return file.exists();
    }





}
