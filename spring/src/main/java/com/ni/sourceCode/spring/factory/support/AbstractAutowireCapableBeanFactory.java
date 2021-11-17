package com.ni.sourceCode.spring.factory.support;

import com.ni.sourceCode.spring.BeansException;
import com.ni.sourceCode.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/14
 * @描述 实现了bean的实例化操作，使用 Cglib 实例化
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    protected Object creatBean(String beanName, BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException {
        Object bean = null;

        bean = beanDefinition.getClazz().newInstance();

        addSingleton(beanName,bean);

        return bean;
    }

    private InstantiantionStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

//    有参的createBean 使用 Cglib 实例化
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getClazz();
//        获取beanDefinition的构造函数
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
//        选择合适的构造函数，源码中还会对比相同的参数类型
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiantionStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiantionStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
