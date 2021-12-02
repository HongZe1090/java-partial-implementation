package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.BeansException;
import com.ni.sourceCode.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;  //拿到符合入参信息对应的构造函数

/**
 * @创建人 HongZe
 * @创建时间 2021/11/17
 * @描述 添加必要的入参信息（好像在Fisco里面用过），实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;

}
