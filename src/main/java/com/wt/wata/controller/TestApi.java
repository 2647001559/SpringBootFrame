package com.wt.wata.controller;

import com.wt.wata.common.upload.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 添柴灬少年
 * @date 2019/6/10 - 18:05
 */
@Controller
@RequestMapping("test")
public class TestApi {
    @Autowired
    FileUploadUtil fileUploadUtil;

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        try{
//            return fileUploadUtil.uploadImage(file);
//            return fileUploadUtil.uploadVideo(file);
            return fileUploadUtil.uploadWord(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
