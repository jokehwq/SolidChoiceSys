package com.foreveross.webbase.common;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OssUtil {

	static String endpoint = "oss-cn-beijing.aliyuncs.com";
	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
	static String accessKeyId = "LTAIMteieEw5jar4";
	static String accessKeySecret = "JAbMF8fTnaGEKnCi8yXayndVg9ZqBj";
	static String bucketName = "lmqhhh";

	public static String uploadHeadImg(String url) throws Exception {
  		String imageName = "";
		if (url.indexOf("jpeg") != -1){
			 imageName = imageName() + ".jpg";    //图片名称
		}
		if (url.indexOf("mp4") != -1){
			 imageName = imageName() + ".mp4";    //图片名称
		}
		String path=new File("").getAbsolutePath()+"/";//这种方式可以拿到tomcat下的bin目录
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret); //初始化上传客户端
		download(url, imageName, path);   //下载图片到本地（见下面）
		File file = new File(path + imageName);   //根据下载的文件创建新文件
		//文件不存在，则上传默认头像
		if (!file.exists()) {
			return "1";
		}
		InputStream content = new FileInputStream(file);
		ObjectMetadata meta = new ObjectMetadata(); // 创建上传Object的Metadata
		meta.setContentLength(file.length());   // 必须设置ContentLength

		String keySuffixWithSlash = folderNameYM() + "/" + folderNameD() + "/"; // 上传Object.（这里的方法是生成根据时间生成文件夹名称的）
		PutObjectResult putResult = client.putObject(bucketName, keySuffixWithSlash + imageName, content, meta);  //图片上传到oss

		String ossPath = imageName;   //图片上传路径
		if (file.exists()) {//上传完成后，删除文件
				file.delete();
		}
		content.close();//关闭所有链接
				client.shutdown();
		return ossPath;
	}


	/**
	 * 下载图片到本地
	 *
	 * @param urlString
	 * @param filename
	 * @param savePath
	 * @throws Exception
	 */
	public static void download(String urlString, String filename, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		//设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

	/**
	 * 图片名称生成
	 *
	 * @return
	 */
	public static String imageName() {
		Random random = new Random();//生成随机数
		String strDate = Long.toString(System.currentTimeMillis());
		for (int i = 0; i < 3; i++) {
			strDate = strDate + random.nextInt(9);
		}
		return strDate;
	}

	/**
	 * 文件夹名称-年月
	 *
	 * @return
	 */
	public static String folderNameYM() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		String strDate = formatter.format(new Date());
		return strDate;
	}

	/**
	 * 文件夹名称-天
	 *
	 * @return
	 */
	public static String folderNameD() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		String strDate = formatter.format(new Date());
		return strDate;
	}

	public static void main(String[] args) throws Exception {
		File file=new File("C:\\Users\\HIAPAD\\Desktop\\scss.txt");
		BufferedReader reader=null;
		String temp=null;
		int line=1;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				System.out.println("line"+line+":"+temp);
				line++;
				JSONObject jsStr = JSONObject.fromObject(temp);
				JSONArray jsonArray = (JSONArray) jsStr.get("data");
				String osspath =  uploadHeadImg("http://creative-static-ag.ymcdn.cn/jpeg/2e/b5/6e/2eb56e5914821999975f97b347ead8a9.jpeg");
				for(int i=0;i<jsonArray.size();i++){
					JSONArray materialList = (JSONArray)((JSONObject)(jsonArray.get(i))).get("materialList");
					if(materialList.size()<1){
						continue;
					}
					String url = (String) ((JSONObject)materialList.get(0)).get("url");
					String duration = String.valueOf((Integer) ((JSONObject) (jsonArray.get(i))).get("duration"));
					String totalCount = String.valueOf((Integer)((JSONObject)(jsonArray.get(i))).get("totalCount"));
					String slogan = (String)((JSONObject)(jsonArray.get(i))).get("slogan");
					String channel = (String)((JSONObject)((JSONObject)(jsonArray.get(i))).get("channel")).get("name");
					JSONObject appinfo = (JSONObject)((JSONObject)(jsonArray.get(i))).get("appInfo");
					String platform = (String) appinfo.get("platform");
					String developer = (String) appinfo.get("developer");
					String appname = (String) appinfo.get("name");
					String icon = (String) appinfo.get("icon");

				}
				System.out.println("1");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(reader!=null){
				try{
					reader.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		OssUtil.getUrl("1");
	}

	public static String getUrl(String key) {
		// 设置URL过期时间为10年 3600l* 1000*24*365*10
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret); //初始化上传客户端
		Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = client.generatePresignedUrl(bucketName, "1531729019357567.jpeg", expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}

}
