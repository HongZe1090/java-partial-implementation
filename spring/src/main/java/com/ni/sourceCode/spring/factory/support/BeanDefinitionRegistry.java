package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.factory.config.BeanDefinition;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 beanDefinition注册接口
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);


}
