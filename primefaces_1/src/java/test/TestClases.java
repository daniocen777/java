package test;

import dto.Autores;
import dto.Frases;

public class TestClases {
    
    public static void main(String[] args) {
        Frases frases = new Frases();
        Autores autores = new Autores();
        autores.setAutor("Lolas");
        autores.setIdautor(2);
        
        frases.setFrase("Hola loloas");
        frases.setAutores(autores);
        
        System.out.println(frases.getAutores().getAutor());
    }
}
