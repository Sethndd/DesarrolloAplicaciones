package POJO;

public class Tutor {
    private int idTutor;
    private int idEstudiante;
    private String primerNombreTutor;
    private String segundoNombreTutor;
    private String apellidoPatTutor;
    private String apellidoMatTutor;
    private String correoElectronico;
    private String telefonoFijo;
    private String telefonoCelular;
    private String nombreEstudiante;
    private boolean activo;

    public Tutor() {
        this.idTutor = 0;
    }

    public Tutor(int idTutor, String primerNombreTutor, String segundoNombreTutor, String apellidoPatTutor, String apellidoMatTutor, String correoElectronico, String telefonoFijo, String telefonoCelular) {
        this.idTutor = idTutor;
        this.primerNombreTutor = primerNombreTutor;
        this.segundoNombreTutor = segundoNombreTutor;
        this.apellidoPatTutor = apellidoPatTutor;
        this.apellidoMatTutor = apellidoMatTutor;
        this.correoElectronico = correoElectronico;
        this.telefonoFijo = telefonoFijo;
        this.telefonoCelular = telefonoCelular;
    }

    public int getIdTutor() {
        return this.idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public String getPrimerNombreTutor() {
        return this.primerNombreTutor;
    }

    public void setPrimerNombreTutor(String primerNombreTutor) {
        this.primerNombreTutor = primerNombreTutor;
    }

    public String getSegundoNombreTutor() {
        return this.segundoNombreTutor;
    }

    public void setSegundoNombreTutor(String segundoNombreTutor) {
        this.segundoNombreTutor = segundoNombreTutor;
    }

    public String getApellidoPatTutor() {
        return this.apellidoPatTutor;
    }

    public void setApellidoPatTutor(String apellidoPatTutor) {
        this.apellidoPatTutor = apellidoPatTutor;
    }

    public String getApellidoMatTutor() {
        return this.apellidoMatTutor;
    }

    public void setApellidoMatTutor(String apellidoMatTutor) {
        this.apellidoMatTutor = apellidoMatTutor;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoFijo() {
        return this.telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return this.telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getNombreEstudiante() {
        return this.nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public int getIdEstudiante() {
        return this.idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
