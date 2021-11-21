package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.core.io.Resource;
import com.ni.sourceCode.core.io.ResourceLoader;
import com.ni.sourceCode.spring.BeansException;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/21
 * @描述 加载bean的方法 加载和注册，这俩个方法的实现会封装到抽象类中 以免污染具体的接口实现方法
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
