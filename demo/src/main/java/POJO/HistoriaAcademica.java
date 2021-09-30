package POJO;

public class HistoriaAcademica {
    private int idHistoriaAcademica;
    private int idEstudiante;
    private String nombreEstudiante;
    private String colegioAnterior;
    private String año;
    private String grado;

    public int getIdHistoriaAcademica() {
        return idHistoriaAcademica;
    }

    public void setIdHistoriaAcademica(int idHistoriaAcademica) {
        this.idHistoriaAcademica = idHistoriaAcademica;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getColegioAnterior() {
        return colegioAnterior;
    }

    public void setColegioAnterior(String colegioAnterior) {
        this.colegioAnterior = colegioAnterior;
    }

    public String getAño() {
        return año;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}
