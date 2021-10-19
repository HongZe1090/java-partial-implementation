package com.ni.sourceCode.provider;

import com.ni.sourceCode.framwork.protocal.Http.HttpServer;

//API，提供接口
public class provider {
//    用户配置tomcat，netty，jetty在此打开
public static void main(String[] args) {
    HttpServer httpServer = new HttpServer();
    httpServer.httpStart("localhost", 8080);

//    暴露接口，本地注册

    }
}
