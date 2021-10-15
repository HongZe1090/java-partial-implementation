package com.ni.sourceCode.provider;

public class HelloServiceImpl implements HelloService{

    public String sayHello(String userName) {
        return "Hello "+ userName +"this dubbo";
    }
}
