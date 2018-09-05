package com.foreveross.webbase.weixin.sdk.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.utils.FileUtils;
import com.foreveross.webbase.common.utils.IdGen;
import com.foreveross.webbase.system.file.entity.SysFile;

public class UploadUtils {
	
	private static Logger logger =  LoggerFactory.getLogger(UploadUtils.class);
	/**
	 * @param file 需要上传的文件
	 * @param savePath 保存的相对路径
	 * @param request 
	 * @param arrange 是否按日期分目录
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> uploadFile(MultipartFile file,String savePath,HttpServletRequest request,boolean arrange) throws Exception{
		//获取项目真实路径
		String path = request.getSession().getServletContext()
				.getRealPath("/");		
		String localPath;
		String localFilePath;
		if(arrange){
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/");				
		     localPath = savePath+dateformat.format(new Date());
		     localFilePath = path+localPath;
		}else{
			localPath = savePath;
			localFilePath = path+localPath;
		}            
		File dirctory= new File(localFilePath);
      if(!dirctory.exists()){
    	  logger.info("文件夹不存在");
    	  dirctory.mkdirs();
      }  
      String fileName = file.getOriginalFilename();
      String ext = fileName.substring(fileName.lastIndexOf("."));
      File localFile = new File(localFilePath+"/"+IdGen.uuid()+ext);
      file.transferTo(localFile);
      String imageUrl = buildBasePath(request)+localPath+localFile.getName();
      Map<String,Object> result = new HashMap<String,Object>();
      result.put("localFile",localFile);
      result.put("imageUrl", imageUrl);
      result.put("filePath", localPath+localFile.getName());
      return result;		
}
	public static String buildBasePath(HttpServletRequest request){
		        //获取项目名
				String projectPath = request.getContextPath();				
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + projectPath;
				return basePath;
	}
	
	public static void deleteUploadFile(String filename,HttpServletRequest request){
		if(StringUtils.isEmpty(filename)){
			 logger.info("文件路径为空");
			 return;
		}
		//获取项目真实路径
		String path = request.getSession().getServletContext()
				.getRealPath("/");	
		 if(FileUtils.deleteFile(path+filename)){
			 logger.info("上传文件删除成功");
		 };
	}
	
	public static SysFile SystemUpload(MultipartFile file,String dataid) throws IllegalStateException, IOException{
		SysFile sysFile = new SysFile();
		sysFile.setName(file.getOriginalFilename());
		
		sysFile.setExt(FileUtils.getFileExtension(file.getOriginalFilename()));
		sysFile.setSize(file.getSize()+"");
		sysFile.setSizestr(getSizeStr(file.getSize()));
		
		sysFile.setPath(UUID.randomUUID().toString());
		String uploaddir=Global.getConfig("file.uploaddir");
		String filepath=uploaddir+sysFile.getPath();
		File f=new File(filepath);
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			file.transferTo(f);
			sysFile.setDataid(dataid);
			return sysFile;
	}
	
	public static SysFile SystemUpload(String dataid) throws IllegalStateException, IOException{
		SysFile sysFile = new SysFile();
//		sysFile.setName(file.getOriginalFilename());
//		
//		sysFile.setExt(FileUtils.getFileExtension(file.getOriginalFilename()));
//		sysFile.setSize(file.getSize()+"");
//		sysFile.setSizestr(getSizeStr(file.getSize()));
		
		sysFile.setPath(UUID.randomUUID().toString());
		String uploaddir=Global.getConfig("file.uploaddir");
		String filepath=uploaddir+sysFile.getPath();
		File f=new File(filepath);
		if(!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		sysFile.setDataid(dataid);
		sysFile.setName(dataid);
  		sysFile.setExt("png");
 	    sysFile.setModule("order");
 	    sysFile.setSubmodule("order");
		return sysFile;
	}
	
/*	public static void SystemUploadDelete(String id){
		SysFile sysFile=sysFileService.get(id);
		String uploaddir=Global.getConfig("file.uploaddir");
		String filepath=uploaddir+sysFile.getPath();
		File f=new File(filepath);
		f.delete();
		sysFileService.delete(id);
	}*/
	
	private static String getSizeStr(long size) {
		long num=1024*1024;	
		if(size<1024) {
			return size+"B";
		} else if(size<num) {
			return size/1024+"KB";
		} else if(size<1024*num) {
			return size/num+"MB";
		} else if(size<num*num) {
			return size/(1024*num)+"GB";
		}
		return null;
	}
}
