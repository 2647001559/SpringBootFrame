package com.wt.wata.common.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * 文件上传工具
 * @author 添柴灬少年
 * @date 2019/6/10 - 15:56
 */
@Component
@Scope("singleton")
public class FileUploadUtil {

//    private static volatile  FileUploadUtil fileUploadUtil = null;   //实例对象
//
//    private FileUploadUtil(){}                                      //构造器私有化
//
//    /**
//     * 获取单例对象
//     * @return
//     */
//    public static FileUploadUtil getFileUploadUtil(){
//        if (fileUploadUtil == null){
//            synchronized (FileUploadUtil.class){
//                if (fileUploadUtil == null){
//                    fileUploadUtil = new FileUploadUtil();
//                }
//            }
//        }
//        return fileUploadUtil;
//    }

    @Value("${images.file-format}")
    private String IMAGE_TYPE;
    @Value("${video.file-format}")
    private String VIDEO_TYPE;
    @Value("${word.file-format}")
    private String WORD_TYPE;
    @Value("${table.file-format}")
    private String TABLE_TYPE;
    @Value("${file.static-access-path}")
    private String LOCATION;
    @Value("${file.upload-folder}")
    private String PATH;

    /**
     * 上传图片
     * @param multipartFile 文件
     * @return
     * @throws Exception
     */
    public String uploadImage(MultipartFile multipartFile) throws Exception{
        try{
            return uploadImage(multipartFile,"");
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 上传图片
     * @param multipartFile 文件
     * @param name          文件名
     * @return
     * @throws Exception
     */
    public String uploadImage(MultipartFile multipartFile,String name) throws Exception{
            try{
                if (name == null || name.length() == 0){
                    name = new SimpleDateFormat("HHMMss").format(System.currentTimeMillis()) + "-" + multipartFile.getOriginalFilename();
                }
                String format = name.substring(name.lastIndexOf(".") + 1,name.length());
                if (!formatVerification(format,1)){
                    return "";
                }
                return uploadFile(multipartFile,name);
            }catch (Exception e){
                throw e;
            }
    }


    /**
     * 上传视频
     * @param multipartFile     文件
     * @return
     * @throws Exception
     */
    public String uploadVideo(MultipartFile multipartFile) throws Exception{
        try{
            return uploadVideo(multipartFile,"");
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 上传视频
     * @param multipartFile 文件
     * @param name          文件名
     * @return
     * @throws Exception
     */
    public String uploadVideo(MultipartFile multipartFile,String name) throws Exception{
        try{
            if (name == null || name.length() == 0){
                name = new SimpleDateFormat("HHMMss").format(System.currentTimeMillis()) + "-" + multipartFile.getOriginalFilename();
            }
            String format = name.substring(name.lastIndexOf(".") + 1,name.length());
            if (!formatVerification(format,2)){
                return "";
            }
            return uploadFile(multipartFile,name);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 上传word文档
     * @param multipartFile     文件
     * @return
     * @throws Exception
     */
    public String uploadWord(MultipartFile multipartFile) throws Exception{
        try{
            return uploadWord(multipartFile,"");
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 上传word文档
     * @param multipartFile 文件
     * @param name          文件名
     * @return
     * @throws Exception
     */
    public String uploadWord(MultipartFile multipartFile,String name) throws Exception{
        try{
            if (name == null || name.length() == 0){
                name = new SimpleDateFormat("HHMMss").format(System.currentTimeMillis()) + "-" + multipartFile.getOriginalFilename();
            }
            String format = name.substring(name.lastIndexOf(".") + 1,name.length());
            if (!formatVerification(format,3)){
                return "";
            }
            return uploadFile(multipartFile,name);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 上传xsl表格
     * @param multipartFile     文件
     * @return
     * @throws Exception
     */
    public String uploadTable(MultipartFile multipartFile) throws Exception{
        try{
            return uploadTable(multipartFile,"");
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 上传xsl表格
     * @param multipartFile 文件
     * @param name          文件名
     * @return
     * @throws Exception
     */
    public String uploadTable(MultipartFile multipartFile,String name) throws Exception{
        try{
            if (name == null || name.length() == 0){
                name = new SimpleDateFormat("HHMMss").format(System.currentTimeMillis()) + "-" + multipartFile.getOriginalFilename();
            }
            String format = name.substring(name.lastIndexOf(".") + 1,name.length());
            if (!formatVerification(format,4)){
                return "";
            }
            return uploadFile(multipartFile,name);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 上传文件
     * @param multipartFile 文件
     * @param name          文件名
     * @return
     * @throws Exception
     */
    public String uploadFile(MultipartFile multipartFile,String name) throws Exception{
        try{
            if (name == null || name.length() == 0){
                name = new SimpleDateFormat("HHMMss").format(System.currentTimeMillis()) + "-" + multipartFile.getOriginalFilename();
            }
            String nowStr = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File file = new File(PATH + nowStr,name);
            if (!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            multipartFile.transferTo(file);
            return LOCATION.substring(0,LOCATION.indexOf("*")-1) + "/" + nowStr + "/" + name;
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 格式验证
     * @param format
     * @param type
     * @return
     */
    private boolean formatVerification(String format,int type) throws Exception{
        boolean flag = false;
        try{
            format = format.toUpperCase();
            switch (type){
                case 1:
                    flag = Arrays.asList(IMAGE_TYPE.split("-")).contains(format);
                    break;
                case 2:
                    flag = Arrays.asList(VIDEO_TYPE.split("-")).contains(format);
                    break;
                case 3:
                    flag = Arrays.asList(WORD_TYPE.split("-")).contains(format);
                    break;
                case 4:
                    flag = Arrays.asList(TABLE_TYPE.split("-")).contains(format);
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            throw e;
        }
        return flag;
    }
}
