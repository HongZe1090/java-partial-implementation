package com.ni.sourceCode.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/21
 * @描述 resource 接口 读取资源的三种不同方法
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
