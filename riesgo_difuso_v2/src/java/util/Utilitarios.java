package util;

public class Utilitarios {

    // Método para generar usuario y contraseña a partir de nombre  apellido
    public static String generarUserYPassword(String nombre, String apellido) {
        String pass;
        String nombrePass = nombre.substring(0, 1);
        String[] apellidoPass = apellido.split(" ");
        pass = nombrePass + apellidoPass[0];
        return pass.toLowerCase();
    }
}
