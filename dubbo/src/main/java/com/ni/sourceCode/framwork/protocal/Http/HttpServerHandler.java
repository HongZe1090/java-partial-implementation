package com.ni.sourceCode.framwork.protocal.Http;

import com.alibaba.fastjson.JSONObject;
import com.ni.sourceCode.framwork.protocal.Invocation;
import com.ni.sourceCode.framwork.registry.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse res) {
//        处理请求的逻辑，目的
//        解析请求-->调用哪个服务-->那个接口，接口的那个方法，方法参数的设置
//        反序列话取值
        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);

            String interfaceName = invocation.getInterfaceName(); //如何找到实现类？
            String methodName = invocation.getMethodName();
            Class[] paramType = invocation.getParamType();
            Object[] params = invocation.getParams();

//            反射机制
            Class impl = LocalRegister.get(interfaceName);
            try {
                Method method = impl.getMethod(methodName, paramType);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

//            http请求在服务器端没有添加
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
