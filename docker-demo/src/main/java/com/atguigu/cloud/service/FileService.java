package com.atguigu.cloud.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    /**
     * @Description: 文件上传  返回上传完成以后的文件路径
     * @Author: Tan
     * @Date: 2020/3/31
     * @param file: 上传文件对象
     * @return: java.lang.String
     **/
    String uploadFile(MultipartFile file) throws IOException;
    /**
     * @Description: 上传图片并且创建缩略图
     * @Author: Tan
     * @Date: 2020/3/31
     * @param file: 图片文件对象
     * @return: java.lang.String
     **/
    String uploadImageAndCreateThumbImage(MultipartFile file) throws IOException;

    /**
     * @Description: 根据文件名获取缩略图路径
     * @Author: Tan
     * @Date: 2020/3/31
     * @param filePath: 缩略图路径
     * @return: java.lang.String
     **/
    String getThumbImagePath(String filePath) ;


    /**
     * @Description:  根据文件路径进行下载 返回一个byte[]数组 包含文件内容
     * @Author: Tan
     * @Date: 2020/3/31
     * @param filePath: 下载文件路径
     * @return: byte[]
     **/
    byte[] downFile(String filePath);



    /**
     * @Description: 根据文件路径 删除文件
     * @Author: Tan
     * @Date: 2020/3/31
     * @param filePath: 文件路径
     * @return: void
     **/
    void deleteFile(String filePath);


}
