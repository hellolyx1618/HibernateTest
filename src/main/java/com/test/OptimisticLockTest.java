package com.test;

import com.lyx.HibernateUtil;
import com.lyx.WorkDao;
import com.lyx.Worker;
import org.junit.After;
import org.junit.Before;

import com.lyx.RoleDao;
import com.lyx.UserDao;
import org.junit.Test;

public class OptimisticLockTest {
    UserDao userDao;
    RoleDao roleDao;
    WorkDao workDao;

    /**
     * openStatelessSession
     */
    @Before
    public void beginTransaction() {
        userDao = new UserDao();
        roleDao = new RoleDao();
        workDao = new WorkDao();
        HibernateUtil.getSessionFactory().getCurrentSession()
                .beginTransaction();
        System.out.println(HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());

    }

    @After
    public void commit() {
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
        System.out.println(HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
    }

    @Test
    public void testOptimisticLock() {
        Worker worker = new Worker("lyx", 12);
        workDao.save(worker);

        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
    }

}
