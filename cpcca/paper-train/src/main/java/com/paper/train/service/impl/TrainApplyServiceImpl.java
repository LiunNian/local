package com.paper.train.service.impl;

import com.paper.common.model.Result;
import com.paper.common.util.StringUtils;
import com.paper.train.dao.MemberUnitMapper;
import com.paper.train.dao.TrainApplyMapper;
import com.paper.train.dao.TrainMesaageMapper;
import com.paper.train.model.TrainApply;
import com.paper.train.model.TrainMesaage;
import com.paper.train.service.TrainApplyService;
import com.paper.train.service.TrainMemberUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service("trainApplyService")
@Transactional
public class TrainApplyServiceImpl implements TrainApplyService {

    @Autowired
    TrainApplyMapper trainApplyMapper;

    @Autowired
    TrainMemberUnitService trainMemberUnitService;

    @Autowired
    TrainMesaageMapper trainMesaageMapper;

    /**
     * 新增预报名信息
     * @param apply
     * @return
     */
    @Override
    public Object addApply(TrainApply apply) {

        if(StringUtils.isEmpty( apply.getCity() )){
            return new Result("城市不能为空", 0, false);
        }

        if(StringUtils.isEmpty( apply.getEmail() )){
            apply.setEmail("");
        }
        if(StringUtils.isEmpty( apply.getOfficephone() )){
            apply.setOfficephone("");
        }
        if(StringUtils.isEmpty( apply.getRemark() )){
            apply.setRemark("");
        }
        Result result = (Result) checkUserIsApply(apply.getUid(), apply.getMid());
        if(!result.isSuccess()){
            return result;
        }

        result = (Result) trainMemberUnitService.findPriceByUnit(apply.getName(), apply.getMid());
        TrainMesaage mesaage = trainMesaageMapper.selectByPrimaryKey(apply.getMid());
        if(result.isSuccess()){
            apply.setPrice(mesaage.getMemberprice());
            apply.setIsmember(1);
        }else {
            apply.setPrice(mesaage.getPrice());
            apply.setIsmember(0);
        }

        if(trainApplyMapper.insert(apply) > 0){
            int count = trainApplyMapper.selectBymid(apply.getMid());
            Map map = new HashMap();
            map.put("all","你是第"+count+"位预报名成功的用户，我们将在报名人数达标后通过短信和站内信的方式通知您正式报名时间，请注意查收短信和站内信。");
            map.put("count", count);
            return new Result("报名成功", map , 1, true);
        }else {
            return new Result("报名成功失败", 0, false);
        }
    }

    @Override
    public Object checkUserIsApply(int uid, int mid) {
        if(trainApplyMapper.selectCountByUIDandMID(uid, mid) > 0){
            return new Result("已预报名该培训", 0, false);
        }
        return new Result("未预报名该培训", 1, true);
    }
}
