package base.service;

import java.util.List;

public interface GenericService <T, Pk> {

    public void create(T object);
    public void update(T object);
    public void delete(Pk key);
    public T getObjectById(Pk key);
    public List<T> getAllObjects();
}