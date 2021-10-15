package com.ni.sourceCode.example.service;

import com.ni.sourceCode.spring.BeanPostProcessor;
import com.ni.sourceCode.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {

    //默认每个bean都会判断
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("这里是每次初始化前");
        if(beanName.equals("userService")) {
            ((UserService)bean).setName("这里是postProcessBeforeInitialization方法的使用，程序猿自定义BeanPostProcesso, userServicer实现接口");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("这里是每次初始化后");
        if (beanName.equals("userService")) {

            Object proxyInstance = Proxy.newProxyInstance(UserService.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("代理逻辑");
                    return method.invoke(bean,args);
                }
            });

            return proxyInstance;
        }

        return bean;
    }
}
