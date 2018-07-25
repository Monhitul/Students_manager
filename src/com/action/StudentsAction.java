package com.action;

import com.entity.Students;
import com.service.StudentsDao;
import com.service_impl.StudentsDaoImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//学生Action类
public class StudentsAction extends SuperAction {
    //查询所有学生的动作
    public String query(){
        StudentsDao sdao = new StudentsDaoImpl();
        List<Students> list=sdao.queryAllStudents();
        //放进session中
        if (list!=null&&list.size()>0){
            session.setAttribute("students_list",list);
        }
        return "Students_query_success";
    }

    //删除学生动作
    public String delete(){
        StudentsDao sdao=new StudentsDaoImpl();
        String sid=request.getParameter("sid");
        sdao.deleteStudents(sid);
        return "delete_success";
    }

    //添加学生动作
    public String add(){
        StudentsDao sdao=new StudentsDaoImpl();
        Students s=new Students();
        String sid=request.getParameter("sid");
        String sname=request.getParameter("sname");
        String gender=request.getParameter("gender");
        String birthday=request.getParameter("birthday");
        String address=request.getParameter("address");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        s.setSid(sid);
        s.setSname(sname);
        s.setGender(gender);
        s.setBirthday(date);
        s.setAddress(address);

        boolean t=sdao.addStudents(s);
        return "add_success";
    }

    //修改学生资料动作
    public String modify(){
        StudentsDao sdao=new StudentsDaoImpl();
        String sid=request.getParameter("sid");
        Students s=sdao.queryStudentsBySid(sid);

        System.out.println(s);

        session.setAttribute("modify_students",s);
        return "modify_success";
    }

    //修改学生资料保存动作
    public String save(){
        StudentsDao sdao=new StudentsDaoImpl();
        Students s=new Students();
        String sid=request.getParameter("sid");
        s.setSid(sid);
        s.setSname(request.getParameter("sname"));
        s.setGender(request.getParameter("gender"));
        String birthday=request.getParameter("birthday");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        s.setBirthday(date);
        s.setAddress(request.getParameter("address"));
        sdao.deleteStudents(sid);
        sdao.addStudents(s);
        return "save_success";
    }
}
