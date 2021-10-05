package com.ni.sourceCode.spring;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

//spring的容器类
public class ApplicationContext {
//    配置类
    private Class configClass;

    //单例池
    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public ApplicationContext(Class configClass) throws ClassNotFoundException {

        this.configClass = configClass;
//        解析配置类 .value为获取其的component值
//        ComponentScan注解-->扫描路径-->扫描路径包下所有有spring注释中的类，对其进行解析
//        获取传入类的componentScan注解
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        System.out.println(path);

//        扫描路径 类加载器
//        jre/lib
//        jre/ext/lib
//        classpath 本应用下的根目录 （即target下的classes）
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();

        //获取目录 获取文件
        URL resource = classLoader.getResource("com/ni/sourceCode/example/service");
        File file = new File(resource.getFile());
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File f : files) {
                String fileName = f.getAbsolutePath();
//                需要将路径名转换为类名，所以要截取com-->class之间的路径，并用 “.” 替换，同时"\"转义
                String className = fileName.substring(fileName.indexOf("com"),fileName.indexOf(".class"));
                className = className.replace("\\",".");
                System.out.println(className);

//                加载这个类
                Class<?> aClass = classLoader.loadClass(className);
                if(aClass.isAnnotationPresent(Component.class)) {
//                    判断是否有component注解
//                    判断当前bean是单例bean还是原型（pro）bean
//                      BeanDefinition
                    Component component = aClass.getDeclaredAnnotation(Component.class);
                    String beanName = component.value();

                    BeanDefinition beanDefinition = new BeanDefinition();

                    if(aClass.isAnnotationPresent(Scope.class)) {
                        Scope scope = aClass.getDeclaredAnnotation(Scope.class);
                        beanDefinition.setScope(scope.value());
                    } else {
                        beanDefinition.setScope("singlet");
                    }

                    // beanDefinitionMap 中储存该包下所有beanName的属性（由注释标注）
                    beanDefinitionMap.put(beanName,beanDefinition);

                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {

        }
        return null;
    }
}
