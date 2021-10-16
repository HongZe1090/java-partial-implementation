package com.ni.sourceCode.framwork.protocal.Http;

import org.apache.catalina.Engine;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.parser.Host;

import java.sql.Connection;

public class HttpServer {

    public void httpStart(Integer port) {
//        启动tomcat,netty
//        tomcat也是基于socket
        Tomcat tomcat = new Tomcat();

//        配置tomcat
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        StandardHost host = new StandardHost();
        host.setName(hostname);

        String contextPath = "";


    }

    public void dobboStart() {
//        启动dubbo
    }


}
