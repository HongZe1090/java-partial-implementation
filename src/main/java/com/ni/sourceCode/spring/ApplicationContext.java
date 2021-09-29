package com.ni.sourceCode.spring;

//spring的容器类
public class ApplicationContext {
//    配置类
    private Class configClass;

    public ApplicationContext(Class configClass) {

        this.configClass = configClass;
//        解析配置类
//        ComponentScan注解-->扫描路径-->扫描
    }

    public Object getBean(String beanName) {

        return null;
    }
}
