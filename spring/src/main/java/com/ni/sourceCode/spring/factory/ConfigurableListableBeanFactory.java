package com.ni.sourceCode.spring.factory;

import com.ni.sourceCode.spring.BeansException;
import com.ni.sourceCode.spring.factory.config.AutowireCapableBeanFactory;
import com.ni.sourceCode.spring.factory.config.BeanDefinition;
import com.ni.sourceCode.spring.factory.config.BeanPostProcessor;
import com.ni.sourceCode.spring.factory.config.ConfigurableBeanFactory;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
