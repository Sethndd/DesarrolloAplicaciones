package POJO;

public class Colegio {
    private  int idColegio;
    private String tipoColegio;
    private String ciudad;
    private String nombre;
    private boolean estado;

    public Colegio() {
    }

    public Colegio(int idColegio, String tipoColegio, String ciudad, String nombre, boolean estado) {
        this.idColegio = idColegio;
        this.tipoColegio = tipoColegio;
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(int idColegio) {
        this.idColegio = idColegio;
    }

    public String getTipoColegio() {
        return tipoColegio;
    }

    public void setTipoColegio(String tipoColegio) {
        this.tipoColegio = tipoColegio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
