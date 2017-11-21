package com.lession.spring.web.controller;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
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

}
