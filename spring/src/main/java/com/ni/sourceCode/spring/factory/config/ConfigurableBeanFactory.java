package com.ni.sourceCode.spring.factory.config;

import com.ni.sourceCode.spring.factory.HierarchicalBeanFactory;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/21
 * @描述
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry{
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
