package com.example.babycare.Proyecto;

import com.example.babycare.Proyecto.Perfil.Hijo;

import java.util.List;

public class UsuarioSingleton {
    private long id;
    private String nombreUsuario;
    private String email;
    private String password;
    private List<Hijo> hijos;

    private static UsuarioSingleton anfitrion = null;

    public static UsuarioSingleton getAnfitrion(long id, String nombreUsuario, String email, String password, List<Hijo> hijos) {
        if (anfitrion == null){
            anfitrion = new UsuarioSingleton(id,nombreUsuario,email,password,hijos);
            // if(checkIfFileExists("usuarioAnfitrion.csv")) anfitrion = leerCSVAnfitrion();
        }
        return anfitrion;

    }

    private UsuarioSingleton(long id, String nombreUsuario, String email, String password, List<Hijo> hijos) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.hijos = hijos;
    }

    //TODO al androidmanifest:
    //<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

    //TODO estos serian los metodos para escribir un fichero csv con los datos del usuario que inicio sesion
    /*--------------------------
    private void crearAnfitrionCSV(){
        File file = new File(Environment.getExternalStorageDirectory(), "usuarioAnfitrion.csv");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            // Escribir la cabecera
            osw.write("id,nombreUsuario,email,password,hijos\n");

            // Escribir los datos del usuario
            //ESTE USUARIO SERIA EL DE LA LLAMADA A LA API AL HACER EL REGISTRO O EL LOGIN
            osw.write(usuario.getId() + ","
                    + usuario.getNombreUsuario() + ","
                    + usuario.getEmail() + ","
                    + usuario.getPassword() + ","
                    + formatHijos(usuario.getHijos()) + "\n");

            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatHijos(List<Hijo> hijos) {
        StringBuilder sb = new StringBuilder();
        for (Hijo hijo : hijos) {
            sb.append("[").append(hijo.getId()).append(":").append(hijo.getNombre()).append("]");
        }
        return sb.toString();
    }
    --------------------------------------*/

    //TODO este seria el metodo dentro de este mismo singleton para recoger los datos del csv si lo hubieses (se borra al cerrar sesion en perfil)

    /*----------------
    private Usuario leerCSVAnfitrion() {
        File file = new File(Environment.getExternalStorageDirectory(), "usuarioAnfitrion.csv");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine(); // Leer la cabecera y descartarla

            if ((line = br.readLine()) != null) {
                // Dividir la línea en partes utilizando la coma como delimitador
                String[] parts = line.split(",");

                // Obtener los datos del usuario
                long id = Long.parseLong(parts[0]);
                String nombreUsuario = parts[1];
                String email = parts[2];
                String password = parts[3];
                List<Hijo> hijos = parseHijos(parts[4]);

                // Crear y retornar el objeto Usuario
                return new Usuario(id, nombreUsuario, email, password, hijos);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Hijo> parseHijos(String hijosStr) {
        List<Hijo> hijos = new ArrayList<>();

        // Eliminar los corchetes y dividir los datos de los hijos
        hijosStr = hijosStr.replaceAll("[\\[\\]]", "");
        String[] hijosParts = hijosStr.split("],\\[");

        for (String hijoPart : hijosParts) {
            String[] hijoData = hijoPart.split(":");
            long id = Long.parseLong(hijoData[0]);
            String nombre = hijoData[1];
            hijos.add(new Hijo(id, nombre));
        }

        return hijos;
    }
    ----------------------------------------------*/

    //TODO Tambien podriamos tener un metodo que validase si la sesion esta inciada directamente (es decir que sepa si ese fichero existe)
    /*
    private boolean checkIfFileExists(String fileName) {
        // Crear la referencia al archivo en el almacenamiento externo
        File file = new File(Environment.getExternalStorageDirectory(), fileName);

        // Comprobar si el archivo existe
        return file.exists();
    }
    */


}
