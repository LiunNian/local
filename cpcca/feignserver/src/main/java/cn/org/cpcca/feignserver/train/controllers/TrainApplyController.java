package cn.org.cpcca.feignserver.train.controllers;

import cn.org.cpcca.feignserver.train.models.TrainApply;
import cn.org.cpcca.feignserver.train.services.TrainApplyService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 培训信息管理
 */
@RestController
@Api(tags = "培训预约报名",value = "培训预约报名")
@RequestMapping(value = "train/system/apply", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
public class TrainApplyController {

	@Autowired
	private TrainApplyService trainApplyService;

	/**
	 * 新增预约报名
	 * @return
	 */
	@ApiOperation(value = "新增预约培训信息", notes = "新增预约培训信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uid", value = "用户id", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "mid", value = "培训id", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "nation", value = "民族", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "sex", value = "性别", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "province", value = "省", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "city", value = "市", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "birth", value = "出生年月", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "idcard", value = "身份证", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "duty", value = "职务", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "unitname", value = "单位名称", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "unitaddress", value = "单位地址", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "email", value = "电子邮箱", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "officephone", value = "办公电话", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "remark", value = "备注", paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/addapply", produces = {"application/json"})
	@ResponseBody
	public Object addApply(@RequestParam("uid") Integer uid, @RequestParam("mid")Integer mid, @RequestParam("name") String name,
						   @RequestParam("nation") String nation, @RequestParam("sex") String sex, @RequestParam("province") String province,
						   @RequestParam("city") String city, @RequestParam("birth") String birth, @RequestParam("idcard") String idcard, @RequestParam("duty") String duty,
						   @RequestParam("unitname") String unitname, @RequestParam("unitaddress") String unitaddress, @RequestParam("phone") String phone,
						   @RequestParam("email") Optional<String> email, @RequestParam("officephone") Optional<String> officephone, @RequestParam("remark") Optional<String> remark ){
 		TrainApply apply = new TrainApply();
 		apply.setUid(uid);
		apply.setMid(mid);
		apply.setName(name);
		apply.setNation(nation);
		apply.setSex(sex);
		apply.setProvince(province);
		apply.setCity(city);
		apply.setBirth(birth);
		apply.setIdcard(idcard);
		apply.setDuty(duty);
		apply.setUnitname(unitname);
		apply.setUnitaddress(unitaddress);
		apply.setPhone(phone);
		apply.setEmail(email.orElse(""));
		apply.setOfficephone(officephone.orElse(""));
		apply.setRemark(remark.orElse(""));

		return trainApplyService.addApply(apply);
	}

	/**
	 * 检查是否有重复报名
	 * @param uid
	 * @param mid
	 * @return
	 */
	@ApiOperation(value = "检查预约培训信息是否存在", notes = "检查预约培训信息是否存在")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uid", value = "用户id", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "mid", value = "培训id", required = true, paramType = "query", dataType = "int")
	})
	@RequestMapping(value = "/checkapply", produces = {"application/json"})
	@ResponseBody
	public Object check(@RequestParam("uid") int uid , @RequestParam("mid")int mid){
		return trainApplyService.check(uid, mid);
	}

	@ApiOperation(value = "文件上传", notes = "上传文件，限制文件png，jpg")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file", value = "照片", required = true, paramType = "query", dataType = "obj")
	})
	@RequestMapping(value = "/upload", produces = {"application/json"})
	public Object uploadImg(@RequestParam("file") MultipartFile file) {
        return trainApplyService.uploadImg(file);
	}

}
