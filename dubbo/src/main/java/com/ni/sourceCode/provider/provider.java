package com.ni.sourceCode.provider;

import com.ni.sourceCode.framwork.protocal.Http.HttpServer;
import com.ni.sourceCode.framwork.registry.LocalRegister;

//API，提供接口
public class provider {
//    用户配置tomcat，netty，jetty在此打开
public static void main(String[] args) {
    //    暴露接口，本地注册 版本号控制对应的接口实现类
//    LocalRegister.register(HelloService.class.getName()+version1 , HelloServiceImpl.class);
    LocalRegister.register(HelloService.class.getName() , HelloServiceImpl.class);

    HttpServer httpServer = new HttpServer();
    httpServer.httpStart("localhost", 8080);

    }
}
