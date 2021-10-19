package com.ni.sourceCode.provider;

public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(Integer userName) {
        return null;
    }

    public String sayHello(String userName) {
        return "Hello "+ userName +"this dubbo";
    }
}
