package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class BaseDao<T> implements BaseDaoI<T> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;


    public void setClazz(Class clazzToSet) {
        this.clazz = clazzToSet;
    }

    public Class<T> getClazz() { return this.clazz; }

    public T findById(final int id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public T create(final Object entity) {
        entityManager.persist(entity);
        return (T) entity;
    }

    public T update(final Object entity) {
        return (T) entityManager.merge(entity);
    }

    public void delete(final Object entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final int entityId) {
        final T entity = findById(entityId);
        delete(entity);
    }
}