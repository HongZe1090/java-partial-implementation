package com.ni.sourceCode.framwork.protocal.Http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {
//指定域名和接口 配置内嵌的tomcat
    public void httpStart(String hostname , Integer port) {
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
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

//      拦截请求解析
        tomcat.addServlet(contextPath,"dispatcher",new DispatchServlet());
        context.addServletMappingDecoded("/*","dispatcher");

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }

    public void dobboStart() {
//        启动dubbo
    }


}
