package com.ni.sourceCode.context;

import com.ni.sourceCode.spring.BeansException;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
