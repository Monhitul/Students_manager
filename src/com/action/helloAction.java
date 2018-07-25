package com.action;

import com.opensymphony.xwork2.ActionSupport;

public class helloAction extends ActionSupport {
    public String login(){
        System.out.println("跳转成功");
        return "hello_success";
    }
}
