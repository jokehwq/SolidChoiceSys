package com.foreveross.webbase.test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringEscapeUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		
		/*Configuration config=new Configuration();
		config.setDirectoryForTemplateLoading(new File("I:/htky/hlk/java/hlkapp/src/test/java/com/foreveross/webbase/test/"));
		config.setDefaultEncoding("UTF-8");
		
		//创建数据模型
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("score",  new Random().nextInt(100));
		m.put("name", "张三");
		m.put("time", new Date());
		m.put("str", "北京,上海,杭州");
		Map<String,Object> context=new HashMap<String,Object>();
		context.put("score",  new Random().nextInt(100));
		context.put("time", new Date());
		context.put("str", "北京,上海,杭州");
		
		//加载模板文件
		Template template=config.getTemplate("test.ftl");
		//显示生成后的数据
		Writer writer=new OutputStreamWriter(System.out);
		template.process(context, writer);
		writer.close();*//*
		String str = "&lt;p&gt;这是一个资讯&lt;img src=&quot;/ueditor/jsp/upload/image/20180207/1518014302230092248.jpg&quot; title=&quot;1518014302230092248.jpg&quot; alt=&quot;Camera_20120616_144629.jpg&quot; width=&quot;636&quot; height=&quot;325&quot; style=&quot;width: 636px; height: 325px;&quot;/&gt;&lt;/p&gt;&lt;p&gt;美好的一天开始了&lt;img src=&quot;http://img.baidu.com/hi/jx2/j_0002.gif&quot;/&gt;，，恩恩123324234&lt;img src=&quot;http://img.baidu.com/hi/jx2/j_0009.gif&quot;/&gt;&lt;img src=&quot;/ueditor/jsp/upload/image/20180207/1518014345993072584.jpg&quot; title=&quot;1518014345993072584.jpg&quot; alt=&quot;Camera_20120617_160144.jpg&quot; width=&quot;1&quot; height=&quot;1&quot; style=&quot;width: 1px; height: 1px;&quot;/&gt;&lt;/p&gt;";

        String s = StringEscapeUtils.unescapeHtml4(str);
*/
		String[] temp = "http://beehot.senson.cc/dist/static/imgs/default.png".split("/");
		String t_temp = temp[temp.length-1];
		if(!t_temp.equals("dist") && !t_temp.equals("news") &&!t_temp.equals("works")){
			System.out.println(t_temp);
		}


	}
}
