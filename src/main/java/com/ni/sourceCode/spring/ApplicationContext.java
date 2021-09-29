package com.ni.sourceCode.spring;

//spring的容器类
public class ApplicationContext {
//    配置类
    private Class configClass;

    public ApplicationContext(Class configClass) {

        this.configClass = configClass;
//        解析配置类
//        ComponentScan注解-->扫描路径-->扫描路径包下所有有spring注释中的类，对其进行解析
//        获取传入类的componentScan注解
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        System.out.println(path);

//        扫描路径

    }

    public Object getBean(String beanName) {

        return null;
    }
}
