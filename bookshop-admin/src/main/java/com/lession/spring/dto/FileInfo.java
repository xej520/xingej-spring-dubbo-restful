package com.lession.spring.dto;

/**
 * 测试文件上传下载
 * 
 * @author erjun 2017年11月22日 上午6:34:44
 */
public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
