package dao;

import model.Customer;

import java.util.HashMap;
import java.util.List;

public interface GenericDaoI<T> extends BaseDaoI<T> {

    List<T> findByColumn(Object entity, HashMap<String,String> items);

}
