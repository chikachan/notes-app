package tabitabi.picco.service;

import java.io.Serializable;
import java.util.Collection;

interface DataService<T, ID extends Serializable> {
   
    void create(T t);
    
    void delete(ID id);
    
    T find(ID id);
    
    Collection<T> findAll();

    T update(T t);
}
