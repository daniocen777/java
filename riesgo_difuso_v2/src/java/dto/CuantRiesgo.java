package dto;

import java.io.Serializable;
import java.util.Date;

public class CuantRiesgo implements Serializable {

    private Integer idcuantriesgo;
    private Salvaguarda salvaguarda_idsalvaguarda;
    private String valoramenaza;
    private String valorimpacto;
    private String valorvulner;
    private Date fecreg;
    private double defu;
    private String rie;

    public CuantRiesgo() {
    }

    public Integer getIdcuantriesgo() {
        return idcuantriesgo;
    }

    public void setIdcuantriesgo(Integer idcuantriesgo) {
        this.idcuantriesgo = idcuantriesgo;
    }

    public Salvaguarda getSalvaguarda_idsalvaguarda() {
        return salvaguarda_idsalvaguarda;
    }

    public void setSalvaguarda_idsalvaguarda(Salvaguarda salvaguarda_idsalvaguarda) {
        this.salvaguarda_idsalvaguarda = salvaguarda_idsalvaguarda;
    }

    public String getValoramenaza() {
        return valoramenaza;
    }

    public void setValoramenaza(String valoramenaza) {
        this.valoramenaza = valoramenaza;
    }

    public String getValorimpacto() {
        return valorimpacto;
    }

    public void setValorimpacto(String valorimpacto) {
        this.valorimpacto = valorimpacto;
    }

    public String getValorvulner() {
        return valorvulner;
    }

    public void setValorvulner(String valorvulner) {
        this.valorvulner = valorvulner;
    }

    public Date getFecreg() {
        return fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

    public double getDefu() {
        return defu;
    }

    public void setDefu(double defu) {
        this.defu = defu;
    }

    public String getRie() {
        return rie;
    }

    public void setRie(String rie) {
        this.rie = rie;
    }

    // Valorar la ameanza
    public double[] valorarAmenaza() {
        double[] valorAmen = new double[3];

        if (valoramenaza.equalsIgnoreCase("MB")) {
            valorAmen[0] = 0;
            valorAmen[1] = 1;
            valorAmen[2] = 2;
        }
        if (valoramenaza.equalsIgnoreCase("B")) {
            valorAmen[0] = 2;
            valorAmen[1] = 3;
            valorAmen[2] = 4;
        }
        if (valoramenaza.equalsIgnoreCase("M")) {
            valorAmen[0] = 4;
            valorAmen[1] = 5;
            valorAmen[2] = 6;
        }
        if (valoramenaza.equalsIgnoreCase("A")) {
            valorAmen[0] = 6;
            valorAmen[1] = 7;
            valorAmen[2] = 8;
        }
        if (valoramenaza.equalsIgnoreCase("MA")) {
            valorAmen[0] = 8;
            valorAmen[1] = 9;
            valorAmen[2] = 10;
        }
        return valorAmen;
    }

    // Valorar la ameanza
    public double[] valorarImpacto() {
        double[] valorImplacto = new double[3];

        if (valorimpacto.equalsIgnoreCase("MB")) {
            valorImplacto[0] = 0;
            valorImplacto[1] = 1;
            valorImplacto[2] = 2;
        }
        if (valorimpacto.equalsIgnoreCase("B")) {
            valorImplacto[0] = 2;
            valorImplacto[1] = 3;
            valorImplacto[2] = 4;
        }
        if (valorimpacto.equalsIgnoreCase("M")) {
            valorImplacto[0] = 4;
            valorImplacto[1] = 5;
            valorImplacto[2] = 6;
        }
        if (valorimpacto.equalsIgnoreCase("A")) {
            valorImplacto[0] = 6;
            valorImplacto[1] = 7;
            valorImplacto[2] = 8;
        }
        if (valorimpacto.equalsIgnoreCase("MA")) {
            valorImplacto[0] = 8;
            valorImplacto[1] = 9;
            valorImplacto[2] = 10;
        }
        return valorImplacto;
    }

    // Valorar la vulnerabilidad
    public double[] valorarVulnerabilidad() {
        double[] valorVulnerabilidad = new double[3];

        if (valorvulner.equalsIgnoreCase("MB")) {
            valorVulnerabilidad[0] = 0;
            valorVulnerabilidad[1] = 1;
            valorVulnerabilidad[2] = 2;
        }
        if (valorvulner.equalsIgnoreCase("B")) {
            valorVulnerabilidad[0] = 2;
            valorVulnerabilidad[1] = 3;
            valorVulnerabilidad[2] = 4;
        }
        if (valorvulner.equalsIgnoreCase("M")) {
            valorVulnerabilidad[0] = 4;
            valorVulnerabilidad[1] = 5;
            valorVulnerabilidad[2] = 6;
        }
        if (valorvulner.equalsIgnoreCase("A")) {
            valorVulnerabilidad[0] = 6;
            valorVulnerabilidad[1] = 7;
            valorVulnerabilidad[2] = 8;
        }
        if (valorvulner.equalsIgnoreCase("MA")) {
            valorVulnerabilidad[0] = 8;
            valorVulnerabilidad[1] = 9;
            valorVulnerabilidad[2] = 10;
        }
        return valorVulnerabilidad;
    }

    public double[] multiplicar() {
        double[] mult = new double[3];
        //Valor de AMENAZA
        double[] v_amenaza = valorarAmenaza();
        // Valor de VULNERABILIDAD
        double[] v_vulner = valorarVulnerabilidad();
        // Valor de IMPACTO
        double[] v_imp = valorarImpacto();
        // MULTIPLICAMOS
        for (int i = 0; i < v_amenaza.length; i++) {
            mult[i] = v_amenaza[i] * v_vulner[i] * v_imp[i];
        }
        return mult;
    }

    // FUNCIÓN PARA DEFUZIFICAR
    public double defuzzificar() {
        double[] mult = multiplicar();
        defu = (mult[0] + 2 * mult[1] + mult[2]) / 4;
        return defu;
    }

    // Cálculo de riesgo
    public String calcularRiesgo() {
        rie = null;
        defu = defuzzificar();

        if (defu >= 0 && defu <= 2.5) {
            rie = "MUY BAJO";
        }
        if (defu > 2.5 && defu <= 31.5) {
            rie = "BAJO";
        }
        if (defu > 31.5 && defu <= 132.5) {
            rie = "MEDIO";
        }
        if (defu > 132.5 && defu <= 353.5) {
            rie = "ALTO";
        }
        if (defu > 353.5 && defu <= 742.5) {
            rie = "MUY ALTO";
        }
        return rie;
    }

}
