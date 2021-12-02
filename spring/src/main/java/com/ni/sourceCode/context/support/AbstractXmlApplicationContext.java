package com.ni.sourceCode.context.support;

import com.ni.sourceCode.spring.factory.support.DefaultListableBeanFactory;
import com.ni.sourceCode.spring.factory.xml.XmlBeanDefinitionReader;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations[0]);
        }
    }

    protected abstract String[] getConfigLocations();
}
