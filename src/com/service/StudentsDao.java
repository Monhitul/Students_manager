package com.service;

import com.entity.Students;

import java.util.List;

//学生的业务逻辑接口
public interface StudentsDao {
    //查询所有学生资料
    public List<Students> queryAllStudents();

    //根据学生编号查询学生资料
    public Students queryStudentsBySid(String sid);

    //添加学生资料
    public boolean addStudents(Students s);

    //修改学生资料
    public boolean deleteStudents(String sid);
}
