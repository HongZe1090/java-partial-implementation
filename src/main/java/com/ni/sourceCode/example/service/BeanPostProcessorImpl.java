package com.ni.sourceCode.example.service;

import com.ni.sourceCode.spring.BeanPostProcessor;
import com.ni.sourceCode.spring.Component;

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
        return bean;
    }
}
