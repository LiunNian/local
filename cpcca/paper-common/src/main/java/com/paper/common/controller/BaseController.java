package com.paper.common.controller;

import com.paper.common.model.Result;
import com.paper.common.model.User;
import com.paper.common.util.DateUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class BaseController {

	protected Logger log = Logger.getLogger(this.getClass());
	private HttpServletRequest request;
 	private HttpSession session;
	private Subject subject;
	private User user;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
		
		// Timestamp 类型转换
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Date date = DateUtils.parseDate(text);
				setValue(date==null?null:new Timestamp(date.getTime()));
			}
		});
	}
	private void initSubject(){
		if(subject == null){
			subject = SecurityUtils.getSubject();
		}
	}

	protected User getShiroUser(){
		this.initSubject();
		if(user == null){
			user = (User)subject.getPrincipal();
		}
		return  user;
	}

	protected String getUserCompanyId(){
		this.getShiroUser();
		return user.getCompanyId();
	}

	private HttpServletRequest initRequest() {
		if(request == null) {
			request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
		}
		return request;
	}

	protected HttpSession initHttpSession(){
		initRequest();
		if(session == null) {
			session = request.getSession();
		}
		return session;
	}

	public HttpServletRequest getRequest() {
		return request == null ? initRequest() : request;
	}

	public HttpSession getSession() {
		return session == null ? initHttpSession() :  session;
	}

	public Object fileUpload(MultipartFile file, String flag){
		String originalFilename = file.getOriginalFilename();
		System.out.print(originalFilename);
		String suffixName = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
//		String filePath = "D:\\work\\project\\temp\\java\\cpcca\\";
//		String basePath = "/wnmp/cpcca/docker/cpcca/uploads/";
		String basePath = "F:/公司/company/cpcca/Registered-members/file/uploads/";
		String filePath = basePath;
		if(flag.equals("member")){
			filePath = basePath+"member/predict/img/";
		}
		String fileName = UUID.randomUUID().toString().replaceAll("-","") + suffixName;
		File dest = new File(filePath + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		try {
			byte[] bytes = file.getBytes();
			file.transferTo(dest);
			Map map = new HashMap();
			String path = filePath.substring(filePath.lastIndexOf("uploads"), filePath.length())+fileName;
			map.put("path","/"+path);
            return new Result("文件上传成功", map, 1, true);
		} catch (IllegalStateException e) {
			e.printStackTrace();
            return new Result("文件上传出错", 0, false);
		} catch (IOException e) {
			e.printStackTrace();
            return new Result("文件上传出错", 0, false);
		}
	}
}
