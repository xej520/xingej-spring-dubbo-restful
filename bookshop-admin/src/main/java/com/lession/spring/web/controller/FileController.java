package com.lession.spring.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lession.spring.dto.FileInfo;

/**
 * 文件上传Controller
 * 
 * @author erjun 2017年11月22日 上午6:06:59
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    // 参数的名称为file，必须与测试用例的第一个参数一致才行的
    public FileInfo update(MultipartFile file) throws Exception {

        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        // 将上传上来的文件，存储到本地路径下
        String path = "E:/Project/xingej/xingej-spring-dubbo-restful/bookshop-admin/fileUpLoad";
        // 截取文件类型
        String extention = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        // path 是父路径
        // 第2个参数是，以时间戳作为文件名称
        File localFile = new File(path, new Date().getTime() + "." + extention);

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("download")
    public void downLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = "E:/Project/xingej/xingej-spring-dubbo-restful/bookshop-admin/fileUpLoad/1511303606485.txt";

        // JDK1.8特性，这样的话，就不需要在finally里，关闭inputStream, outputStream流了
        try (InputStream inputStream = new FileInputStream(filePath);
                OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");

            // test.txt 下载到 本地 的名称
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();
        }
    }

}
