package com.dao;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDao {


    public static String filePathCreate(HttpServletRequest request, String name)
    {
        return request.getSession().getServletContext().getRealPath("upload/");
    }

    public static String fileNameCreate(MultipartFile file, long number, int id)//根据学号和作业号生成文件名
    {
        return number+"_"+id+"_"+file.getOriginalFilename();
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception//上传文件,将文件写入指定路径
    {
        File targetFile = new File(filePath);
        if(!targetFile.exists())      //若该文件不存在，则创建该目录
        {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);     //写入的路径为给出的目录加上文件名
        out.write(file);
        out.flush();
        out.close();
    }

    public static void downloadFile(HttpServletResponse response, String filePath, String fileName)  throws Exception    //从指定路径处下载文件
    {
        File file = new File(filePath+fileName);
        if (file.exists())
        {
            response.setContentType("application/download-fircely");// 强制无法开启           
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            OutputStream outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1)
            {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            if (bufferedInputStream != null)
            {
                bufferedInputStream.close();
            }
            if (fileInputStream!= null)
            {
                fileInputStream.close();
            }
        }
    }
}
