package com.ni.sourceCode.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/21
 * @描述 从本地文件(指定文件路径)中获取资源
 */
import java.io.File;
import java.io.FileInputStream;

public class FileSystemResource implements Resource {

    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public final String getPath() {
        return this.path;
    }

}