package com.ni.FirstSpring.example.service;

import com.ni.FirstSpring.Application.Autowired;
import com.ni.FirstSpring.Application.BeanNameAware;
import com.ni.FirstSpring.Application.Component;
import com.ni.FirstSpring.Application.Scope;

//Scope表明这是一个原型bean
//单例bean每次获取是不同的
@Component("userService")
@Scope("prototype")
public class UserService implements BeanNameAware,UserInterface {

    @Autowired
    OrderService orderService;

    private String beanName;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    public void test() {
        System.out.println(orderService);
        System.out.println("这里是BeanNameAware回调后的函数"+beanName);
    }
}
