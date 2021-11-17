package com.ni.sourceCode.Application;

public interface InitializingBean {

//    dubbo等和spring整合，常用来验证某些属性是否存在
    void afterPropertiesSet() throws Exception;
}
