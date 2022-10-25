package com.aoming.fkh.optimize.picture;
 

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.*;
import java.util.UUID;
 
/**
 * @author Gavin.luo
 * @title: ImageController
 * @projectName MyData
 * @description:
 * @date 2021/7/21 10:09
 */
@RestController
@RequestMapping("/image")
public class ImageController {
 
 
    @RequestMapping(value = "file/img",method = RequestMethod.POST)
    public String imgUploads(@RequestParam("file") MultipartFile file) throws IOException {
        //压缩图片到指定120K以内,不管你的图片有多少兆,都不会超过120kb,精度还算可以,不会模糊
        byte[] bytes = PicUtils.compressPicForScale(file.getBytes(), 120);
        //生成保存在服务器的图片名称，统一修改原后缀名为:jpg
        String newFileName = UUID.randomUUID() + ".jpg";
        File fOut = new File("C:/Users/admin/Pictures/copy" + newFileName);//C:/Users/admin/Pictures/copy
        FileOutputStream fileOutputStream = new FileOutputStream(fOut);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        return newFileName;
    }
 
    @RequestMapping(value = "file/img2",method = RequestMethod.POST)
    public String imgUploads2(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bs = ImgCompression.getImageCom(file);
        String newFileName = UUID.randomUUID() + ".jpg";
        File fOut = new File("C:/Users/admin/Pictures/copy" + newFileName);
        FileOutputStream fileOutputStream = new FileOutputStream(fOut);
        fileOutputStream.write(bs);
        fileOutputStream.close();
        return newFileName;
    }
 
}