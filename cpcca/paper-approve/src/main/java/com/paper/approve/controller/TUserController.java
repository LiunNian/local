package com.paper.approve.controller;

import com.paper.approve.model.TUser;
import com.paper.approve.service.TUserService;
import com.paper.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 分类信息管理
 */
@Controller
@RequestMapping("/train/system/user")
public class TUserController extends BaseController{

	@Autowired
	private TUserService tUserService;

	/**
	 * 用户注册提交
	 * @param user 用户实体
	 * @return
	 */
	@PostMapping("/regist")
	@ResponseBody
	public Object trainRegist(@RequestBody TUser user){
		return tUserService.trainRegist(user);
	}

	/**
	 * 用户登录
	 * @param phonenum 电话号码
	 * @param verifyCode 短信验证码
	 * @return
	 */
	@PostMapping("/login")
	@ResponseBody
	public Object trainLogin(@RequestParam("phone") String phonenum , @RequestParam("verify") String verifyCode){
		return tUserService.trainLogin(phonenum, verifyCode);
	}

	/**
	 *  获取验证码
	 * @param phonenum
	 * @return
	 */
	@PostMapping("/verification")
	@ResponseBody
	public Object getVerifyCode(@RequestParam("phonenum") String phonenum){
		return tUserService.getVerifyCode(phonenum);
	}

	@PostMapping("/call")
	@ResponseBody
	public Object checkPhone(@RequestParam("phone") String phone){
		return tUserService.checkPhone(phone);
	}

}
