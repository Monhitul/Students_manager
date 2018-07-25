package com.action;

import com.entity.Users;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UsersDao;
import com.service_impl.UserDaoImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

    private Users user=new Users();

    @Override
    public Users getModel() {
        return this.user;
    }

    //用户登录动作
    public String login(){
        UsersDao udao=new UserDaoImpl();//UsersDao可换成UserDaoImpl
        if (udao.usersLogin(user)){
            //在session中保存登录成功的用户名
            session.setAttribute("loginUserName",user.getUsername());

            return "login_success";
        }else{
            return "login_failure";
        }
    }

    //用户注销方法
    public String logout(){
        if(session.getAttribute("loginUserName")!=null){
            session.removeAttribute("loginUserName");
        }
        return "logout_success";
    }
}
