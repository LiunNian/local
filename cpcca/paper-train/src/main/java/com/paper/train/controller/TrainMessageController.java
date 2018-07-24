package com.paper.train.controller;

import com.paper.common.controller.BaseController;
import com.paper.train.service.TrainMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 培训信息管理
 */
@Controller
@RequestMapping("/train/system/message")
public class TrainMessageController extends BaseController{

	@Autowired
	private TrainMessageService trainMessageService;

	/**
	 * 查询所有
	 * @return
	 */
	@PostMapping("/all")
	@ResponseBody
	public Object findAll(){
		return trainMessageService.findAll();
	}

	@PostMapping("/findPrice")
	@ResponseBody
	public Object findPrice(@RequestParam("id") int messageId){
		return  trainMessageService.findPrice(messageId);
	}

}
