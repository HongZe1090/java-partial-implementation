package com.ni.sourceCode.framwork.protocal;

import java.io.Serializable;

//序列化，方便储存与网络传输
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramType;
    private Object[] params;

    public Invocation(String interfaceName, String methodName, Class[] paramType, Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramType = paramType;
        this.params = params;
    }

    public Invocation() {
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamType() {
        return paramType;
    }

    public void setParamType(Class[] paramType) {
        this.paramType = paramType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
