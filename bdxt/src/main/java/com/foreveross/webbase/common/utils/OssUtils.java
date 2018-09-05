package com.foreveross.webbase.common.utils;


import com.aliyun.oss.OSSClient;
import com.foreveross.webbase.common.config.Global;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by tanjinhua on 2018-2-3.
 */
public class OssUtils {

    public static final String ENDPOINT = "http://"+Global.getConfig("oss.endpoint");
    public static final String ACCESSKEYID = Global.getConfig("oss.accessKeyId");
    public static final String ACCESSKEYSECRET = Global.getConfig("oss.accessKeySecret");
    public static final String BUCKETNAME = Global.getConfig("oss.bucketName");
    public static final String ACCESSURL = "http://"+BUCKETNAME+"."+Global.getConfig("oss.endpoint");

    /**
     * oss上传文件(如果上传的是同一个文件名，则会替换掉源文件)
     * @param stream
     * @return
     * @throws Exception
     */
    public static String uploadFile(InputStream stream,String fileName) throws Exception{
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        // 上传文件流
        ossClient.putObject(BUCKETNAME, fileName, stream);
        // 关闭client
        ossClient.shutdown();

        return ACCESSURL+"/" + fileName;
    }

    public static boolean deleteFile(String filePath) throws Exception{

        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        ossClient.deleteObject(BUCKETNAME, filePath);

        return true;
    }

    public static void main(String[] args) throws Exception {

        File file = new File("D:\\testfile\\test2.jpg");
        InputStream stream=null;
        stream=new FileInputStream(file);

        System.out.println(file.getName());
        System.out.print(uploadFile(stream,file.getName()));
    }

}
