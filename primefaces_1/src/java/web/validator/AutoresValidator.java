package web.validator;

import dto.Autores;
import dto.Frases;
import java.util.LinkedList;
import java.util.List;

public class AutoresValidator {

    public List<String> validarAutor(Autores autores) {
        List<String> list = new LinkedList<>();
        if (autores.getAutor().length() == 0 || autores.getAutor().equalsIgnoreCase("")) {
            list.add("Debe ingresar un autor");
        }
        if (autores.getAutor().equalsIgnoreCase("Daniel")) {
            list.add("No puede ser Daniel");
            list.add("Ponga otro");
        }

        return list;
    }

    public List<String> validarFrase(Frases frases, Autores autores) {
        List<String> list = new LinkedList<>();
        if (autores.getIdautor() == null) {
            list.add("Debe ingresar un autor");
        }

        if (frases.getFrase().length() == 0 || frases.getFrase().equalsIgnoreCase("")) {
            list.add("Debe ingresar frase");
        }

        return list;
    }

}
