package com.paper.train.controller;

import com.paper.common.controller.BaseController;
import com.paper.train.model.TrainUser;
import com.paper.train.service.TrainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 分类信息管理
 */
@Controller
@RequestMapping("/train/system/user")
public class TrainUserController extends BaseController{

	@Autowired
	private TrainUserService trainUserService;

	/**
	 * 用户注册提交
	 * @param user 用户实体
	 * @return
	 */
	@PostMapping("/regist")
	@ResponseBody
	public Object trainRegist(@RequestBody TrainUser user){
		return trainUserService.trainRegist(user);
	}

	/**
	 * 用户登录
	 * @param phone 电话号码
	 * @return
	 */
	@PostMapping("/login")
	@ResponseBody
	public Object trainLogin(@RequestParam("phone") String phone){
		return trainUserService.trainLogin(phone);
	}

	/**
	 * 获取验证码
	 * @param phonenum
	 * @return
	 */
	@PostMapping("/verification")
	@ResponseBody
	public Object getVerifyCode(@RequestParam("phonenum") String phonenum){
		return trainUserService.getVerifyCode(phonenum);
	}

	@PostMapping("/call")
	@ResponseBody
	public Object checkPhone(@RequestParam("phone") String phone){
		return trainUserService.checkPhone(phone);
	}

	@PostMapping("/reset")
	@ResponseBody
	public Object resetPassword(@RequestBody TrainUser user){
		return trainUserService.resetPassword(user);
	}

}
