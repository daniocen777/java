package test;

public class TestPassword {

    public static void main(String[] args) {
        TestPassword test = new TestPassword();
        String nombre = "Yonatan Danel";
        String apellido = "Ochoa Chavarria";
        String password = test.getPass(nombre, apellido);
        System.out.println(password);
    }

    private String getPass(String nombre, String apellido) {
        String pass;
        String nombrePass = nombre.substring(0, 1);
        String[] apellidoPass = apellido.split(" ");
        pass = nombrePass + apellidoPass[0];
        return pass.toLowerCase();
    }
}
