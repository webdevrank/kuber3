package com.rank.ccms.dao;

import com.rank.ccms.util.EntityMetaData;
import com.rank.ccms.util.OrderType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;

public interface GenericDao<T> extends Serializable {

    EntityMetaData getEntityMetaData();

    Boolean isIdentifierDirty(Object old, Object current) throws HibernateException;

    List createQuerySingleResult(String queryString, Object... parameters);

    void deleteRow(T instance);

    List<T> findAllNonDeleted();

    List<T> findAll();

    List<T> findNonDeletedByExample(T instance, OrderType orderType, String orderBy);

    List<T> findByExample(T instance, OrderType orderType, String orderBy);

    T findNonDeletedById(Long id);

    T findById(Long id);

    List<T> findNonDeletedByProperties(Map mapList);

    List<T> findByProperties(Map mapList);

    void flush();

    T mergeRow(T instance);

    T saveRow(T instance);

}
