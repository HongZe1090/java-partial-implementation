package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.BeansException;
import com.ni.sourceCode.spring.factory.BeanFactory;
import com.ni.sourceCode.spring.factory.config.BeanDefinition;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 首先继承了DefaultBeanRegistry 可以使用单例注册类方法，如果单例无法获取，则调用相应的bean实例化操作
 */
public abstract class AbstractBeanFactory extends DefaultBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

//        单例池中没有，就createBean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
