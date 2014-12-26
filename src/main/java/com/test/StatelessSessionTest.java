package com.test;

import com.lyx.*;
import org.hibernate.*;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;

public class StatelessSessionTest {
    UserDao userDao;
    RoleDao roleDao;

    /**
     * openStatelessSession
     */
    @Before
    public void beginTransaction() {
        this.userDao = new UserDao();
        this.roleDao = new RoleDao();
    }


    @Test
    public void test8656() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        Role role = (Role) session.get(Role.class, 3);
        if (role.getUsers() != null) {
            System.out.println("users size=" + role.getUsers().size());
        } else {
            System.out.println("users is null");
        }
        tx.commit();
        System.out.println("commit end");
    }

    @Test
    public void test766() {
        StatelessSession statelessSession = HibernateUtil.getSessionFactory().
                openStatelessSession();
        Transaction tx = statelessSession.getTransaction();
        tx.begin();
        Role role = (Role) statelessSession.get(Role.class, 3);
        if (role.getUsers() != null) {
            System.out.println("users size=" + role.getUsers().size());
        } else {
            System.out.println("users is null");
        }
        tx.commit();
        System.out.println("commit end");
    }


    @Test
    public void test86856756() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        User user = (User) session.get(User.class, 1);
        System.out.println(user.getRole().getId());
        System.out.println(user.getRole().getRoleName());
        tx.commit();
        System.out.println("commit end");
    }

    @Test
    public void test723266() {
        StatelessSession statelessSession = HibernateUtil.getSessionFactory().
                openStatelessSession();
        Transaction tx = statelessSession.getTransaction();
        tx.begin();
        User user = (User) statelessSession.get(User.class, 1);
        System.out.println(user.getRole().getId());
        System.out.println(user.getRole().getRoleName());
        tx.commit();
        System.out.println("commit end");
    }

}
