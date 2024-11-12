package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLEncoder;
@Tag(name="文件上传模块",description = "文件上传下载")
@RestController
public class FileUploadAndDownloadController {

    @Resource
    private FileService fileService;

    public static String URL_SERVER = "http://211.159.166.210:8888/";


    @Operation(description = "文件上传，无任何参数",summary = "单纯文件上传")
    @Parameter(name = "file",description = "文件",in = ParameterIn.DEFAULT,required = true,
            schema = @Schema(name = "file",format = "binary"))
    @PostMapping("upload/fileUpload")
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String aa = fileService.uploadFile(file);
        StringBuilder sb = new StringBuilder();
        sb.append("上传成功 文件地址为:").append(URL_SERVER).append(aa);
        return Result.success(sb.toString());
    }

    @Operation(summary = "文件下载", description = "文件下载")
    @GetMapping("upload/downloadFile")
    public void downloadFile(@RequestParam("filePath") String filePath, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("中文名.jpg", "utf-8"));
        byte[] bytes = fileService.downFile(filePath);
        response.getOutputStream().write(bytes);
    }

    @Operation(summary = "文件删除", description = "文件删除")
    @GetMapping("upload/deleteFile")//
    public Result<String> deleteFile(@RequestParam("filePath") String filePath){
       fileService.deleteFile(filePath);
       return Result.success("删除成功");
    }




}