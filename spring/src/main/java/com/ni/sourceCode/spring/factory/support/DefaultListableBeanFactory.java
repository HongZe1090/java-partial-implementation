package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.factory.config.BeanDefinition;

import javax.management.MBeanException;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 核心类，接口定义了注册，抽象类定义了获取
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null)
            return null;
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
