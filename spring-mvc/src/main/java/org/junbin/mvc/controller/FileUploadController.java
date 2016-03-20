package org.junbin.mvc.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Date : 2016-03-20 10:21
 * @Author : junbin chung
 * @Email : seraphstorm@163.com
 * @Intro : 文件上传控制器
 */
@Controller(value = "fileUploadController")
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "mvcUpload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File dir = new File(realPath);
        if (!dir.exists()) {
            assert dir.mkdir();
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> batch = multipartRequest.getFiles("batch");
        try {
            for (MultipartFile file : batch) {
                if (StringUtils.isEmpty(file.getOriginalFilename())) {
                    continue;
                }
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "mvcUpload";
    }

}
