package com.ni.sourceCode.comsumer;

import com.ni.sourceCode.framwork.protocal.Http.HttpClient;
import com.ni.sourceCode.framwork.protocal.Invocation;
import com.ni.sourceCode.provider.HelloService;

public class Consumer {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        Invocation invocation = new Invocation(HelloService.class.getName(),"sayHello",new Class[]{String.class},new Object[]{"123456"});

        httpClient.send("localhost",8080,invocation);
    }
}
