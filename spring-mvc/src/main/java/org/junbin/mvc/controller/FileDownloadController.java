package org.junbin.mvc.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Date : 2016-03-20 12:52
 * @Author : junbin chung
 * @Email : seraphstorm@163.com
 * @Intro : 文件上传控制器
 */
@Controller(value = "fileDownloadController")
public class FileDownloadController {

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public String enter(HttpServletRequest request, Model model) {
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        String[] files = new File(realPath).list();
        model.addAttribute("files", files);
        return "mvcDownload";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam(value = "filename") String filename) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(realPath, filename);
        if (!file.exists()) {
            throw new FileNotFoundException("文件名称：" + filename
                    + "\n对不起，资源已被删除或者不再提供该资源的下载服务！");
        }
        // 第一步：创建HttpHeaders
        HttpHeaders httpHeaders = new HttpHeaders();
        // 第二步：设置HttpHeaders的数据类型为任意二进制附件
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 第三步：设置attachment附件的名称【貌似不能直接使用传递过来的filename参数】
        httpHeaders.setContentDispositionFormData("attachment",
                new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
        // 第四步：创建响应主题
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);
    }

}
