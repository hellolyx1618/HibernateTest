package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyx.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EnumType;

public class HibernateTest {

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

//    @After
//    public void commit() {
//        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
//                .commit();
//        System.out.println("after=" + HibernateUtil.getSessionFactory()
//                .getCurrentSession().hashCode());
//    }

    @Test
    public void test21() {
        Role role = new Role();
        role.setDescription(Thread.currentThread().getName());
        role.setRoleName("admin");
        roleDao.save(role);
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
    }

    /**
     * 测试 base dao save 方法
     */
    @Test
    public void test89() {
        Role role = roleDao.get(1);
        User user = new User("lyx", 23, role);
        user.setGender(Gender.FMAIL);
        User user1 = new User("lyx", 23, role);
        user1.setGender(Gender.MAIL);
        User user2 = new User("lyx", 23, role);
        user2.setGender(Gender.FMAIL);
        User user3 = new User("lyx", 23, role);
        user3.setGender(Gender.FMAIL);
        User user4 = new User("lyx", 23, role);
        user4.setGender(Gender.UNSPECIFIED);

        this.userDao.save(user);
        this.userDao.save(user1);
        this.userDao.save(user2);
        this.userDao.save(user3);
        this.userDao.save(user4);

        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
    }

    /**
     * 测试base dao executeQuery 方法
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test891() {
        String hql = "from User where name =:name";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "lyx");
        List<User> users = (List<User>) this.userDao.executeQuery(hql, params);
        System.out.println(users.size());
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
    }

    /**
     * 测试base dao executeQuery 方法 List<Map<String, Object>> 返回
     */
    @Test
    public void test893() {
        String hql = "select new map(u.name,u.age) from User u where u.name =:name";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "lyx");
        List<Map<String, Object>> users = (List<Map<String, Object>>) this.userDao
                .executeQuery(hql, params);
        System.out.println(users.size());
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
    }

    @Test
    public void test892() {
        String hql = "update User u set u.name = :name where u.id % 2 = 0";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "hello");
        int n = this.userDao.executeUpdateOrDelete(hql, params);
        System.out.println(n);
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
    }

    @Test
    public void test894() {
        Role role = new Role();
        role.setDescription(Thread.currentThread().getName());
        role.setRoleName("manager");
        roleDao.save(role); //在session flush之前，请确保save

        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            User user = new User("lyx" + i, 23, role);
            users.add(user);
        }
        int n = this.userDao.executeSaveByBatch(users, 10);
        System.out.println("=======" + n);
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
    }


    /**
     * org.hibernate.exception.LockTimeoutException: could not execute statement
     */
    @Test
    public void test895() {
        Worker worker = workDao.get(1);
        System.out.println(worker.getVersion());
        worker.setName(Thread.currentThread().getName());
        System.out.println("commit=" + HibernateUtil.getSessionFactory()
                .getCurrentSession().hashCode());
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
                .commit();
        System.out.println("commit end");
    }


    @Test
    public void tes797ui() {
        System.out.println(EnumType.STRING.getClass().isAssignableFrom(Enum.class));
    }

}
