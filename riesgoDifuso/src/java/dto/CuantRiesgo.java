package dto;

public class CuantRiesgo {

    private Integer idcuantriesgo;
    private Integer idactespecamen;
    private String amenaza;
    private String vulnerabilidad;
    private String impacto;

    public CuantRiesgo() {
    }

    public Integer getIdcuantriesgo() {
        return idcuantriesgo;
    }

    public void setIdcuantriesgo(Integer idcuantriesgo) {
        this.idcuantriesgo = idcuantriesgo;
    }

    public Integer getIdactespecamen() {
        return idactespecamen;
    }

    public void setIdactespecamen(Integer idactespecamen) {
        this.idactespecamen = idactespecamen;
    }

    public String getAmenaza() {
        return amenaza;
    }

    public void setAmenaza(String amenaza) {
        this.amenaza = amenaza;
    }

    public String getVulnerabilidad() {
        return vulnerabilidad;
    }

    public void setVulnerabilidad(String vulnerabilidad) {
        this.vulnerabilidad = vulnerabilidad;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    // Valorar la ameanza
    public double[] valorarAmenaza() {
        double[] valorAmen = new double[3];

        if (amenaza.equalsIgnoreCase("MB")) {
            valorAmen[0] = 0;
            valorAmen[1] = 1;
            valorAmen[2] = 2;
        }
        if (amenaza.equalsIgnoreCase("B")) {
            valorAmen[0] = 2;
            valorAmen[1] = 3;
            valorAmen[2] = 4;
        }
        if (amenaza.equalsIgnoreCase("M")) {
            valorAmen[0] = 4;
            valorAmen[1] = 5;
            valorAmen[2] = 6;
        }
        if (amenaza.equalsIgnoreCase("A")) {
            valorAmen[0] = 6;
            valorAmen[1] = 7;
            valorAmen[2] = 8;
        }
        if (amenaza.equalsIgnoreCase("MA")) {
            valorAmen[0] = 8;
            valorAmen[1] = 9;
            valorAmen[2] = 10;
        }
        return valorAmen;
    }

    // Valorar la ameanza
    public double[] valorarVulnerabilidad() {
        double[] valorVulnerabilidad = new double[3];

        if (vulnerabilidad.equalsIgnoreCase("MB")) {
            valorVulnerabilidad[0] = 0;
            valorVulnerabilidad[1] = 1;
            valorVulnerabilidad[2] = 2;
        }
        if (vulnerabilidad.equalsIgnoreCase("B")) {
            valorVulnerabilidad[0] = 2;
            valorVulnerabilidad[1] = 3;
            valorVulnerabilidad[2] = 4;
        }
        if (vulnerabilidad.equalsIgnoreCase("M")) {
            valorVulnerabilidad[0] = 4;
            valorVulnerabilidad[1] = 5;
            valorVulnerabilidad[2] = 6;
        }
        if (vulnerabilidad.equalsIgnoreCase("A")) {
            valorVulnerabilidad[0] = 6;
            valorVulnerabilidad[1] = 7;
            valorVulnerabilidad[2] = 8;
        }
        if (vulnerabilidad.equalsIgnoreCase("MA")) {
            valorVulnerabilidad[0] = 8;
            valorVulnerabilidad[1] = 9;
            valorVulnerabilidad[2] = 10;
        }
        return valorVulnerabilidad;
    }

    // Valorar la impacto
    public double[] valorarImpacto() {
        double[] valorImpac = new double[3];

        if (impacto.equalsIgnoreCase("MB")) {
            valorImpac[0] = 0;
            valorImpac[1] = 1;
            valorImpac[2] = 2;
        }
        if (impacto.equalsIgnoreCase("B")) {
            valorImpac[0] = 2;
            valorImpac[1] = 3;
            valorImpac[2] = 4;
        }
        if (impacto.equalsIgnoreCase("M")) {
            valorImpac[0] = 4;
            valorImpac[1] = 5;
            valorImpac[2] = 6;
        }
        if (impacto.equalsIgnoreCase("A")) {
            valorImpac[0] = 6;
            valorImpac[1] = 7;
            valorImpac[2] = 8;
        }
        if (impacto.equalsIgnoreCase("MA")) {
            valorImpac[0] = 8;
            valorImpac[1] = 9;
            valorImpac[2] = 10;
        }
        return valorImpac;
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
        double defu;
        double[] mult = multiplicar();
        defu = (mult[0] + 2 * mult[1] + mult[2]) / 4;
        return defu;
    }

    // Cálculo de riesgo
    public String calcularRiesgo() {
        String rie = null;
        double defu = defuzzificar();

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
