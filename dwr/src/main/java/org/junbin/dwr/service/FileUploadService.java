package org.junbin.dwr.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.io.FileTransfer;

import java.io.File;
import java.io.IOException;

/**
 * @Date : 2016-03-20 13:28
 * @Author : junbin chung
 * @Email : seraphstorm@163.com
 * @Intro : 提供文件批量上传服务
 */
public class FileUploadService {

    /**
     * 功能：根据文件传输器批量上传文件到webapp/upload目录下
     *
     * @param fileTransfers 从页面传递过来的DWR的文件传输器数组
     * @throws IOException
     */
    public void batchUpload(FileTransfer[] fileTransfers) throws IOException {
        WebContext webContext = WebContextFactory.get();
        String realPath = webContext.getServletContext().getRealPath("/upload");
        File dir = new File(realPath);
        if (!dir.exists() || !dir.isDirectory()) {
            assert dir.mkdir();
        }
        for (FileTransfer fileTransfer : fileTransfers) {
            String filename = FilenameUtils.getName(fileTransfer.getFilename());
            if (StringUtils.isEmpty(filename)) {
                continue;
            }
            FileUtils.copyInputStreamToFile(fileTransfer.getInputStream(), new File(realPath, filename));
        }
    }
}
