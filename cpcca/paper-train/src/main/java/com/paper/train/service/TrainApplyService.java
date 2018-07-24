package com.paper.train.service;


import com.paper.train.model.TrainApply;

public interface TrainApplyService {

    Object addApply(TrainApply apply);

    Object checkUserIsApply(int uid, int mid);
}
