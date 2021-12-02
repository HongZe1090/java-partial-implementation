package com.ni.sourceCode.context.support;

import com.ni.sourceCode.spring.BeansException;
import com.ni.sourceCode.spring.factory.ConfigurableListableBeanFactory;
import com.ni.sourceCode.spring.factory.support.DefaultListableBeanFactory;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return (ConfigurableListableBeanFactory) beanFactory;
    }
}
