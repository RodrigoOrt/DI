package refugio.model;

/**
 * @description
 * Clase Vacuna
 * 
 * @author rodri
 */
public class Vacuna {
    
    private int id;
    private String nombre;
    private String descripcion;
    private boolean escencial;
    private int revacunacion;
    private String fecha_dosis;

    public Vacuna() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.escencial = false;
        this.revacunacion = 0;
        this.fecha_dosis = "";
    }

    
    
    public Vacuna(int id, String nombre, String descripcion, boolean escencial, int revacunacion, String fecha_dosis) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.escencial = escencial;
        this.revacunacion = revacunacion;
        this.fecha_dosis = fecha_dosis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEscencial() {
        return escencial;
    }

    public void setEscencial(boolean escencial) {
        this.escencial = escencial;
    }

    public int getRevacunacion() {
        return revacunacion;
    }

    public void setRevacunacion(int revacunacion) {
        this.revacunacion = revacunacion;
    }

    public String getFecha_dosis() {
        return fecha_dosis;
    }

    public void setFecha_dosis(String fecha_dosis) {
        this.fecha_dosis = fecha_dosis;
    }
    
    
}
