package com.ni.sourceCode.spring.factory;

import com.ni.sourceCode.spring.BeansException;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述
 */
public interface BeanFactory {

    public Object getBean(String name) throws InstantiationException, IllegalAccessException;

    public Object getBean(String name,Object... agrs) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
