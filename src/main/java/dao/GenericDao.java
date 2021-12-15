package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

public class GenericDao<T> extends BaseDao<T> implements GenericDaoI<T> {

    @Override
    public List<T> findByColumn(Object entity, HashMap<String, String> items) {
        String columnName = "";
        String columnValue = "";

        for (String key: items.keySet()) {
            columnName = key;
            columnValue = items.get(key);
        }

        String hql = "FROM " +  entity.toString() + " E WHERE E." + columnName + " = :column_value";
        Query query = entityManager.createQuery(hql);
        query.setParameter("column_value", columnValue);
        return query.getResultList();
    }
}
