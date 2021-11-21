package com.ni.sourceCode.spring.factory.config;

import com.ni.sourceCode.spring.PropertyValues;

//bean注册 bean的相关属性储存
public class BeanDefinition {
//    bean的类型 传入类型为class，实例化放在容器中
    private Class clazz;
//    加载模式
    private String scope;

    private PropertyValues propertyValues;

    public BeanDefinition() {
    }

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        this.propertyValues = new PropertyValues();
        this.scope = scope;
    }

//    直接使用二元式定义，避免后面for循环的时候还要判断属性填充是否为空
    public BeanDefinition(Class beanClass, String scope,PropertyValues propertyValues) {
        this.clazz = clazz;
        this.propertyValues = propertyValues !=null?propertyValues:new PropertyValues();
        this.scope = scope;
    }

    public BeanDefinition(Class beanClass) {
        this.clazz = clazz;
        this.propertyValues = this.propertyValues = new PropertyValues();
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
