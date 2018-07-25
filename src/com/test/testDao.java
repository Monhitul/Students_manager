package com.test;

import com.entity.Students;
import com.service.StudentsDao;
import com.service_impl.StudentsDaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class testDao {
    public static void testQueryAllStudents(){
        StudentsDao sdao=new StudentsDaoImpl();
        List<Students> list =sdao.queryAllStudents();

        for(Students s:list){
            System.out.println(s);
        }
    }

    public static void testAddStudents(){
        StudentsDao sdao=new StudentsDaoImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("1997-10-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Students s=new Students("1001","陈阿瓜","男",date,"华南理工大学");
        boolean t=sdao.addStudents(s);
        System.out.println(t);
    }

    public static void main(String[] args){
//        testQueryAllStudents();
        testAddStudents();
    }
}
