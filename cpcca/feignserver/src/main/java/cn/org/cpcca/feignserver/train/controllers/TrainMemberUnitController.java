package cn.org.cpcca.feignserver.train.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.org.cpcca.feignserver.train.services.TrainMemberUnitService;

/**
 * 培训信息管理
 */
@RestController
@Api(tags = "培训查询会员单位",value = "培训查询会员单位")
@RequestMapping(value = "train/system/message", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
public class TrainMemberUnitController{

	@Autowired
	private TrainMemberUnitService trainMemberUnitService;

	/**
	 * 查询是否为会员单位 -->是：返回会员单位价格
	 * @param unitName
	 * @param messageId
	 * @return
	 */
	@ApiOperation(value = "查询会员单位价格", notes = "查询会员单位价格")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "单位名称", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "messageid", value = "培训信息id", required = true, paramType = "query", dataType = "int")
	})
	@RequestMapping(value = "/findUnit", produces = {"application/json"})
	@ResponseBody
	public Object findPriceByUnit(@RequestParam("name") String unitName, @RequestParam("messageid") int messageId){
		return trainMemberUnitService.findPriceByUnit(unitName, messageId);
	}




}
