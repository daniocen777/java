package test;

import dto.CuantRiesgo;

public class TesstCuantificarRiesgo {

    public static void main(String[] args) {
        CuantRiesgo r = new CuantRiesgo();
        r.setAmenaza("MB");
        r.setImpacto("M");
        r.setVulnerabilidad("MA");

        double[] t = r.valorarAmenaza();
        double[] e = r.valorarVulnerabilidad();
        double[] w = r.valorarImpacto();
        System.out.println("VALORES DE AMENAZA");
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
            System.out.println();
        }
        System.out.println("VALORES DE VULNERABILIDAD");
        for (int i = 0; i < e.length; i++) {
            System.out.print(e[i]);
            System.out.println();
        }
        System.out.println("VALORES DE IMPACTO");
        for (int i = 0; i < w.length; i++) {
            System.out.print(w[i]);
            System.out.println();
        }

        // MULTIPLICACION
        System.out.println("MULTIPLICACION");
        double[] mult = r.multiplicar();
        for (int j = 0; j < mult.length; j++) {
            System.out.print((int) mult[j] + " ");

        }

        System.out.println("DEFUZZIFICAR");
        double res = r.defuzzificar();
        System.out.println(res);

        System.out.println("VALOR DE RIESGO");
        System.out.println(r.calcularRiesgo());
    }
}
