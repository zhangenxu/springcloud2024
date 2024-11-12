package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.service.FileService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import jakarta.annotation.Resource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * &#064;Description:  文件上传下载
 * &#064;Author:  Tan
 * &#064;CreateDate:  2020/3/31
 **/
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FastFileStorageClient storageClient;

    @Resource
    private ThumbImageConfig thumbImageConfig;


    /**
     * {@code @Description:} 文件上传  返回上传完成以后的文件路径
     * @Author: Tan
     * @Date: 2020/3/31
     * @param file: 上传文件对象
     * @return: java.lang.String
     **/
    public String uploadFile(MultipartFile file) throws IOException {
        if(Objects.isNull(file)){
            return "";
        }
        //参1 文件输入流,参2 文件大小,参3 文件拓展名,参4 元数据集合
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    /**
     * @Description: 上传图片并且创建缩略图
     * @Author: Tan
     * @Date: 2020/3/31
     * @param file: 图片文件对象
     * @return: java.lang.String
     **/
    public  String uploadImageAndCreateThumbImage(MultipartFile file) throws IOException{
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }


    /**
     * @Description: 根据文件名获取缩略图路径
     * @Author: Tan
     * @Date: 2020/3/31
     * @param filePath: 缩略图路径
     * @return: java.lang.String
     **/
    public String getThumbImagePath(String filePath) {
        return thumbImageConfig.getThumbImagePath(filePath);
    }


    /**
     * @Description:  根据文件路径进行下载 返回一个byte[]数组 包含文件内容
     * @Author: Tan
     * @Date: 2020/3/31
     * @param filePath: 下载文件路径
     * @return: byte[]
     **/
    public byte[] downFile(String filePath) {
        StorePath storePath = StorePath.parseFromUrl(filePath);
        return storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadCallback<byte[]>() {
            @Override
            public byte[] recv(InputStream ins) throws IOException {
                return org.apache.commons.io.IOUtils.toByteArray(ins);
            }
        });
    }



    /**
     * @Description: 根据文件路径 删除文件
     * @Author: Tan
     * @Date: 2020/3/31
     * @param filePath: 文件路径
     * @return: void
     **/
    public void deleteFile(String filePath) {
        StorePath storePath = StorePath.parseFromUrl(filePath);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());

    }



}