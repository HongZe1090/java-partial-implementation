package com.ni.sourceCode.spring;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/17
 * @描述
 */
public class BeansException extends RuntimeException {

    /**
     * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
     * 公众号：bugstack虫洞栈
     * Create by 小傅哥(fustack)
     *
     * 定义 Bean 异常
     */
        public BeansException(String msg) {
            super(msg);
        }

        public BeansException(String msg, Throwable cause) {
            super(msg, cause);
        }

}
