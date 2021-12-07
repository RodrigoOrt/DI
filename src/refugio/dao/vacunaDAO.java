package refugio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import refugio.model.Vacuna;
import refugio.util.ConnectionManager;

/**
 *
 * @author rodri
 */
public class vacunaDAO implements DAOI{

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
            Logger.getLogger(animalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunas;
    }

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
            Logger.getLogger(animalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunas;
    }
  
    public void suministrarDosisEscencial(){
        
    }
    
    public int suministrarDosis(String nombre){
        return 0;
    }
    
    
    @Override
    public Object get(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object save(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object read(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object t) {
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
