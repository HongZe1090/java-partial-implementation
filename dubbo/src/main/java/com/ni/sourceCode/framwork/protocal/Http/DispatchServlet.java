package com.ni.sourceCode.framwork.protocal.Http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//tomcat中处理的请求的HttpServelet
public class DispatchServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServerHandler httpServerHandler = new HttpServerHandler();
        httpServerHandler.handler(req, resp);
    }
}
