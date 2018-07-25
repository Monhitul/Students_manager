package com.service_impl;

import com.db.MyHibernateSessionFactory;
import com.entity.Students;
import com.service.StudentsDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

//学生业务逻辑的实现类
public class StudentsDaoImpl implements StudentsDao {

    //获得所有学生
    @Override
    public List<Students> queryAllStudents() {
        Transaction tx=null;
        List<Students> list=null;
        try {
            Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            String hql="from Students";
            Query query=session.createQuery(hql);
            list=query.list();
            tx.commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            tx.commit();
            return list;
        }finally {
            if (tx!=null){
                tx=null;
            }
        }
    }

    //由学号查找学生
    @Override
    public Students queryStudentsBySid(String sid) {
        Transaction tx=null;
        Students s=null;
        try{
            Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            String hql="from Students where sid=:sid";
            Query<Students> query=session.createQuery(hql);
            query.setParameter("sid",sid);
            List<Students> list=query.list();
            s=list.get(0);
            tx.commit();
            return s;
        }catch (Exception e){
            e.printStackTrace();
            tx.commit();
            return s;
        }finally {
            if (tx!=null){
                tx=null;
            }
        }
    }

//    添加新的学生
    @Override
    public boolean addStudents(Students s) {
        Transaction tx=null;
        try{
            Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            session.save(s);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            tx.commit();
            return false;
        }finally {
            if (tx!=null){
                tx=null;
            }
        }
    }

//    删除学生
    @Override
    public boolean deleteStudents(String sid) {
        Transaction tx=null;
        try{
            Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            Students s=(Students)session.get(Students.class,sid);
            session.delete(s);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            tx.commit();
            return false;
        }finally {
            if (tx!=null){
                tx=null;
            }
        }
    }
}
