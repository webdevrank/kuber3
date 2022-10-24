package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.GenericDao;
import com.rank.ccms.util.EntityMetaData;
import com.rank.ccms.util.OrderType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDaoImpl<T> extends HibernateTemplate implements GenericDao<T> {

    private Class<T> clazz;

    private EntityMetaData entityMetaData;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
        logger.info("AbstractDao initialized.. ");

    }

    @Override
    public EntityMetaData getEntityMetaData() {
        return entityMetaData;
    }

    public void setEntityMetaData(EntityMetaData entityMetaData) {
        this.entityMetaData = entityMetaData;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        setEntityMetaData(getMetaData());
    }

    @Transactional
    @Override
    public T saveRow(T instance) {
        try {
            saveOrUpdate(instance);
            flush();
            refresh(instance);
        } catch (DataAccessException e) {
            return null;
        }
        return instance;
    }

    @Transactional
    @Override
    public T mergeRow(T instance) {
        try {
            merge(instance);
            flush();
            refresh(instance);
        } catch (DataAccessException e) {
            return null;
        }
        return instance;
    }

    @Transactional
    @Override
    public void deleteRow(T instance) {
        delete(instance);
    }

    @Override
    public Boolean isIdentifierDirty(Object old, Object current) throws HibernateException {
        return getSessionFactory().getClassMetadata(getClazz()).getIdentifierType().isDirty(old, current, (SessionImplementor) getSessionFactory().getCurrentSession());
    }

    @Transactional
    @Override
    public T findNonDeletedById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getClazz())
                .add(Restrictions.idEq(id))
                .add(Restrictions.eq("deleteFlg", false));                           //For Delete status check
        return (T) findByCriteria(detachedCriteria).get(0);
    }

    @Transactional
    @Override
    public T findById(Long id) {
        return (T) getSessionFactory().getCurrentSession().get(getClazz(), id);
    }

    @Transactional
    @Override
    public List<T> findAllNonDeleted() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getClazz());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));                           //For Delete status check
        detachedCriteria.addOrder(Order.asc("id"));
        return (List<T>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return (List<T>) find("from " + getClazz().getName());
    }

    @Transactional
    @Override
    public List<T> findNonDeletedByExample(T instance, OrderType orderType, String orderBy) {
        Example example = Example.create(instance);
        example.ignoreCase();
        example.excludeZeroes();
        //example.excludeNone();

        DetachedCriteria criteria = DetachedCriteria.forClass(instance.getClass()).add(example);
        criteria.add(Restrictions.eq("deleteFlg", false));                           //For Delete status check                                      
        if (orderBy != null) {
            if (orderType.equals(OrderType.asc)) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }
        return (List<T>) findByCriteria(criteria);
    }

    @Transactional
    @Override
    public List<T> findByExample(T instance, OrderType orderType, String orderBy) {
        Example example = Example.create(instance);
        example.ignoreCase();
        example.excludeZeroes();
        //example.excludeNone();

        DetachedCriteria criteria = DetachedCriteria.forClass(instance.getClass()).add(example);
        if (orderBy != null) {
            if (orderType.equals(OrderType.asc)) {
                criteria.addOrder(Order.asc(orderBy));
            } else {
                criteria.addOrder(Order.desc(orderBy));
            }
        }
        return (List<T>) findByCriteria(criteria);
    }

    @Transactional
    @Override
    public List<T> findNonDeletedByProperties(Map mapList) {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(getClazz());
            criteria.add(Restrictions.eq("deleteFlg", false));                           //For Delete status check
            criteria.add(Restrictions.allEq(mapList));
            return (List<T>) findByCriteria(criteria);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public List<T> findByProperties(Map mapList) {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(getClazz());
            criteria.add(Restrictions.allEq(mapList));
            return (List<T>) findByCriteria(criteria);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void flush() {
        getSessionFactory().getCurrentSession().flush();
    }

    @Transactional
    @Override
    public List createQuerySingleResult(String queryString, Object... parameters) {
        return find(queryString, parameters);
    }

    private EntityMetaData getMetaData() {

        EntityMetaData entMetaData = new EntityMetaData();

        Class persistentClass = getClazz();

        if (persistentClass == null) {
            throw new NullPointerException("Not a Persistent Class");
        }

        ClassMetadata classMetadata = getSessionFactory().getClassMetadata(persistentClass);
        if (classMetadata == null) {
            throw new IllegalArgumentException("Class Metadata Not Found.");
        }
        if (classMetadata instanceof AbstractEntityPersister) {

            AbstractEntityPersister entityPersister = ((AbstractEntityPersister) classMetadata);

            entMetaData.setQualifiedClassName(entityPersister.getName());
            entMetaData.setTableName(entityPersister.getTableName());
            entMetaData.setEntityNameActual(entityPersister.getEntityName());
            entMetaData.setEntityColumnType(entityPersister.getType());
            entMetaData.setRootTableKeyColumnsLength(entityPersister.getRootTableKeyColumnNames().length);
            entMetaData.setRootTableKeyColumnsNames(entityPersister.getRootTableKeyColumnNames());
            entMetaData.setRootTableIdentifierColumnLength(entityPersister.getRootTableIdentifierColumnNames().length);
            entMetaData.setRootTableIdentifierColumnNames(entityPersister.getRootTableIdentifierColumnNames());
            entMetaData.setRootEntityName(entityPersister.getRootEntityName());
            entMetaData.setRootTableName(entityPersister.getRootTableName());
            entMetaData.setIdentifierPropertyName(entityPersister.getIdentifierPropertyName());
            entMetaData.setIdentifierColumnLength(entityPersister.getIdentifierColumnNames().length);
            entMetaData.setIdentifierColumnNames(entityPersister.getIdentifierColumnNames());
            entMetaData.setIdentifierColumnType(entityPersister.getIdentifierType());
            entMetaData.setPrimaryKeyColumnLength(entityPersister.getKeyColumnNames().length);
            entMetaData.setPrimaryKeyColumnNames(entityPersister.getKeyColumnNames());
            entMetaData.setMappedSuperclassName(entityPersister.getMappedSuperclass());
            entMetaData.setPropertyNamesLength(entityPersister.getPropertyNames().length);
            entMetaData.setPropertyNames(entityPersister.getPropertyNames());
            entMetaData.setPropertyColumnNames1Length(entityPersister.getPropertyColumnNames(1).length);
            entMetaData.setPropertyColumnNames1(entityPersister.getPropertyColumnNames(1));

            logger.info("MetaData initialized for Class > " + entMetaData.getEntityNameActual());

            return entMetaData;

        } else {
            throw new RuntimeException("Unknown implementation.");
        }
    }
}
