package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.factory.config.BeanDefinition;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述
 */
public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
