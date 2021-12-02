package refugio.dao;

import java.util.Collection;
/**
 *
 * @author rodri
 */
public interface DAOI<T,ID> {
    T get(ID id);
    ID save(T t);
    T read(Integer id);
    void update(T t);
    void insert(T t);
    void dosisEsencial();//Suministrar todas las dosis esenciales.
    void suministrarDosis(T t);//Suministrar una dosis especifica.
    Collection<T> getAll();//Obtener lista de animales.
}
