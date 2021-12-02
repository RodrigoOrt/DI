package refugio.model;

import java.util.Date;

/**
 *
 * @author rodri
 */
public class Animal {
    
    private int id;
    private String nombre;
    private String sexo;
    private Date fecha_nac;
    private String color;
    private int id_raza;
    private String raza;
    private String especie;
    private double peso;
    private Date fecha_alta;
    private Date fecha_adp;
    private String caracteristicas;

    public Animal(int id, String nombre, String sexo, Date fecha_nac, String color, int id_raza, String raza, String especie, double peso, Date fecha_alta, Date fecha_adp, String caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fecha_nac = fecha_nac;
        this.color = color;
        this.id_raza = id_raza;
        this.raza = raza;
        this.especie = especie;
        this.peso = peso;
        this.fecha_alta = fecha_alta;
        this.fecha_adp = fecha_adp;
        this.caracteristicas = caracteristicas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public String getColor() {
        return color;
    }

    public int getId_raza() {
        return id_raza;
    }

    public String getRaza() {
        return raza;
    }

    public String getEspecie() {
        return especie;
    }

    public double getPeso() {
        return peso;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public Date getFecha_adp() {
        return fecha_adp;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }


    
}
