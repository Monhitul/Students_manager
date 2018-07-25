package com.service_impl;

import com.db.MyHibernateSessionFactory;
import com.entity.Users;
import com.service.UsersDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UsersDao {
    @Override
    public boolean usersLogin(Users u) {
        //事务对象
        Transaction tx=null;
        String hql="";
        try {
            System.out.println(u.getUsername());
            System.out.println(u.getPassword());
            Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();//开始事务
            hql=" from Users where username=:username and password=:password";

            Query<Users> query=session.createQuery(hql);
            query.setParameter("username",u.getUsername());
            query.setParameter("password",u.getPassword());
            System.out.println(hql);
            List<Users> list=query.list();

            System.out.println(list);
            tx.commit();
            if(list.size()>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if(tx!=null){
               // tx.commit();
                tx=null;
            }
        }
    }
}
