package com.paper.train.controller;

import com.paper.common.controller.BaseController;
import com.paper.train.service.TrainMemberUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 培训信息管理
 */
@Controller
@RequestMapping("/train/system/message")
public class TrainMemberUnitController extends BaseController{

	@Autowired
	private TrainMemberUnitService trainMemberUnitService;

	/**
	 * 查询是否为会员单位 -->是：返回会员单位价格
	 * @param unitName
	 * @param messageId
	 * @return
	 */
	@PostMapping("/findUnit")
	@ResponseBody
	public Object findPriceByUnit(@RequestParam("name") String unitName, @RequestParam("messageid") int messageId){
		return trainMemberUnitService.findPriceByUnit(unitName, messageId);
	}




}
