package cn.org.cpcca.feignserver.train.controllers;

import cn.org.cpcca.feignserver.train.services.TrainMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 培训信息管理
 */
@Controller
@Api(tags = "培训信息",value = "培训信息")
@RestController
@RequestMapping(value = "train/system/message", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
public class TrainMessageController{

	@Autowired
	private TrainMessageService trainMessageService;

	/**
	 * 查询所有
	 * @return
	 */
	@ApiOperation(value = "查询所有培训课程", notes = "查询所有培训课程")
	@RequestMapping(value = "/all", produces = {"application/json"})
	@ResponseBody
	public Object findAll(){
		return trainMessageService.findAll();
	}

	/**
	 * 查询培训课程价格
	 * @param messageId
	 * @return
	 */
	@ApiOperation(value = "查询培训课程价格", notes = "查询培训课程价格")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "培训课程id", required = true, paramType = "query", dataType = "int")
	})
	@RequestMapping(value = "/findPrice", produces = {"application/json"})
	@ResponseBody
	public Object findPrice(@RequestParam("id") int messageId){
		return  trainMessageService.findPrice(messageId);
	}

}
