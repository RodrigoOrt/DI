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
    private String fecha_nac;
    private String color;
    private int id_raza;
    private String raza;
    private String especie;
    private double peso;
    private String fecha_alta;
    private String fecha_adp;
    private String caracteristicas;
    
    public Animal(int id, String nombre, String sexo, String fecha_nac, String color, int id_raza, String raza, String especie, double peso, String fecha_alta, String fecha_adp, String caracteristicas) {
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

    public String getFecha_nac() {
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

    public String getFecha_alta() {
        return fecha_alta;
    }

    public String getFecha_adp() {
        return fecha_adp;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId_raza(int id_raza) {
        this.id_raza = id_raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public void setFecha_adp(String fecha_adp) {
        this.fecha_adp = fecha_adp;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    
    
}
