package com.ni.FirstSpring.Application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//        source：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；被编译器忽略
//        class：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
//        runtime：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
//        这3个生命周期分别对应于：Java源文件(.java文件) ---> .class文件 ---> 内存中的字节码。
//          ComponentScan注解规定了要扫描的包
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentScan {
    String value() default "";
}
