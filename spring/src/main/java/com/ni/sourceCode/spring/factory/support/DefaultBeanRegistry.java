package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 主要实现了getSingleton方法，同时实现了一个受保护的addSingleton方法，这个方法 可以被继承此类其他类调用
 */
public class DefaultBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
    }
}
