package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.factory.config.BeanDefinition;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 实现了bean的实例化操作
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException {
        Object bean = null;

        bean = beanDefinition.getClazz().newInstance();

        addSingleton(beanName,bean);

        return bean;
    }
}
