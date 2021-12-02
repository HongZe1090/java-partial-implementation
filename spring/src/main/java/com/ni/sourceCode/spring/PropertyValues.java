package com.ni.sourceCode.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/19
 * @描述 属性的集合
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**

     *@描述 获得所有的属性值

     */
    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    /**

     *@描述 根据属性名获得对应的属性值

     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            } }
        return null; }
}
