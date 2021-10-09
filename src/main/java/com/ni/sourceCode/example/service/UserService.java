package com.ni.sourceCode.example.service;

import com.ni.sourceCode.spring.Autowired;
import com.ni.sourceCode.spring.BeanNameAware;
import com.ni.sourceCode.spring.Component;
import com.ni.sourceCode.spring.Scope;

//Scope表明这是一个原型bean
//单例bean每次获取是不同的
@Component("userService")
@Scope("prototype")
public class UserService implements BeanNameAware {

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
