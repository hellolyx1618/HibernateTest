package com.test;

import com.lyx.HibernateUtil;
import com.lyx.WorkDao;
import com.lyx.Worker;
import org.hibernate.Session;

/**
 * 测试乐观锁
 */
public class AppMain1 {

    public static void main(String args[]) {
        final WorkDao workDao = new WorkDao();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Session session1 = HibernateUtil.getSessionFactory().getCurrentSession();
                System.out.println("current session1 hashcode=" + HibernateUtil.getSessionFactory()
                        .getCurrentSession().hashCode());
                session1.getTransaction().begin();
                session1.getTransaction().setTimeout(Integer.MAX_VALUE);
                Worker worker1 = workDao.get(1);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 sleep end");
                System.out.println("session1 version=" + worker1.getVersion());
                worker1.setName(Thread.currentThread().getName());
                System.out.println("session1 after setName version=" + worker1.getVersion());
//                workDao.update(worker1);
                System.out.println("session1 update");
                session1.getTransaction().commit();
                System.out.println("session1 commit");
                System.out.println("session1 after commit version=" + worker1.getVersion());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
                System.out.println("current session2 hashcode=" + HibernateUtil.getSessionFactory()
                        .getCurrentSession().hashCode());
                session2.getTransaction().begin();
                Worker worker2 = workDao.get(1);
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 sleep end");
                System.out.println("session2 version=" + worker2.getVersion());
                worker2.setName(Thread.currentThread().getName());
                System.out.println("session2 after setName version=" + worker2.getVersion());
//                workDao.update(worker2);
                System.out.println("session2 update");
                session2.getTransaction().commit();
                System.out.println("session2 commit");
                System.out.println("session2 after commit version=" + worker2.getVersion());
            }
        });

        t1.start();
        t2.start();
    }
}
