package POJO;

public class Estudiante {
    private int id;
    private String primerApe;
    private String primerNom;
    private String segundoApe;
    private String segundoNom;
    private boolean activo;

    public Estudiante() {
        this.id = 0;
    }

    public Estudiante(String primerApe, String primerNom, String segundoApe, String segundoNom, boolean activo) {
        id = 0;
        this.primerApe = primerApe;
        this.primerNom = primerNom;
        this.segundoApe = segundoApe;
        this.segundoNom = segundoNom;
        this.activo = activo;
    }

    public Estudiante(int id, String primerApe, String primerNom, String segundoApe, String segundoNom, boolean activo) {
        this.id = id;
        this.primerApe = primerApe;
        this.primerNom = primerNom;
        this.segundoApe = segundoApe;
        this.segundoNom = segundoNom;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimerApe() {
        return primerApe;
    }

    public void setPrimerApe(String primerApe) {
        this.primerApe = primerApe;
    }

    public String getPrimerNom() {
        return primerNom;
    }

    public void setPrimerNom(String primerNom) {
        this.primerNom = primerNom;
    }

    public String getSegundoApe() {
        return segundoApe;
    }

    public void setSegundoApe(String segundoApe) {
        this.segundoApe = segundoApe;
    }

    public String getSegundoNom() {
        return segundoNom;
    }

    public void setSegundoNom(String segundoNom) {
        this.segundoNom = segundoNom;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return id + " " + primerApe + " " + segundoApe + " " + primerNom + " " + segundoNom + " " + activo;
    }
}