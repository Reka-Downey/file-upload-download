package org.junbin.dwr.service;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.io.FileTransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Date : 2016-03-20 13:28
 * @Author : junbin chung
 * @Email : seraphstorm@163.com
 * @Intro : 提供文件下载服务
 */
public class FileDownloadService {

    /**
     * 功能：获取webapp/upload目录下的文件名称列表
     *
     * @return 文件名称列表
     */
    public String[] listFiles() {
        WebContext webContext = WebContextFactory.get();
        String realPath = webContext.getServletContext().getRealPath("/upload");
        File dir = new File(realPath);
        if (!dir.exists()) {
            assert dir.mkdir();
        }
        return dir.list();
    }

    /**
     * 功能：根据指定名称获取文件并提供下载
     *
     * @param filename 文件名称
     * @return DWR文件传输器
     * @throws IOException
     */
    public FileTransfer downloadFile(String filename) throws IOException {
        WebContext webContext = WebContextFactory.get();
        String realPath = webContext.getServletContext().getRealPath("/upload");
        File file = new File(realPath, filename);
        if (file.exists() && file.isFile()) {
            Tika tika = new Tika(TikaConfig.getDefaultConfig());
            return new FileTransfer(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"),
                    tika.detect(file), file.length(), new FileInputStream(file));
        }
        throw new FileNotFoundException("文件名称：" + filename
                + "\n对不起，资源已被删除或者不再提供该资源的下载服务！");
    }

}
