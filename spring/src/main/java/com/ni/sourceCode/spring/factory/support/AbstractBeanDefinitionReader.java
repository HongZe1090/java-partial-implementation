package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.core.io.DefaultResourceLoader;
import com.ni.sourceCode.core.io.Resource;
import com.ni.sourceCode.core.io.ResourceLoader;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/21
 * @描述 装饰类，增强接口 getRegistry getResourceLoader 都是用于提供给后面三个方法的工具
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    protected BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    protected ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
