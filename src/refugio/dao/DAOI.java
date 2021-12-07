package refugio.dao;

import java.util.Collection;
import java.util.List;
/**
 *
 * @author rodri
 */
public interface DAOI<T,ID> {
    T get(ID id);
    ID save(T t);
    T read(Integer id);
    void update(T t);
    int insert(T t);
    List<String> getValorPorEspecie(String especie);
    Collection<T> getAll();
}
