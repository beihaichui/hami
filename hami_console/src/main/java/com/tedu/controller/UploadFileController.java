package com.tedu.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.tedu.util.PropUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * 都在这个一个文件上传的类里执行
 */
@Controller
@RequestMapping("upload")
public class UploadFileController {


    /**
     * 实现图片文件上传的方法
     * @param request   请求对象
     * @param response  响应对象
     * @param fileType   文件的类型  是图片还是mp3
     */
    @RequestMapping("uploadFile")
    public void uploadFile(HttpServletRequest request,HttpServletResponse response,String lastImg,String fileType) throws IOException {
        //获取到前端传过来的所有有关文件的参数
        MultipartHttpServletRequest mr= (MultipartHttpServletRequest) request;
        //获得存储文件的map
        Map<String, MultipartFile> fileMap = mr.getFileMap();
        //获取文件
        MultipartFile pic = fileMap.get("picFile");
        //获得pic图片文件的字节数组  方便上传
        byte[] bytes = pic.getBytes();

        //获取文件的名字
        String originalFilename = pic.getOriginalFilename();
        //获取文件名的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //定义一个新的名字给文件
        String fileName = UUID.randomUUID().toString();
        fileName=fileName+suffix;
        //获取文件服务器的路径
        String filePath = PropUtil.read("filePath");
        //文件上传的路径  组装  http://localhost:8088/pic/01.jpd
        String realPath=filePath+"/"+fileType+"/"+fileName;
        //组装图片的相对路径   往数据库存储的
        String relativePath="/"+fileType+"/"+fileName;
        //创建上传的jersy客户端对象
        Client client = Client.create();
        if(lastImg != null && !"".equals(lastImg)){
            WebResource resource1 = client.resource(lastImg);
            resource1.delete();
        }
        //获取web资源
        WebResource resource = client.resource(realPath);
        //上传
        resource.put(bytes);
        //创建前端需要的json对象
        JSONObject jo=new JSONObject();
        jo.put("realPath", realPath);
        jo.put("relativePath", relativePath);
        //没有返回值使用response对象进行数据返回
        response.getWriter().write(jo.toString());
    }


    @RequestMapping("uploadFileMp3")
    public void uploadFileMp3(MultipartFile pic ,HttpServletRequest request,HttpServletResponse response,String lastMp3,String fileType) throws IOException {
        //获取到前端传过来的所有有关文件的参数
//        MultipartHttpServletRequest mr= (MultipartHttpServletRequest) request;
//        //获得存储文件的map
//        Map<String, MultipartFile> fileMap = mr.getFileMap();
//        //获取文件
//        MultipartFile pic = fileMap.get("mp3file");
        //获得pic图片文件的字节数组  方便上传
        byte[] bytes = pic.getBytes();

        //获取文件的名字
        String originalFilename = pic.getOriginalFilename();
        //获取文件名的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //定义一个新的名字给文件
        String fileName = UUID.randomUUID().toString();
        fileName=fileName+suffix;
        //获取文件服务器的路径
        String filePath = PropUtil.read("filePath");
        //文件上传的路径  组装  http://localhost:8088/pic/01.jpd
        String realPath=filePath+"/"+fileType+"/"+fileName;
        //组装图片的相对路径   往数据库存储的
        String relativePath="/"+fileType+"/"+fileName;
        //创建上传的jersy客户端对象
        Client client = Client.create();
        if(lastMp3 != null && !"".equals(lastMp3)){
            WebResource resource1 = client.resource(lastMp3);
            resource1.delete();
        }
        //获取web资源
        WebResource resource = client.resource(realPath);
        //上传
        resource.put(bytes);
        //创建前端需要的json对象
        JSONObject jo=new JSONObject();
        jo.put("realPath", realPath);
        jo.put("relativePath", relativePath);
        //没有返回值使用response对象进行数据返回
        response.getWriter().write(jo.toString());
    }

}
