package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.factory.BeanFactory;
import com.ni.sourceCode.spring.factory.config.BeanDefinition;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 首先继承了DefaultBeanRegistry 可以使用单例注册类方法，如果单例无法获取，则调用相应的bean实例化操作
 */
public abstract class AbstractBeanFactory extends DefaultBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws InstantiationException, IllegalAccessException {
//        如果可以从继承的单例池中获取
        Object bean = getSingleton(name);
        if(bean!=null)
            return bean;
//        如果不能
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return creatBean(name,beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object creatBean(String beanName,BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException;
}
