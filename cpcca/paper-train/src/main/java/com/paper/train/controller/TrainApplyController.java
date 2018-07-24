package com.paper.train.controller;

import com.alibaba.fastjson.JSON;
import com.paper.common.controller.BaseController;
import com.paper.common.model.Result;
import com.paper.train.model.TrainApply;
import com.paper.train.service.TrainApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 培训信息管理
 */
@Controller
@RequestMapping("/train/system/apply")
public class TrainApplyController extends BaseController{

	@Autowired
	private TrainApplyService trainApplyService;

	/**
	 * 新增预约报名
	 * @return
	 */
	@PostMapping("/addapply")
	@ResponseBody
	public Object addApply(@RequestBody TrainApply apply){
		return trainApplyService.addApply(apply);
	}

	/**
	 * 检查是否有重复报名
	 * @param uid
	 * @param mid
	 * @return
	 */
	@PostMapping("/checkapply")
	@ResponseBody
	public Object check(@RequestParam("uid") int uid , @RequestParam("mid")int mid){
		return trainApplyService.checkUserIsApply(uid, mid);
	}

	@RequestMapping(value="/upload")
	@CrossOrigin("*")
	@ResponseBody
	public Object uploadImg( @RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		if(".png".equals(suffixName)||".jpg".equals(suffixName)){
			return this.fileUpload(file,"train");
		}else{
			return new Result("文件类型错误", 0, false);
		}
	}


}
