package com.ni.sourceCode.spring;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/19
 * @描述 定义属性
 */
public class PropertyValue {
    public final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
