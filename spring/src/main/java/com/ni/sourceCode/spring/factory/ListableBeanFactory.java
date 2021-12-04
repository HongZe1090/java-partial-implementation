package com.ni.sourceCode.spring.factory;

import com.ni.sourceCode.spring.BeansException;

import java.util.Map;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 继承了BeanFactory的相关方法
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
