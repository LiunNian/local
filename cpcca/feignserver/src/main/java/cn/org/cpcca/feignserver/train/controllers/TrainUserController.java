package cn.org.cpcca.feignserver.train.controllers;

import cn.org.cpcca.feignserver.paper.shiro.models.User;
import cn.org.cpcca.feignserver.train.models.TrainUser;
import cn.org.cpcca.feignserver.train.services.TrainUserService;
import cn.org.cpcca.feignserver.train.models.Result;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户操作相关
 */
@RestController
@Api(tags = "培训用户操作相关",value = "培训用户操作相关")
@RequestMapping(value = "train", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
public class TrainUserController{

	@Autowired
	private TrainUserService trainUserService;

	/**
	 * 用户注册提交
	 * @param
	 * @return
	 */
	@ApiOperation(value = "用户注册提交", notes = "用户注册提交")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "phonenum", value = "手机号码", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "unitname", value = "单位名称", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "jobtitle", value = "职务", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "status", value = "状态", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "verify", value = "验证码", required = true, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/regist", produces = {"application/json"})
	@ResponseBody
	public Object trainRegist(@RequestParam("name") String name, @RequestParam("phonenum") String phonenum, @RequestParam("password") String password,
							  @RequestParam("unitname") String unitname,
//							  @RequestParam("jobtitle") String jobtitle, @RequestParam("status") Integer status,
							  @RequestParam("verify") String verify){
		TrainUser user = new TrainUser();
		user.setName(name);
		user.setPhonenum(phonenum);
		user.setPassword(password);
		user.setUnitname(unitname);
		user.setJobtitle("");
		user.setStatus(0);
		user.setVerify(verify);

		return trainUserService.trainRegist(user);
	}


	/**
	 * 用户登录
	 * @param phonenum
	 * @param password
	 * @return
	 */
	@ApiOperation(value = "用户登陆", notes = "用户登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phonenum", value = "手机号码", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/login", produces = {"application/json"})
	@ResponseBody
	public Object trainLogin(@RequestParam("phonenum") String phonenum, @RequestParam("password") String password){
		Subject currentUser = SecurityUtils.getSubject();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		User token = new User(phonenum, md5Password,"train");
		try {
			token.setRememberMe(false);// 不使用记住我功能
			if (!currentUser.isAuthenticated()) {
				currentUser.login(token);
			}
            TrainUser trainUser = (TrainUser) currentUser.getPrincipal();
			return new Result("登录成功",trainUser.getId(), 1, true);
		} catch (UnknownAccountException e) {
			return new Result("账号不存在", 1001, false);//账号不存在
		}catch (LockedAccountException e) {
			return new Result("账号被锁定", 1002, false);//账号未启用
		}catch (DisabledAccountException e) {
			return new Result("账号未启用", 1003, false);//账号未启用
		}
		catch (IncorrectCredentialsException e) {
			return new Result("密码错误", 1004, false);//密码错误
		} catch (RuntimeException e) {
			return new Result("系统出错，请刷新后重试", 1005, false);//未知错误,请联系管理员
		}
	}

	/**
	 * 获取验证码
	 * @param phonenum
	 * @return
	 */
	@ApiOperation(value = "获取验证码", notes = "获取验证码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phonenum", value = "手机号码", required = true, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/verification", produces = {"application/json"})
	@ResponseBody
	public Object getVerifyCode(@RequestParam("phonenum") String phonenum){
		return trainUserService.getVerifyCode(phonenum);
	}

	/**
	 * 查询手机号是否已存在
	 * @param phone
	 * @return
	 */
	@ApiOperation(value = "查询手机号是否已存在", notes = "查询手机号是否已存在")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/call", produces = {"application/json"})
	@ResponseBody
	public Object checkPhone(@RequestParam("phone") String phone){
		return trainUserService.checkPhone(phone);
	}


	/**
	 * 重置密码
	 * @param phonenum
	 * @param password
	 * @param verify
	 * @return
	 */
	@ApiOperation(value = "重置密码", notes = "重置密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phonenum", value = "手机号码", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "verify", value = "验证码", required = true, paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/reset", produces = {"application/json"})
	@ResponseBody
	public Object resetPassword(@RequestParam("phonenum") String phonenum, @RequestParam("password") String password,
								 @RequestParam("verify") String verify){
		TrainUser user = new TrainUser();
		user.setPhonenum(phonenum);
		user.setPassword(password);
		user.setVerify(verify);

		return trainUserService.resetPassword(user);
	}

}
