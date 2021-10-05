package com.ni.sourceCode.spring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//spring的容器类
public class ApplicationContext {
//    配置类
    private Class configClass;

    //单例池 这里的Object是已经实例化后的Object
    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public ApplicationContext(Class configClass) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        this.configClass = configClass;
        //        解析配置类 .value为获取其的component值
        //        ComponentScan注解-->扫描路径-->获取路径并使用io类加载该包下的文件路径
        Scan(configClass);

//        在容器启动时即为所有的单例类创建Bean（实例）遍历Map获取每个entry[映射]
        for (Map.Entry<String,BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            if (entry.getValue().getScope().equals("singleton")) {
                Object bean = creatBean(entry.getValue());
                singletonObjects.put(beanName,bean);
            }
        }
    }

    public Object creatBean(BeanDefinition beanDefinition) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class clazz = beanDefinition.getClazz();
        Object instance = clazz.getDeclaredConstructor().newInstance();

        return instance;
    }

    private void Scan(Class configClass) throws ClassNotFoundException {
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
                    beanDefinition.setClazz(aClass);

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

    public Object getBean(String beanName) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (beanDefinitionMap.containsKey(beanName)) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals("singleton")) {
                Object o = singletonObjects.get(beanName);
                return o;
            } else {
//            创建Bean对象
                Object bean = creatBean(beanDefinition);
                return bean;
            }
        } else {
//            不存在这个bean
            throw new NullPointerException();
        }
    }
}
