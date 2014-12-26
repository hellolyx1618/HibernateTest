package com.test;

import com.lyx.*;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Lenovo on 2014/12/3.
 */
public class PessimisticLockTest {
    UserDao userDao;
    RoleDao roleDao;
    WorkDao workDao;

    @Before
    public void beginTransaction() {
        this.userDao = new UserDao();
        this.roleDao = new RoleDao();
        this.workDao = new WorkDao();
        HibernateUtil.getSessionFactory().getCurrentSession()
                .beginTransaction();
        System.out.println("before=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());

    }


    /**
     * 测试UPGRADE锁
     */
    @Test
    public void test892() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Worker as w where id = 1");
        query.setLockMode("w", LockMode.UPGRADE);
        Worker worker = (Worker) query.uniqueResult();
        worker.setAge(10);
        System.out.println(worker.toString());
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
        System.out.println("commit end");
    }


    /**
     * LockMode.READ
     */
    @Test
    public void test8921() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Worker as w where id = 1");
        query.setLockMode("w", LockMode.READ);
        Worker worker = (Worker) query.uniqueResult();
//        worker.setAge(8);
        System.out.println(worker.toString());
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("sleep end");
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
        System.out.println("commit end");
    }

    /**
     * LockMode.UPGRADE
     */
    @Test
    public void test98766() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Worker worker = (Worker) session.load(Worker.class, 1);
        session.lock(worker, LockMode.UPGRADE);
        System.out.println(worker.toString());
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("sleep end");
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
        System.out.println("commit end");
    }


    @Test
    public void test766() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Worker worker = (Worker) session.get(Worker.class, 1);
        session.buildLockRequest(new LockOptions(LockMode.UPGRADE)).lock(worker);
        worker.setName("jjjjjjjjjjjjjj");
        System.out.println(session.getCurrentLockMode(worker));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep end");
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
        System.out.println("commit end");
    }

}
