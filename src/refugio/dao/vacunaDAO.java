package refugio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import refugio.model.Animal;
import refugio.model.Vacuna;
import refugio.util.ConnectionManager;

/**
 * @description
 * vacunaDAO dispone de metodos para interactuar con la parte de vacunas y dosis
 * de la base de datos
 * 
 * @author rodri
 */
public class vacunaDAO implements DAOI{

    LocalDate fechaActual = LocalDate.now();
    
    //Metodo para obtener las vacunas disponibles para una especie.
    @Override
    public List getValorPorEspecie(String especie) {
        String consulta= "Select nombre " +
            "FROM vacuna V, especie E,vacuna_especie VE " +
            "WHERE V.id=VE.id_vacuna AND VE.id_especie=E.id AND E.tipo='"+ especie+"'";
        
        List<String> vacunas = new ArrayList();
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()){
                String nombre = resultado.getString("nombre");
                vacunas.add(nombre);
             
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(vacunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunas;
    }
    
    //Metodo para obtener las vacunas de un animal.
    public Collection<Vacuna> getAll(int id_animal) {
        String consulta= "Select * "
                    + "FROM vacuna V, dosis D "
                    + "WHERE V.id=D.id_vacuna AND D.id_animal="+ id_animal;
        
        List<Vacuna> vacunas = new ArrayList();
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()){
                int id = resultado.getInt("V.id");
                String nombre = resultado.getString("V.nombre");
                String descripcion = resultado.getString("V.descripción");
                Boolean esencial = resultado.getBoolean("V.escencial");
                int revacunacion = resultado.getInt("V.revacunación");
                String fecha_dosis = resultado.getString("D.fecha");
                
                Vacuna vacuna = new Vacuna(id,nombre,descripcion,esencial,
                    revacunacion,fecha_dosis);
                vacunas.add(vacuna);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(vacunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunas;
    }
    
    //Metodo para obtener una vacuna especifica por su nombre.
    public Vacuna getVacuna(String nombreVacuna) {
        String consulta= "Select * "
                    + "FROM vacuna "
                    + "WHERE nombre='"+ nombreVacuna +"'";
        
        Vacuna vacuna = null;
        
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripción");
                Boolean esencial = resultado.getBoolean("escencial");
                int revacunacion = resultado.getInt("revacunación");
                
                vacuna = new Vacuna(id,nombre,descripcion,esencial,
                    revacunacion,fechaActual.toString());
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(vacunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacuna;
    }
  
    //Metodo que suministra todas las dosis esenciales a el animal recibido por 
    //parametro.
    public void suministrarDosisEscencial(Animal animal){
        String consulta= "SELECT V.id "
                + "FROM vacuna V, vacuna_especie VE, especie E "
                + "WHERE V.id=VE.id_vacuna AND VE.id_especie=E.id AND "
                + "E.tipo='"+ animal.getEspecie() +"' AND V.escencial=1";
        
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()){
                int id = resultado.getInt("V.id");
                PreparedStatement sentencia2 = connection.prepareStatement(""
                        + "INSERT INTO dosis VALUES ("+ animal.getId() +","
                        + id +",'"+ fechaActual +"')");
                sentencia2.executeUpdate();
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(vacunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Suministrar una dosis especifica a un animal seleccionado.
    public int suministrarDosis(int id,String nombre){
        int key = -1;
        String consulta= "INSERT INTO dosis VALUES("+id+",("
                + "SELECT id FROM vacuna WHERE nombre='"+ nombre +"'),"
                + "'"+fechaActual+"')";
        
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            key = sentencia.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(vacunaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public int update(int id,String nombre,String peso,String caracteristicas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
