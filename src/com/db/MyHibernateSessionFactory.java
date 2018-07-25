package com.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyHibernateSessionFactory {
    private static SessionFactory sessionFactory;//会话工厂属性

    //构造方法私有化，保证单例模式
    private MyHibernateSessionFactory(){

    }

    //公用静态方法，获得会话工厂对象
    public static SessionFactory getSessionFactory(){
        if (sessionFactory==null){
            Configuration config=new Configuration().configure();
            sessionFactory=config.buildSessionFactory();
            return sessionFactory;
        }else {
            return sessionFactory;
        }
    }
}
