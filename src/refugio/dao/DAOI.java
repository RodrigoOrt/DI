package refugio.dao;

import java.util.Collection;
import java.util.List;

/**
 * @description
 * Iterfaz DAO 
 * @author rodri
 */

public interface DAOI<T,ID> {
    int update(int id,String nombre,String peso,String caracteristicas);
    int insert(T t);
    List<String> getValorPorEspecie(String especie);
    Collection<T> getAll();
}
