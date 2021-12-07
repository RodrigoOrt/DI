package refugio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import refugio.model.Animal;
import refugio.util.ConnectionManager;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author rodri
 */
public class animalDAO implements DAOI<Animal,Integer>{
    
    LocalDate fechaActual = LocalDate.now();
    
    @Override
    public Collection<Animal> getAll(){
        String consulta= "Select * "
                    + "FROM animal A,raza R,especie E "
                    + "WHERE A.id_raza_predominante=R.id AND R.idespecie=E.id "
                    + "ORDER BY A.fecha_arribo ASC";
        
        List<Animal> animales = new ArrayList();
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()){
                int id = resultado.getInt("id");
                String nombre = resultado.getString("A.nombre");
                String sexo = resultado.getString("sexo");
                String fecha_nac = resultado.getString("fecha_nac");
                String color = resultado.getString("color_predominante");
                int id_raza = resultado.getInt("id_raza_predominante");
                String raza = resultado.getString("R.nombre");
                String especie = resultado.getString("tipo");
                double peso = resultado.getDouble("peso");
                String fecha_alta = resultado.getString("fecha_arribo");
                String fecha_adp = resultado.getString("fecha_adopcion");
                String caracteristicas = resultado.getString("características");
                Animal animal = new Animal(id,nombre,sexo,fecha_nac,color,id_raza,
                        raza,especie,peso,fecha_alta,fecha_adp,caracteristicas);
                animales.add(animal);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(animalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return animales;
    }
    
    @Override
    public List<String> getValorPorEspecie(String especie){
        String consulta= "Select nombre FROM raza R, especie E "
        + "WHERE E.id=R.idespecie AND E.tipo='"+ especie+"'";
        
        List<String> razas = new ArrayList();
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()){
                String nombre = resultado.getString("nombre");
                razas.add(nombre);
             
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(animalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return razas;
    }
    
    public int getIdRaza(String raza){
        String consulta= "Select id FROM raza WHERE nombre = '"+ raza +"'";
        int key = -1;
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta)){
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                key = resultado.getInt("id");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(animalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public void update(Animal t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int adoptar(Integer Id) {
        int key = -1;
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement("UPDATE animal SET fecha_adopcion = "
                    + "'"+ fechaActual + "'"
                    + "WHERE id="+Id)){
            key = sentencia.executeUpdate();
  
        }
        catch (SQLException ex) {
            Logger.getLogger(animalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public int insert(Animal t) {
        int key = -1;
        String consulta ="INSERT INTO animal(nombre,sexo,fecha_nac,color_predominante,"
                + "id_raza_predominante,peso,fecha_arribo,"
                + "características) VALUES ('"+ t.getNombre() +"','"+t.getSexo()+
                "','"+t.getFecha_nac()+"','"+t.getColor()+"',"
                + ""+t.getId_raza()+","+t.getPeso()+",'"+fechaActual+"',"
                + "'"+t.getCaracteristicas()+"')"; 
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(consulta,PreparedStatement.RETURN_GENERATED_KEYS)){
            sentencia.executeUpdate();
            ResultSet rs = sentencia.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(animalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public Animal get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer save(Animal t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal read(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*public static void main(String[] args) {
        animalDAO dao = new animalDAO();
        dao.getAll();
        LocalDate date = LocalDate.now();    
        LocalDate yesterday = date.minusDays(1);    
        LocalDate tomorrow = yesterday.plusDays(2);    
        System.out.println("Today date: "+date);    
        System.out.println("Yesterday date: "+yesterday);    
        System.out.println("Tomorrow date: "+tomorrow);   
    }*/
}
