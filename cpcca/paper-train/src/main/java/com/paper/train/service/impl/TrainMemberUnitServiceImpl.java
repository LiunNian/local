package com.paper.train.service.impl;

import com.paper.common.model.Result;
import com.paper.common.util.StringUtils;
import com.paper.train.dao.MemberUnitMapper;
import com.paper.train.dao.TrainMesaageMapper;
import com.paper.train.model.TrainMesaage;
import com.paper.train.service.TrainMemberUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service("trainMemberUnitService")
@Transactional
public class TrainMemberUnitServiceImpl implements TrainMemberUnitService {

    @Autowired
    private MemberUnitMapper memberUnitMapper;

    @Autowired
    private TrainMesaageMapper trainMesaageMapper;

    @Override
    public Object findPriceByUnit(String unitName, int messageId) {
        int unitCount = memberUnitMapper.selectUnit(unitName);
        double price = 0;
        TrainMesaage mesaage = trainMesaageMapper.selectByPrimaryKey(messageId);
        Map result = new HashMap();
        if(unitCount > 0){
            if(mesaage.getMemberprice() > 0){
                price = mesaage.getMemberprice();
            }else {
                price = mesaage.getPrice();
            }
            Map<String, String> map =  memberUnitMapper.selectByName(unitName);

            result.put("price", price);
            result.put("type", map.get("type"));
            String expense = "";
            if(StringUtils.isEmpty(map.get("m2015"))){
               expense = "2015" ;
            }

            if(StringUtils.isEmpty(map.get("m2016"))){
                if("".equals(expense) ){
                    expense = "2016" ;
                }else {
                    expense += "、"+"2016" ;
                }
            }

            if(StringUtils.isEmpty(map.get("m2017"))){
                if("".equals(expense) ){
                    expense = "2017" ;
                }else {
                    expense = expense+ "、"+"2017" ;
                }
            }
            if(StringUtils.isEmpty(map.get("m2018"))){
                if("".equals(expense) ){
                    expense = "2018" ;
                }else {
                    expense = expense+ "、"+"2018" ;
                }
            }

//            if(!StringUtils.isEmpty(expense)){
//                expense = "第"+expense+"年未缴纳会费";
//            }
            result.put("expense", expense);

            return new Result("查询成功", result , 1, true);
        }else {
            price = mesaage.getPrice();
            result.put("price", price);
            return new Result("该单位不是会员",result,  0, false);
        }
    }





}
