package POJO;

public class InformacionSalud {
    private int numeroSeguroSocial;
    private String nombreEntidad;
    private String grupoSanguineo;
    private int idEstudiante;

    public InformacionSalud() {
    }

    public InformacionSalud(int numeroSeguroSocial, String nombreEntidad, String grupoSanguineo, int idEstudiante) {
        this.numeroSeguroSocial = numeroSeguroSocial;
        this.nombreEntidad = nombreEntidad;
        this.grupoSanguineo = grupoSanguineo;
        this.idEstudiante = idEstudiante;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public void setNumeroSeguroSocial(int numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    @Override
    public String toString() {
        return "Número de SS:" + numeroSeguroSocial +
                ", Nombre de la entidad:'" + nombreEntidad + '\'' +
                ", Tipo de sangre:'" + grupoSanguineo + '\'' + '}';
    }
}
