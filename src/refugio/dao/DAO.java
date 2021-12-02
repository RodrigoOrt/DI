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
import java.util.Date;

/**
 *
 * @author rodri
 */
public class DAO implements DAOI{
    
    @Override
    public Collection<Animal> getAll(){
        List<Animal> animales = new ArrayList();
        try(Connection connection = ConnectionManager.getInstance().getConnection();
            PreparedStatement sentencia = connection.prepareStatement(
                    "Select * "
                    + "FROM animal A,raza R,especie E "
                    + "WHERE A.id_raza_predominante=R.id AND R.idespecie=E.id "
                    + "ORDER BY A.fecha_arribo ASC")){
            ResultSet resultado = sentencia.executeQuery();
            
            while (resultado.next()){
                int id = resultado.getInt("id");
                String nombre = resultado.getString("A.nombre");
                String sexo = resultado.getString("sexo");
                Date fecha_nac = resultado.getDate("fecha_nac");
                String color = resultado.getString("color_predominante");
                int id_raza = resultado.getInt("id_raza_predominante");
                String raza = resultado.getString("R.nombre");
                String especie = resultado.getString("tipo");
                double peso = resultado.getDouble("peso");
                Date fecha_alta = resultado.getDate("fecha_arribo");
                Date fecha_adp = resultado.getDate("fecha_adopcion");
                String caracteristicas = resultado.getString("características");
                
                Animal animal = new Animal(id,nombre,sexo,fecha_nac,color,id_raza,
                        raza,especie,peso,fecha_alta,fecha_adp,caracteristicas);
                System.out.println(id + "/" + nombre + "/" + sexo + "/" + fecha_nac + "/" + color + "/" + id_raza + "/" + raza + "/" + especie + "/" + peso + "/" + fecha_alta + "/" + fecha_adp + "/" + caracteristicas );
                animales.add(animal);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return animales;
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
    public void insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dosisEsencial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void suministrarDosis(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.getAll();
    }
    
}
