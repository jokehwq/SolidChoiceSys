/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.file.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.FileUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.system.file.entity.SysFile;
import com.foreveross.webbase.system.file.service.SysFileService;
import com.foreveross.webbase.system.sys.utils.UserUtils;

/**
 * 附件Controller
 * @author zhangle
 * @version 2016-12-01
 */
@Controller
@RequestMapping(value = "${adminPath}/system/file/sysFile")
public class SysFileController extends BaseController {

	@Autowired
	private SysFileService sysFileService;
	
	@RequiresPermissions("system:file:sysFile:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysFile sysFile) {
		Page<SysFile> page = sysFileService.findPage(new Page<SysFile>(request(), response()), sysFile); 
		attr("page", page);
		return "system/file/sysFileList";
	}

	@RequiresPermissions("system:file:sysFile:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			SysFile sysFile=sysFileService.get(id);
			attr("sysFile", sysFile);
		} else {
			attr("sysFile", new SysFile());
		}
		return "system/file/sysFileForm";
	}

	@RequiresPermissions("system:file:sysFile:edit")
	@RequestMapping("save")
	public String save(SysFile sysFile,MultipartFile file,RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, sysFile)){
			return form(sysFile.getId());
		}
		if(sysFile.getIsNewRecord()) {
			sysFile.setCreateby(UserUtils.getUser().getLoginName());
			sysFile.setCreatetime(new Date());
		}
		if(file!=null && !file.isEmpty()) {
			sysFile.setName(file.getOriginalFilename());
			
			sysFile.setExt(FileUtils.getFileExtension(file.getOriginalFilename()));
			sysFile.setSize(file.getSize()+"");
			sysFile.setSizestr(getSizeStr(file.getSize()));
			
			sysFile.setPath(UUID.randomUUID().toString());
			String uploaddir=Global.getConfig("file.uploaddir");
			String filepath=uploaddir+sysFile.getPath();
			File f=new File(filepath);
			try {
				if(!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				file.transferTo(f);
				sysFileService.save(sysFile);
				addMessage(redirectAttributes, "保存附件成功");
			} catch (IllegalStateException | IOException e) {
				addMessage(redirectAttributes, "保存附件失败");
			}
		}
		
		return "redirect:"+Global.getAdminPath()+"/system/file/sysFile/?repage";
	}
	
	private String getSizeStr(long size) {
		
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
	
	@RequiresPermissions("system:file:sysFile:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		
		SysFile sysFile=sysFileService.get(id);
		String uploaddir=Global.getConfig("file.uploaddir");
		String filepath=uploaddir+sysFile.getPath();
		File f=new File(filepath);
		f.delete();
		sysFileService.delete(id);
		addMessage(redirectAttributes, "删除附件成功");
		return "redirect:"+Global.getAdminPath()+"/system/file/sysFile/?repage";
	}
	
	@RequiresPermissions("system:file:sysFile:download")
	@RequestMapping("download")
	public void download(String id,HttpServletResponse resp) {
		
		SysFile sysFile=sysFileService.get(id);
		String uploaddir=Global.getConfig("file.uploaddir");
		String filepath=uploaddir+sysFile.getPath();
		File f=new File(filepath);
		
		resp.setContentType("application/force-download");
		FileUtils.downFile(f, request(), resp, sysFile.getName());
	}

}