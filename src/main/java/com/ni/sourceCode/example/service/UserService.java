package com.ni.sourceCode.example.service;

import com.ni.sourceCode.spring.Component;
import com.ni.sourceCode.spring.Scope;

//Scope表明这是一个原型bean
//单例bean每次获取是不同的
@Component("userService")
@Scope("prototype")
public class UserService {

}
