package com.ni.sourceCode.spring.factory.config;

import com.ni.sourceCode.spring.BeansException;
import com.ni.sourceCode.spring.factory.ConfigurableListableBeanFactory;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
