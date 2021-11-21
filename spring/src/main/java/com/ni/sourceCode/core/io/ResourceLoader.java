package com.ni.sourceCode.core.io;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/21
 * @描述 定义资源获取接口 资源加载器可以把上述不同的加载方式集中到统一的类服务下进行处理
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
