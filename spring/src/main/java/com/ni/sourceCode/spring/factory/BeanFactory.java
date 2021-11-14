package com.ni.sourceCode.spring.factory;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述
 */
public interface BeanFactory {
    public Object getBean(String name) throws InstantiationException, IllegalAccessException;
}
