package com.ni.sourceCode.spring.factory;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/6
 * @描述
 */

/**
 * Interface to be implemented by beans that want to release resources
 * on destruction. A BeanFactory is supposed to invoke the destroy
 * method if it disposes a cached singleton. An application context
 * is supposed to dispose all of its singletons on close.
 *
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
