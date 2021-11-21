package com.ni.sourceCode.util;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/21
 * @描述 获得当前类的类加载器(jvm中类的唯一标识)以获取输入流
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
