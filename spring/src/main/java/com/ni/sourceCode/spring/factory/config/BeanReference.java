package com.ni.sourceCode.spring.factory.config;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/19
 * @描述 对象的参数为其他Bean的引用
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
