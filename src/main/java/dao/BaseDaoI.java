package dao;

import java.util.List;

public interface BaseDaoI<T> {

    void setClazz(Class< T > clazzToSet);

    Class<T> getClazz();

    T findById(final int id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final int entityId);
}
