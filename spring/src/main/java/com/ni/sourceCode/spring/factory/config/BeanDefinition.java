package com.ni.sourceCode.spring.factory.config;

//bean注册
public class BeanDefinition {
//    bean的类型 传入类型为class，实例化放在容器中
    private Class clazz;
//    加载模式
    private String scope;

    public BeanDefinition() {
    }

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        this.scope = scope;
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
}
