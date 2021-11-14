package com.ni.sourceCode.spring.factory.config;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 定义了一个获取单例对象的接口
 */

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
