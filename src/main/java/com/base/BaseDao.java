package com.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lyx.HibernateUtil;

public class BaseDao<T extends Serializable, PK extends Serializable> {

    /**
     * 初始化sessionFactory
     */
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public void delete(T t) {
        getCurrentSession().delete(t);
    }

    public void update(T t) {
        getCurrentSession().update(t);
    }

    @SuppressWarnings("unchecked")
    public PK save(T t) {
        return (PK) getCurrentSession().save(t);
    }

    public void saveOrUpdate(T t) {
        getCurrentSession().saveOrUpdate(t);
    }

    public void persist(T t) {
        getCurrentSession().persist(t);
    }

    public void merge(T t) {
        getCurrentSession().merge(t);
    }

    @SuppressWarnings("unchecked")
    public T get(PK id) {
        System.out.println("current session hashcode=" + this.getCurrentSession().hashCode());
        return (T) this.getCurrentSession().get(this.getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    public T load(PK id) {
        return (T) this.getCurrentSession().load(this.getEntityClass(), id);
    }

    /**
     * 设置查询参数
     *
     * @param query
     * @param params
     * @return
     */
    public Query simpleSetParameter(Query query, Map<String, Object> params) {
        if (params != null) {
            if (params.containsKey("page") && params.containsKey("rows")) {
                query.setFirstResult((((Integer) params.get("page")).intValue() - 1)
                        * ((Integer) params.get("rows")).intValue());
                query.setMaxResults(((Integer) params.get("rows")).intValue());
            }
            query.setProperties(params);
        }
        return query;
    }

    /**
     * save 的batch 操作
     *
     * @param entities
     * @param batchSize
     * @return
     */
    public int executeSaveByBatch(List<T> entities, int batchSize) {
        AtomicInteger count = new AtomicInteger();
        for (T t : entities) {
            getCurrentSession().save(t);

            if (count.incrementAndGet() % batchSize == 0) {
                getCurrentSession().flush();
                System.out.println("session flush");
                getCurrentSession().clear();
            }
        }
        return count.intValue();
    }

    /**
     * update的batch 操作
     *
     * @param entities
     * @param batchSize
     * @return
     */
    public int executeUpdateByBatch(List<T> entities, int batchSize) {
        AtomicInteger count = new AtomicInteger();
        for (T t : entities) {
            getCurrentSession().update(t);

            if (count.incrementAndGet() % batchSize == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
        return count.intValue();
    }

    /**
     * delete的batch 操作
     *
     * @param entities
     * @param batchSize
     * @return
     */
    public int executeDeleteByBatch(List<T> entities, int batchSize) {
        AtomicInteger count = new AtomicInteger();
        for (T t : entities) {
            getCurrentSession().delete(t);

            if (count.incrementAndGet() % batchSize == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
        return count.intValue();
    }

    // return The number of entities updated or deleted.
    public int executeUpdateOrDelete(String queryString,
                                     Map<String, Object> params) {
        Query query = getCurrentSession().createQuery(queryString);
        return simpleSetParameter(query, params).executeUpdate();
    }

    // return The number of entities updated or deleted.
    public int executeUpdateOrDeleteBySQL(String queryString,
                                          Map<String, Object> params) {
        Query query = getCurrentSession().createSQLQuery(queryString);
        return simpleSetParameter(query, params).executeUpdate();
    }

    /**
     * 查询 返回类型可以是 List<Map<String, Object>>或 List<T>
     *
     * @param queryString
     * @param params
     * @return
     */
    public List<?> executeQuery(String queryString, Map<String, Object> params) {
        Query query = getCurrentSession().createQuery(queryString);
        return simpleSetParameter(query, params).list();
    }

    /**
     * 查询 返回类型可以是 List<Map<String, Object>>或 List<T> 使用迭代的方式查询数据
     *
     * @param queryString
     * @param params
     * @return
     */
    public Iterator<?> executeQueryByIterator(String queryString,
                                              Map<String, Object> params) {
        Query query = getCurrentSession().createQuery(queryString);
        return simpleSetParameter(query, params).iterate();
    }

    /**
     * 查询单个实体
     *
     * @param queryString
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public T executeQueryBySingle(String queryString, Map<String, Object> params) {
        Query query = getCurrentSession().createQuery(queryString);
        return (T) simpleSetParameter(query, params).uniqueResult();
    }

    public long getDefaultQuantity() {
        String queryString = "select count(*) from "
                + this.getEntityClass().getSimpleName();
        return getQuantityByCondition(queryString, null);
    }

    public long getQuantityByCondition(String queryString,
                                       Map<String, Object> params) {
        Query query = getCurrentSession().createQuery(queryString);
        return (Long) simpleSetParameter(query, params).setReadOnly(true)
                .uniqueResult();
    }

}
